package org.henu.blastfurnace.datasplit;

import org.biojava.bio.BioException;
import org.biojava.bio.seq.Sequence;
import org.biojava.bio.seq.SequenceIterator;
import org.biojava.bio.seq.db.SequenceDB;
import org.biojava.bio.seq.io.SeqIOTools;
import org.biojava.bio.symbol.Alphabet;
import org.biojava.bio.symbol.AlphabetManager;
import org.henu.blastfurnace.datasplit.entity.Gene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataImportService {
    Logger logger = LoggerFactory.getLogger(DataImportService.class);

    public List<Gene> importFASTA(String dir) throws IOException, BioException {
        List<Sequence> sequenceList = new ArrayList<>();
        List<Gene> genes = new ArrayList<>();
        Alphabet alpha = AlphabetManager.alphabetForName("DNA");
        File geneDir = new File(dir);
        for (File fastaEntry : geneDir.listFiles()) {
            FileInputStream is = new FileInputStream(fastaEntry);
            logger.info("before reading " + fastaEntry.getName());
            SequenceDB db = SeqIOTools.readFasta(is, alpha);
            logger.info("after reading " + fastaEntry.getName());
            for (SequenceIterator iterator = db.sequenceIterator(); iterator.hasNext(); ) {
                sequenceList.add(iterator.nextSequence());
            }
            is.close();
        }
        for (Sequence sequence : sequenceList) {
            Gene gene = new Gene();
            gene.setGeneID(sequence.getName());
            gene.setSub(sequence.seqString());
            gene.setSubLen(sequence.length());
            genes.add(gene);
        }
        return genes;
    }
}
