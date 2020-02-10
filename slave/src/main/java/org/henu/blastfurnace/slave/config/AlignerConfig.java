package org.henu.blastfurnace.slave.config;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.henu.blastfurnace.slave.model.AlignmentEntry;
import org.henu.blastfurnace.slave.model.Gene;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AlignerConfig {
    @Bean
    public Aligner aligner(){
        return (alignmentRequest, genes) -> {
            List<AlignmentEntry> alignmentEntryList = new ArrayList<>();
            for(Gene g : genes){
                if(g.getSub().equals("")){
                    continue;
                }
                DNASequence target = new DNASequence(alignmentRequest.getSequence(),
                        AmbiguityDNACompoundSet.getDNACompoundSet());


                DNASequence query = new DNASequence(g.getSub(),
                        AmbiguityDNACompoundSet.getDNACompoundSet());
                SubstitutionMatrix<NucleotideCompound> matrix = SubstitutionMatrixHelper.getNuc4_4();

                SimpleGapPenalty gapP = new SimpleGapPenalty();
                gapP.setOpenPenalty(alignmentRequest.getOpenPenalty());
                gapP.setExtensionPenalty(alignmentRequest.getExtensionPenalty());

                SequencePair<DNASequence, NucleotideCompound> psa =
                        Alignments.getPairwiseAlignment(query, target,
                                Alignments.PairwiseSequenceAlignerType.LOCAL, gapP, matrix);
                AlignmentEntry alignmentEntry = new AlignmentEntry();
                alignmentEntry.setGeneID(g.getGeneID());
                alignmentEntry.setAlignedSequenceList(psa.getAlignedSequences());
                alignmentEntry.setPercentageOfIdentity(psa.getPercentageOfIdentity(false));
                if (psa.getPercentageOfIdentity(false) > alignmentRequest.getLowestPercent())
                    alignmentEntryList.add(alignmentEntry);
            }
            return alignmentEntryList;
        };
    }
}
