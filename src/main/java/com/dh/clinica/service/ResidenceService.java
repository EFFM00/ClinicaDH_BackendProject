package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.persistence.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceService {

    @Autowired
    ResidenceRepository residenceRepository;

    public Residence saveResidente(Residence residence) {
        return residenceRepository.save(residence);
    }

    public void deleteResidenceById(Long id){
        residenceRepository.deleteById(id);
    }

    public Residence updateResidence(Residence residence){
        return residenceRepository.save(residence);
    }


}