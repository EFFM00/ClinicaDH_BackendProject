package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceNotFoundException;
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
        Dentist dentist = this.createData();
        List<Dentist> listDentist = this.dentistService.findAllDentist();
        Assert.assertTrue(!listDentist.isEmpty());
        Assert.assertTrue(listDentist.size() >= 1);
    }

    @Test
    public void findDentist() throws ResourceNotFoundException {
        Dentist dentist = this.createData();
        Optional<Dentist> searchDentist = this.dentistService.findDentistById(dentist.getId());
        Assert.assertTrue(!searchDentist.isEmpty());
    }

    @Test
    public void deleteDentist() throws ResourceNotFoundException {
        Dentist dentist = this.createData();
        this.dentistService.deleteDentistById(dentist.getId());

        Assert.assertTrue(this.dentistService.findDentistById(dentist.getId()).isEmpty());
    }

    @Test
    public void updateDentist(){
        Dentist dentist = this.createData();
        Dentist dentist2 = this.createData();
        dentist2.setId(dentist.getId());
        dentist2.setName("prueba1");
        dentist2.setLastname("prueba2");
        dentist2.setRegister(2532423);

        this.dentistService.updateDentist(dentist2);

        Assert.assertTrue(this.dentistService.findDentistById(dentist2.getId()).isPresent());
        Assert.assertTrue(dentist.getId() == dentist2.getId());
        Assert.assertTrue(dentist.getName() != dentist2.getName());
    }
}

// Odontologo odontologoMayorId = odontologos.stream().max(Comparator.comparing(Odontologo::getId)).orElseThrow(NoSuchElementException::new);;