package org.henu.blastfurnace.datasplit;

import com.google.gson.Gson;
import org.henu.blastfurnace.datasplit.entity.Gene;
import org.henu.blastfurnace.datasplit.repo.GeneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Service
public class DataPrepService {
    private GeneRepo geneRepo;
    private Environment env;
    @Autowired
    public DataPrepService(GeneRepo geneRepo, Environment env) {
        this.geneRepo = geneRepo;
        this.env = env;
    }
    public void prepData(int numNode) throws IOException {
        File file = new File(Objects.requireNonNull(env.getProperty("gene.data.dir")));
        if(file.exists()){
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
        while( i < numNode - 1){
            FileWriter writer = new FileWriter(new File(Objects.requireNonNull(env.getProperty("gene.data.dir"))+i+".json"));
            writer.write(new Gson().toJson(genes.subList(elem[i],elem[i+1])));
            writer.flush();
            writer.close();
            i++;
        }
    }
}
