package org.henu.blastfurnace.slave;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.henu.blastfurnace.slave.model.Gene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Init {
    private CopyOnWriteArrayList<Gene> geneList;
    private Environment env;
    @Autowired
    public Init(@Qualifier("geneList") CopyOnWriteArrayList<Gene> geneList, Environment env){
        this.geneList = geneList;
        this.env = env;
    }
    @PostConstruct
    public void importData() throws FileNotFoundException {

        geneList.addAll(new Gson().fromJson(new FileReader(new File(Objects.requireNonNull(env.getProperty("gene.data.json"))))
                ,new TypeToken<ArrayList<Gene>>(){}.getType()));

    }
}
