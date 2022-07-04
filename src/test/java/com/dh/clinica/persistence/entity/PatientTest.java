package com.dh.clinica.persistence.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientTest {

    @Test
    public void createObjectPatient(){
        Patient patient = new Patient("Prueba", "Test", "534234", "11/04", new Residence("Calle", "calle2", "calle3", "calle4") );

        assertTrue(patient != null);
        assertTrue(patient.getId() == null);
        assertTrue(patient.getResidence() != null);
    }
}