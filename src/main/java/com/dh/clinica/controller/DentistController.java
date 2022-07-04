package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.service.DentistService;
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

import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    Logger logger = Logger.getLogger(DentistController.class);

    @GetMapping
    public ResponseEntity<List<Dentist>> findAllDentists() {
        logger.info("Se listaron todos los odontólogos");
        return ResponseEntity.ok(dentistService.findAllDentist());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Long id){
        Dentist dentist = dentistService.findDentistById(id).orElse(null);
        if(dentist != null){
            logger.info("Se buscó al odontólogo: " + dentistService.findDentistById(id));
        } else {
            logger.error("No se encontró al odontólogo");
        }
        return ResponseEntity.ok(dentist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist, @PathVariable Long id) {
        ResponseEntity<Dentist> response = null;

        if(dentist.getId() != null && dentistService.findDentistById(dentist.getId()).isPresent() && dentist.getId()==id){
            logger.info("Se actualizó al odontólogo: " + dentist);
            response = ResponseEntity.ok(dentistService.updateDentist(dentist));
        } else {
            logger.error("Fallo al intentar actualizar odontólogo: " + dentistService.findDentistById(dentist.getId()));
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping("/new")
    public ResponseEntity<Dentist> registerNewDentist(@RequestBody Dentist dentist) {
        logger.info("Se agregó al odontólogo: " + dentist);
        return ResponseEntity.ok(dentistService.saveDentist(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) {
        ResponseEntity<String> response;

        if (dentistService.findDentistById(id).isPresent()) {
            dentistService.deleteDentistById(id);
            logger.info("Se eliminó al odontólogo: " + dentistService.findDentistById(id));
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            logger.error("Fallo al intentar borrar al odontólogo: " + dentistService.findDentistById(id));
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}


