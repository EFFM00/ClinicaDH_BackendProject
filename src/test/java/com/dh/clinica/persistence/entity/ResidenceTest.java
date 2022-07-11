package com.dh.clinica.persistence.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResidenceTest {

    @Test
    public void createObjectResidence(){
        Residence residence = new Residence("Calle", "calle2", "calle3", "calle4");

        assertTrue(residence != null);
        assertTrue(residence.getId() == null);
    }

}