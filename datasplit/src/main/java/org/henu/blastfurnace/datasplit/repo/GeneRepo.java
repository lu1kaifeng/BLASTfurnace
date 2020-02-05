package org.henu.blastfurnace.datasplit.repo;

import org.henu.blastfurnace.datasplit.entity.Gene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneRepo extends JpaRepository<Gene,Long> {
}
