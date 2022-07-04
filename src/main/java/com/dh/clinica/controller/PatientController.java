package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.service.DentistService;
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
import java.util.Optional;

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
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.findPatientById(id).orElse(null);
        if(patient != null){
            logger.info("Se buscó al paciente: " + patientService.findPatientById(id));
        } else {
            logger.info("No se encontró al paciente");
        }
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/new")
    public ResponseEntity<Patient> registerNewPatient(@RequestBody Patient patient) {
        logger.info("Se agregó al paciente: " + patient);
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable Long id) {
        ResponseEntity<Patient> response = null;

        if(patient.getId() != null && patientService.findPatientById(patient.getId()).isPresent() && patient.getId()==id){
            logger.info("Se actualizó al paciente: " + patient);
            response = ResponseEntity.ok(patientService.updatePatient(patient));
        } else {
            logger.error("Fallo al intentar actualizar odontólogo: " + patientService.findPatientById(patient.getId()));
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (patientService.findPatientById(id).isPresent()) {
            patientService.deletePatientById(id);
            logger.info("Se eliminó al paciente: " + patientService.findPatientById(id));
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            logger.error("Fallo al intentar borrar al paciente: " + patientService.findPatientById(id));
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}