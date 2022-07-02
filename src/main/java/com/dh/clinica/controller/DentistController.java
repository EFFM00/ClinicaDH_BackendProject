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

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping
    public List<Dentist> findAllDentists() {
        return dentistService.findAllDentist();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Long id){
        Dentist dentist = dentistService.findDentistById(id).orElse(null);
        return ResponseEntity.ok(dentist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) {
        ResponseEntity<Dentist> response = null;

        if(dentist.getId() != null && dentistService.findDentistById(dentist.getId()).isPresent()){
            response = ResponseEntity.ok(dentistService.saveDentist(dentist));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping("/new")
    public ResponseEntity<Dentist> registerNewDentist(@RequestBody Dentist odontologo) {
        return ResponseEntity.ok(dentistService.saveDentist(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (dentistService.findDentistById(id).isPresent()) {
            dentistService.deleteDentistById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
