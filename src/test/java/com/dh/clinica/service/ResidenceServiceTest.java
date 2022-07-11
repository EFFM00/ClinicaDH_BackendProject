package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.persistence.entity.Residence;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ResidenceServiceTest {

    @Autowired
    private ResidenceService residenceService;

    public Residence createData() {
        return this.residenceService.saveResidence(new Residence("Calle", "calle2", "calle3", "calle4"));
    }

    @Test
    public void findAll(){
        Residence residence = this.createData();
        List<Residence> listResidence = this.residenceService.findAllResidences();
        Assert.assertTrue(!listResidence.isEmpty());
        Assert.assertTrue(listResidence.size() >= 1);
    }

    @Test
    public void findResidence() throws ResourceNotFoundException {
        Residence residence = this.createData();
        Optional<Residence> searchDentist = this.residenceService.findResidenteById(residence.getId());
        Assert.assertTrue(!searchDentist.isEmpty());
    }

    @Test
    public void deleteDentist() throws ResourceNotFoundException {
        Residence residence = this.createData();
        this.residenceService.deleteResidenceById(residence.getId());

        Assert.assertTrue(this.residenceService.findResidenteById(residence.getId()).isEmpty());
    }

    @Test
    public void updateDentist(){
        Residence residence = this.createData();
        Residence residence2 = this.createData();
        residence2.setId(residence.getId());
        residence2.setId(residence.getId());
        residence2.setStreet("prueba1");
        residence2.setNumber("prueba2");
        residence2.setLocality("localidad2");
        residence2.setProvince("province");

        this.residenceService.updateResidence(residence2);

        Assert.assertTrue(this.residenceService.findResidenteById(residence2.getId()).isPresent());
        Assert.assertTrue(residence.getId() == residence2.getId());
        Assert.assertTrue(residence.getLocality() != residence2.getLocality());
    }

    @Test
    public void saveDentist(){
        Residence residence = this.createData();
        Assert.assertTrue(residence.getId() != null);
    }


}