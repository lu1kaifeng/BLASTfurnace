package org.henu.blastfurnace.slave.model;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlignmentEntry implements Serializable {
    private Double percentageOfIdentity;
    private String numtsID;
    private List<AlignedSequence> alignedSequenceList;

    public List<AlignedSequence> getAlignedSequenceList() {
        return alignedSequenceList;
    }

    public void setAlignedSequenceList(List<org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound>> alignedSequenceList) {
        this.alignedSequenceList = new ArrayList<>();
        for (org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound> Seq : alignedSequenceList) {
            this.alignedSequenceList.add(AlignedSequence.ConvertForDisplay(Seq));
        }
    }

    public Double getPercentageOfIdentity() {
        return percentageOfIdentity;
    }

    public void setPercentageOfIdentity(Double percentageOfIdentity) {
        this.percentageOfIdentity = percentageOfIdentity;
    }

    public String getNumtsID() {
        return numtsID;
    }

    public void setNumtsID(String numtsID) {
        this.numtsID = numtsID;
    }

    public static class AlignedSequence implements Serializable {
        private String sequence;
        private Long starts;
        private Long ends;
        private Double coverage;
        private Long numGaps;

        public static AlignedSequence ConvertForDisplay(org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound> Seq) {
            AlignedSequence alignedSequence = new AlignedSequence();
            alignedSequence.sequence = Seq.getSequenceAsString();
            alignedSequence.starts = Seq.getStart().getPosition().longValue();
            alignedSequence.ends = Seq.getEnd().getPosition().longValue();
            alignedSequence.setCoverage(Seq.getCoverage());
            alignedSequence.setNumGaps((long) Seq.getNumGaps());
            return alignedSequence;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public Long getStarts() {
            return starts;
        }

        public void setStarts(Long starts) {
            this.starts = starts;
        }

        public Long getEnds() {
            return ends;
        }

        public void setEnds(Long ends) {
            this.ends = ends;
        }

        public Double getCoverage() {
            return coverage;
        }

        public void setCoverage(Double coverage) {
            this.coverage = coverage;
        }

        public Long getNumGaps() {
            return numGaps;
        }

        public void setNumGaps(Long numGaps) {
            this.numGaps = numGaps;
        }
    }
}
