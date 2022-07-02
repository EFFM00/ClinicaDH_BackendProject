package com.dh.clinica.persistence.repository;

import com.dh.clinica.persistence.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
}
