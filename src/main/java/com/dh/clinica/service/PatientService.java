package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> findAllPatient(){
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> findPatientById(Long id){
        return patientRepository.findById(id);
    }

    public void deletePatientById(Long id){
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

}
