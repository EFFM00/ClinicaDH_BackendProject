package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Patient;
import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.service.PatientService;
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
    private PatientService patientService;

    @GetMapping
    public List<Patient> findAllPatients(){
        return patientService.findAllPatient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.findPatientById(id).orElse(null);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/new")
    public ResponseEntity<Patient> registerNewPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        ResponseEntity<Patient> response = null;

        if(patient.getId() != null && patientService.findPatientById(patient.getId()).isPresent()){
            response = ResponseEntity.ok(patientService.updatePatient(patient));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (patientService.findPatientById(id).isPresent()) {
            patientService.deletePatientById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}