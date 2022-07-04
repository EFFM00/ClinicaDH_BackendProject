package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.persistence.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidenceService {

    @Autowired
    ResidenceRepository residenceRepository;

    public List<Residence> findAllResidences(){
        return residenceRepository.findAll();
    }
    public Optional<Residence> findResidenteById(Long id) {
        return residenceRepository.findById(id);
    }

    public void deleteResidenceById(Long id){
        residenceRepository.deleteById(id);
    }

    public Residence updateResidence(Residence residence){
        return residenceRepository.save(residence);
    }

    public Residence saveResidence(Residence residence){
        return residenceRepository.save(residence);
    }


}