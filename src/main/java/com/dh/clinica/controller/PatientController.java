package com.dh.clinica.controller;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.service.PatientService;
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
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private ResidenceService residenceService;
    @Autowired
    private PatientService patientService;

    Logger logger = Logger.getLogger(PatientController.class);

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients(){
        logger.info("Se listaron todos los pacientes");
        return ResponseEntity.ok(patientService.findAllPatient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws ResourceNotFoundException{
        Patient patient = patientService.findPatientById(id).orElse(null);
        if(patient != null){
            logger.info("Se buscó al paciente con id: " + id);
        } else {
            logger.info("No se encontró al paciente con id: " + id);
            throw new ResourceNotFoundException("No existe un paciente con id: " + id);
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/new")
    public ResponseEntity<Patient> registerNewPatient(@RequestBody Patient patient) {
        logger.info("Se agregó al paciente con id: " + patient.getId());
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable Long id) {
        ResponseEntity<Patient> response = null;

        if(patient.getId() != null && patientService.findPatientById(patient.getId()).isPresent() && patient.getId()==id){
            logger.info("Se actualizó al paciente con id: " + patient.getId());
            response = ResponseEntity.ok(patientService.updatePatient(patient));
        } else {
            logger.error("Fallo al intentar actualizar paciente con id: " + patient.getId());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;

        if (patientService.findPatientById(id).isPresent()) {
            patientService.deletePatientById(id);
            logger.info("Se eliminó al paciente: " + id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            throw new ResourceNotFoundException("No existe un paciente con id: " + id);
        }
        return response;
    }
}