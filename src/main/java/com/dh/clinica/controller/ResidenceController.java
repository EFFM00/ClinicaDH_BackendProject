package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.service.ResidenceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    Logger logger = Logger.getLogger(DentistController.class);
    @PostMapping("/new")
    public ResponseEntity<Residence> newResidence(@RequestBody Residence residence){
        return ResponseEntity.ok(residenceService.saveResidence(residence));
    }

    @GetMapping
    public ResponseEntity<List<Residence>> findAllResidences(){
        return ResponseEntity.ok(residenceService.findAllResidences());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Residence> updatePatient(@RequestBody Residence residence, @PathVariable Long id) {
        ResponseEntity<Residence> response = null;

        if(residence.getId() != null && residenceService.findResidenteById(residence.getId()).isPresent() && residence.getId()==id){
            logger.info("Se actualizó el domicilio: " + residence);
            response = ResponseEntity.ok(residenceService.updateResidence(residence));
        } else {
            logger.error("Fallo al intentar actualizar odontólogo: " + residenceService.findResidenteById(residence.getId()));
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
