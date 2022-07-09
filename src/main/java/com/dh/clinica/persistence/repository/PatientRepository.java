package com.dh.clinica.persistence.repository;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
