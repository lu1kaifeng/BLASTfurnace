package org.henu.blastfurnace.slave.service;

import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.structure.align.pairwise.AlignmentResult;
import org.henu.blastfurnace.slave.config.Aligner;
import org.henu.blastfurnace.slave.model.AlignmentEntry;
import org.henu.blastfurnace.slave.model.AlignmentRequest;
import org.henu.blastfurnace.slave.model.Gene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

@Service
public class ConcurrentAlignmentService {
    private CopyOnWriteArrayList<Gene> geneList;
    private Aligner aligner;
    private final Integer THRESHOLD;
    @Autowired
    public ConcurrentAlignmentService(@Qualifier("geneList") CopyOnWriteArrayList<Gene> geneList, @Qualifier("aligner") Aligner aligner, Environment environment) {
        this.geneList = geneList;
        this.aligner = aligner;
        this.THRESHOLD = Integer.parseInt(Objects.requireNonNull(environment.getProperty("fork.threshold")));
    }

    private class AlignmentTask extends RecursiveTask<List<AlignmentEntry>>{
        private List<AlignmentEntry> results = new LinkedList<>();
        private List<Gene> genes;
        private AlignmentRequest request;
        public AlignmentTask(List<Gene> genes,AlignmentRequest request) {
            this.genes = genes;
            this.request = request;
        }

        @Override
        protected List<AlignmentEntry> compute() {
            if(this.genes.size() > THRESHOLD){
                List<Gene> geneSplit1,geneSplit2;
                geneSplit1 = this.genes.subList(0,this.genes.size() / 2);
                geneSplit2 = this.genes.subList(this.genes.size() / 2,this.genes.size());
                ArrayList<AlignmentTask> alignmentTasks = new ArrayList<>();
                alignmentTasks.add(new AlignmentTask(geneSplit1,this.request));
                alignmentTasks.add(new AlignmentTask(geneSplit2,this.request));
                for (Iterator<List<AlignmentEntry>> it = ForkJoinTask.invokeAll(alignmentTasks).stream().map(AlignmentTask::getResults).iterator(); it.hasNext(); ) {
                    List<AlignmentEntry> entries = it.next();
                    results.addAll(entries);
                }
                return results;
            }else{
                try {
                    results.addAll(aligner.Align(request,this.genes));
                    return results;
                } catch (CompoundNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        public List<AlignmentEntry> getResults() {
            return results;
        }
    }

    public List<AlignmentEntry> align(AlignmentRequest alignmentRequest){
       return new AlignmentTask(geneList,alignmentRequest).compute();
    }
}
