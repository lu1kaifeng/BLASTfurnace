package org.henu.blastfurnace.datasplit;

import org.henu.blastfurnace.datasplit.entity.Gene;

import java.io.Serializable;

public class TestEntry implements Serializable {
    private Gene gene;
    private Long duration;

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
