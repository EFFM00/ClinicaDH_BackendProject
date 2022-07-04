package com.dh.clinica.persistence.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistTest {

    @Test
    public void createObjectDentist(){
        Dentist dentist = new Dentist("Prueba", "Test", 22421);

        assertTrue(dentist != null);
        assertTrue(dentist.getId() == null);
    }
}