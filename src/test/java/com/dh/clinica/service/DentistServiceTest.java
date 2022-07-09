package com.dh.clinica.service;

import com.dh.clinica.persistence.entity.Dentist;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DentistServiceTest {

    @Autowired
    private DentistService dentistService;

    public Dentist createData(){
        return this.dentistService.saveDentist(new Dentist("Teresita", "Pepita", 12421));
    }

    @Test
    public void saveDentist(){
        Dentist dentist = this.createData();
        Assert.assertTrue(dentist.getId() != null);
    }


    @Test
    public void findAll(){
        List<Dentist> listDentist = this.dentistService.findAllDentist();
        Assert.assertTrue(!listDentist.isEmpty());
        Assert.assertTrue(listDentist.size() >= 1);
    }

    @Test
    public void finDentist(){
        Dentist dentist = this.createData();
        Optional<Dentist> searchDentist = this.dentistService.findDentistById(dentist.getId());
        Assert.assertTrue(!searchDentist.isEmpty());
    }

    @Test
    public void deleteDentist(){
        Dentist dentist = this.createData();
        this.dentistService.deleteDentistById(dentist.getId());

        Assert.assertTrue(this.dentistService.findDentistById(dentist.getId()).isEmpty());

    }
}

// Odontologo odontologoMayorId = odontologos.stream().max(Comparator.comparing(Odontologo::getId)).orElseThrow(NoSuchElementException::new);;