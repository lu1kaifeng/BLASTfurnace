package org.henu.blastfurnace.datasplit;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.henu.blastfurnace.datasplit.entity.Gene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Service
public class TestAlignmentService {
    private final Integer THRESHOLD;
    private TestAligner testAligner;

    @Autowired
    public TestAlignmentService(Environment environment) {
        this.testAligner = (sequence, genes) -> {
            List<TestEntry> testEntryList = new ArrayList<>();
            for (Gene g : genes) {
                if (g.getSub().equals("")) {
                    TestEntry testEntry = new TestEntry();
                    testEntry.setGene(g);
                    testEntry.setDuration(0L);
                    testEntryList.add(testEntry);
                    continue;
                }
                Long start = System.currentTimeMillis();
                DNASequence target = new DNASequence(sequence,
                        AmbiguityDNACompoundSet.getDNACompoundSet());


                DNASequence query = new DNASequence(g.getSub(),
                        AmbiguityDNACompoundSet.getDNACompoundSet());
                SubstitutionMatrix<NucleotideCompound> matrix = SubstitutionMatrixHelper.getNuc4_4();

                SimpleGapPenalty gapP = new SimpleGapPenalty();
                gapP.setOpenPenalty(5);
                gapP.setExtensionPenalty(2);

                SequencePair<DNASequence, NucleotideCompound> psa =
                        Alignments.getPairwiseAlignment(query, target,
                                Alignments.PairwiseSequenceAlignerType.LOCAL, gapP, matrix);
                TestEntry testEntry = new TestEntry();
                testEntry.setGene(g);
                testEntry.setDuration(System.currentTimeMillis() - start);
                testEntryList.add(testEntry);
            }
            return testEntryList;
        };
        this.THRESHOLD = 16;
    }

    public List<TestEntry> align(List<Gene> genes, String sequence) {
        return new AlignmentTask(genes, sequence).compute();
    }

    private class AlignmentTask extends RecursiveTask<List<TestEntry>> {
        private List<TestEntry> results = new LinkedList<>();
        private List<Gene> genes;
        private String request;

        public AlignmentTask(List<Gene> genes, String request) {
            this.genes = genes;
            this.request = request;
        }

        @Override
        protected List<TestEntry> compute() {
            if (this.genes.size() > THRESHOLD) {
                List<Gene> geneSplit1, geneSplit2;
                geneSplit1 = this.genes.subList(0, this.genes.size() / 2);
                geneSplit2 = this.genes.subList(this.genes.size() / 2, this.genes.size());
                ArrayList<AlignmentTask> alignmentTasks = new ArrayList<>();
                alignmentTasks.add(new AlignmentTask(geneSplit1, this.request));
                alignmentTasks.add(new AlignmentTask(geneSplit2, this.request));
                for (Iterator<List<TestEntry>> it = ForkJoinTask.invokeAll(alignmentTasks).stream().map(AlignmentTask::getResults).iterator(); it.hasNext(); ) {
                    List<TestEntry> entries = it.next();
                    results.addAll(entries);
                }
                return results;
            } else {
                try {
                    results.addAll(testAligner.Align(request, this.genes));
                    return results;
                } catch (CompoundNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        public List<TestEntry> getResults() {
            return results;
        }
    }
}
