package com.dh.clinica.persistence.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnTest {

    Dentist dentist;
    Patient patient;

    @BeforeAll
    public void initAll(){
        dentist = new Dentist("Prueba", "Test", 22421);
        patient = new Patient("Prueba", "Test", "534234", "11/04", new Residence("Calle", "calle2", "calle3", "calle4") );
    }

    @Test
    public void createObjectTurn(){
        Turn turn = new Turn("11/11", patient, dentist);

        assertTrue(dentist.getId() == null);
        assertTrue(patient.getId() == null);
        assertTrue(turn.getPatient() != null && turn.getDentist() != null);
    }

}