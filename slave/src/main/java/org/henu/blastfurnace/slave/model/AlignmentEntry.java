package org.henu.blastfurnace.slave.model;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlignmentEntry implements Serializable {
    private Double PercentageOfIdentity;
    private String Numts_ID;
    private List<AlignedSequence> alignedSequenceList;

    public List<AlignedSequence> getAlignedSequenceList() {
        return alignedSequenceList;
    }

    public void setAlignedSequenceList(List<org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound>> alignedSequenceList) {
        this.alignedSequenceList = new ArrayList<>();
        for(org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound> Seq: alignedSequenceList){
            this.alignedSequenceList.add(AlignedSequence.ConvertForDisplay(Seq));
        }
    }

    public Double getPercentageOfIdentity() {
        return PercentageOfIdentity;
    }

    public void setPercentageOfIdentity(Double percentageOfIdentity) {
        PercentageOfIdentity = percentageOfIdentity;
    }

    public String getNumts_ID() {
        return Numts_ID;
    }

    public void setNumts_ID(String numts_ID) {
        Numts_ID = numts_ID;
    }

    public static class AlignedSequence implements Serializable {
        public static AlignedSequence ConvertForDisplay(org.biojava.nbio.core.alignment.template.AlignedSequence<DNASequence, NucleotideCompound> Seq){
            AlignedSequence alignedSequence = new AlignedSequence();
            alignedSequence.Sequence = Seq.getSequenceAsString();
            alignedSequence.Starts = Seq.getStart().getPosition().longValue();
            alignedSequence.Ends = Seq.getEnd().getPosition().longValue();
            alignedSequence.setCoverage(Seq.getCoverage());
            alignedSequence.setNumGaps((long)Seq.getNumGaps());
            return alignedSequence;
        }
        private String Sequence;
        private Long Starts;
        private Long Ends;
        private Double Coverage;
        private Long NumGaps;

        public String getSequence() {
            return Sequence;
        }

        public void setSequence(String sequence) {
            Sequence = sequence;
        }

        public Long getStarts() {
            return Starts;
        }

        public void setStarts(Long starts) {
            Starts = starts;
        }

        public Long getEnds() {
            return Ends;
        }

        public void setEnds(Long ends) {
            Ends = ends;
        }

        public Double getCoverage() {
            return Coverage;
        }

        public void setCoverage(Double coverage) {
            Coverage = coverage;
        }

        public Long getNumGaps() {
            return NumGaps;
        }

        public void setNumGaps(Long numGaps) {
            NumGaps = numGaps;
        }
    }
}
