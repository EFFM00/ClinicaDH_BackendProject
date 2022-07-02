package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Turn;
import com.dh.clinica.service.DentistService;
import com.dh.clinica.service.PatientService;
import com.dh.clinica.service.TurnService;
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
@RequestMapping("/turns")
public class TurnController {

    @Autowired
    private TurnService turnService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentistService dentistService;

    @GetMapping
    public List<Turn> findAllTurns() {
        return turnService.findAllTurns();
    }

    @PostMapping("/new")
    public ResponseEntity<Turn>  registerNewTurn(@RequestBody Turn turn){
        ResponseEntity<Turn> response = null;

        if(patientService.findPatientById(turn.getPatient().getId()).isPresent() && dentistService.findDentistById(turn.getDentist().getId()).isPresent()){
            response = ResponseEntity.ok(turnService.saveTurn(turn));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Turn> updateTurn(@RequestBody Turn turn) {
        ResponseEntity<Turn> response = null;

        if(turn.getId() != null && turnService.findTurnById(turn.getId()).isPresent()){
            response = ResponseEntity.ok(turnService.saveTurn(turn));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurn(@PathVariable Long id){
        ResponseEntity<String> response = null;

        if(turnService.findTurnById(id).isPresent()){
            turnService.deleteTurnById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
