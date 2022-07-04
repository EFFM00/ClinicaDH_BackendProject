package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.persistence.repository.DentistRepository;
import com.dh.clinica.persistence.repository.PatientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    @Autowired
    DentistRepository dentistRepository;

    public List<Dentist> findAllDentist() {
        return dentistRepository.findAll();
    }

    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public Optional<Dentist> findDentistById(Long id){
        return dentistRepository.findById(id);
    }

    public void deleteDentistById(Long id){
        dentistRepository.deleteById(id);
    }

    public Dentist updateDentist(Dentist dentist){
        return dentistRepository.save(dentist);
    }
}
