package org.henu.blastfurnace.slave.config;

import org.henu.blastfurnace.slave.model.Gene;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Configuration
public class GeneSourceConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CopyOnWriteArrayList<Gene> geneList(){
        return new CopyOnWriteArrayList<>();
    }
}
