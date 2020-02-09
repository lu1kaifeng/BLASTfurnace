package org.henu.blastfurnace.datasplit;

import com.google.gson.Gson;
import org.henu.blastfurnace.datasplit.entity.Gene;
import org.henu.blastfurnace.datasplit.repo.GeneRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Service
public class DataPrepService {
    Logger logger = LoggerFactory.getLogger(DataPrepService.class);
    private GeneRepo geneRepo;
    private Environment env;
    private TestAlignmentService alignmentService;

    @Autowired
    public DataPrepService(TestAlignmentService alignmentService, GeneRepo geneRepo, Environment env) {
        this.geneRepo = geneRepo;
        this.env = env;
        this.alignmentService = alignmentService;
    }

    @Deprecated
    public void prepData(int numNode) throws IOException {
        File file = new File(Objects.requireNonNull(env.getProperty("gene.data.dir")));
        if (file.exists()) {
            file.delete();
            file.mkdir();
        }else{
            file.mkdir();
        }
        numNode++;
        List<Gene> genes = geneRepo.findAll();
        int[] elem = new int[numNode];
        int i =0;
        while(i < numNode){
            if( i == 0){
                elem[i] = 0;
                i++;
                continue;
            }
            if(i == numNode - 1) {
                elem[i] = genes.size();
                i++;
                continue;
            }
            elem[i] = ( genes.size() / 4) * i;
            i++;
        }
        i = 0;
        while (i < numNode - 1) {
            FileWriter writer = new FileWriter(new File(Objects.requireNonNull(env.getProperty("gene.data.dir")) + i + ".json"));
            writer.write(new Gson().toJson(genes.subList(elem[i], elem[i + 1])));
            writer.flush();
            writer.close();
            i++;
        }
    }

    public void prepDataPrecise(int numNode, String testSeq) throws IOException {
        List<Gene> genes = geneRepo.findAll();
        logger.info("partitioning data, please wait...");
        logger.info("data source has " + genes.size() + " entries");
        List<TestEntry> result = alignmentService.align(genes, testSeq);
        assert genes.size() == result.size();
        long duration = 0;
        for (TestEntry entry : result) {
            duration += entry.getDuration();
        }
        int i = 0;
        List<List<Gene>> shards = new ArrayList<>();
        Iterator<TestEntry> entryIterator = result.iterator();
        while (i < numNode) {
            long period = duration / numNode;
            long accumulation = 0;
            List<Gene> shard = new ArrayList<>();
            while (accumulation < period) {
                if (!entryIterator.hasNext()) {
                    break;
                }
                TestEntry entry = entryIterator.next();
                shard.add(entry.getGene());
                accumulation += entry.getDuration();
            }
            while ((i == (numNode - 1)) && entryIterator.hasNext()) {
                shard.add(entryIterator.next().getGene());
            }
            shards.add(shard);
            i++;
        }
        i = 0;
        long numEntry = 0;
        for (List<Gene> shard : shards) {
            numEntry += shard.size();
            logger.info("data for node " + i + " has " + shard.size() + " entries");
            i++;
        }
        logger.info(numEntry + " entries in total");
        assert numEntry == genes.size();
        i = 0;
        for (List<Gene> file : shards) {
            FileWriter writer = new FileWriter(new File(Objects.requireNonNull(env.getProperty("gene.data.dir")) + i + ".json"));
            writer.write(new Gson().toJson(file));
            writer.flush();
            writer.close();
            i++;
        }
    }

}
