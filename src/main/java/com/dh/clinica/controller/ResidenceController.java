package com.dh.clinica.controller;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.service.ResidenceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<Residence> getResidenceById(@PathVariable Long id) throws ResourceNotFoundException{
        Residence residence = residenceService.findResidenteById(id).orElse(null);
        if(residence != null){
            logger.info("Se buscó al paciente con id: " + id);
        } else {
            logger.info("No se encontró al paciente con id: " + id);
            throw new ResourceNotFoundException("No existe un domicilio con id: " + id);
        }
        return ResponseEntity.ok(residence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Residence> updatePatient(@RequestBody Residence residence, @PathVariable Long id) {
        ResponseEntity<Residence> response = null;

        if(residence.getId() != null && residenceService.findResidenteById(residence.getId()).isPresent() && residence.getId()==id){
            logger.info("Se actualizó el domicilio: " + residence.getId());
            response = ResponseEntity.ok(residenceService.updateResidence(residence));
        } else {
            logger.error("Fallo al intentar actualizar odontólogo: " + residence.getId());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResidence(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if (residenceService.findResidenteById(id).isPresent()) {
            residenceService.deleteResidenceById(id);
            logger.info("Se eliminó al paciente: " + id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            throw new ResourceNotFoundException("No existe un domicilio con id: " + id);
        }
        return response;
    }
}
