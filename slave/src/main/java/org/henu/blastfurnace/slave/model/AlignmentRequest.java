package org.henu.blastfurnace.slave.model;

import java.io.Serializable;

public class AlignmentRequest implements Serializable {
    private String sequence;
    private Short openPenalty = 5;
    private Short extensionPenalty = 2;
    private Double lowestPercent = 0.8;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Short getOpenPenalty() {
        return openPenalty;
    }

    public void setOpenPenalty(Short openPenalty) {
        this.openPenalty = openPenalty;
    }

    public Short getExtensionPenalty() {
        return extensionPenalty;
    }

    public void setExtensionPenalty(Short extensionPenalty) {
        this.extensionPenalty = extensionPenalty;
    }

    public Double getLowestPercent() {
        return lowestPercent;
    }

    public void setLowestPercent(Double lowestPercent) {
        this.lowestPercent = lowestPercent;
    }
}
