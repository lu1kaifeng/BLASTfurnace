package org.henu.blastfurnace.slave.config;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.henu.blastfurnace.slave.model.AlignmentEntry;
import org.henu.blastfurnace.slave.model.AlignmentRequest;
import org.henu.blastfurnace.slave.model.Gene;

import java.util.List;

public interface Aligner{
    List<AlignmentEntry> Align(AlignmentRequest alignmentRequest, Iterable<Gene> genes) throws CompoundNotFoundException;
}
