package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.persistence.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenceService {

    @Autowired
    ResidenceRepository residenceRepository;

    public List<Residence> findAllResidences(){
        return residenceRepository.findAll();
    }
    public Residence saveResidente(Residence residence) {
        return residenceRepository.save(residence);
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