package org.henu.blastfurnace.datasplit;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.henu.blastfurnace.datasplit.entity.Gene;

import java.util.List;

public interface TestAligner {
    List<TestEntry> Align(String sequence, Iterable<Gene> genes) throws CompoundNotFoundException;
}
