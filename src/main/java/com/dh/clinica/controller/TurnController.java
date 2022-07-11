package com.dh.clinica.controller;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Dentist;
import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Turn;
import com.dh.clinica.service.DentistService;
import com.dh.clinica.service.PatientService;
import com.dh.clinica.service.TurnService;
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
@RequestMapping("/turns")
public class TurnController {

    @Autowired
    private TurnService turnService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentistService dentistService;

    Logger logger = Logger.getLogger(DentistController.class);

    @GetMapping
    public ResponseEntity<List<Turn>> findAllTurns() {
        logger.info("Se listaron todos los pacientes");
        return ResponseEntity.ok(turnService.findAllTurns());
    }

    @PostMapping("/new")
    public ResponseEntity<Turn>  registerNewTurn(@RequestBody Turn turn) {
        ResponseEntity<Turn> response;

        if(patientService.findPatientById(turn.getPatient().getId()).isPresent() && dentistService.findDentistById(turn.getDentist().getId()).isPresent()){
            response = ResponseEntity.ok(turnService.saveTurn(turn));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turn> getTurnById(@PathVariable Long id) throws ResourceNotFoundException {
        Turn turn = turnService.findTurnById(id).orElse(null);
        if(turn != null){
            logger.info("Se buscó el turno con id: " + id);
        } else {
            logger.error("No se encontró el turno");
            throw new ResourceNotFoundException("No existe un turno con id: " + id);

        }
        return ResponseEntity.ok(turn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurn(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if(turnService.findTurnById(id).isPresent()){
            turnService.deleteTurnById(id);
            logger.info("Se eliminó el turno con id: " + id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            throw new ResourceNotFoundException("No existe un turno con id: " + id);
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turn> updateDentist(@RequestBody Turn turn, @PathVariable Long id) {
        ResponseEntity<Turn> response = null;

        if(turn.getId() != null && turnService.findTurnById(turn.getId()).isPresent() && turn.getId()==id){
            logger.info("Se actualizó el turno: " + turn.getId());
            response = ResponseEntity.ok(turnService.updateTurn(turn));
        } else {
            logger.error("Fallo al intentar actualizar el turno con id: " + turn.getId());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
