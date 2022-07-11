package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.persistence.repository.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    public Patient createData(){
        return this.patientService.savePatient(new Patient("Prueba", "Test", "534234", "11/04", new Residence("Calle", "calle2", "calle3", "calle4")));
    }

    @Test
    public void savePatient(){
        Patient patient = this.createData();
        Assert.assertTrue(patient.getId() != null);
    }

    @Test
    public void findAll(){
        List<Patient> listPatient = this.patientService.findAllPatient();
        Assert.assertTrue(!listPatient.isEmpty());
        Assert.assertTrue(listPatient.size() >= 1);
    }

    @Test
    public void findPatient() throws ResourceNotFoundException {
        Patient patient = this.createData();
        Optional<Patient> searchPatient = this.patientService.findPatientById(patient.getId());
        Assert.assertTrue(!searchPatient.isEmpty());
    }

    @Test
    public void deletePatient() throws ResourceNotFoundException {
        Patient patient = this.createData();
        this.patientService.deletePatientById(patient.getId());

        Assert.assertTrue(this.patientService.findPatientById(patient.getId()).isEmpty());

    }

    @Test
    public void updatePatient(){
        Patient patient= this.createData();
        Patient patient2 = this.createData();
        patient2.setId(patient.getId());
        patient2.setName("prueba1");
        patient2.setLastname("prueba2");
        patient2.setDni("82374923");
        patient2.setDateAdmission("11/22");
        patient2.setResidence(new Residence("prueba1", "prueba2", "prueba3", "calle4"));

        this.patientService.updatePatient(patient);

        Assert.assertTrue(patient.getId() == patient2.getId());
        Assert.assertTrue(patient.getName() != patient2.getName());
    }

}