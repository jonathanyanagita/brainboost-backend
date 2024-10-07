package com.brainboost.brainboost.Repository;

import com.brainboost.brainboost.Entity.Baralho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaralhoRepository extends JpaRepository<Baralho, Long> {



}
