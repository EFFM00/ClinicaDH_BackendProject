package com.dh.clinica.controller;

import com.dh.clinica.persistence.entity.Residence;
import com.dh.clinica.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @PostMapping("/new")
    public ResponseEntity<Residence> newResidence(@RequestBody Residence residence){
        return ResponseEntity.ok(residenceService.saveResidence(residence));
    }

    @GetMapping
    public ResponseEntity<List<Residence>> findAllResidences(){
        return ResponseEntity.ok(residenceService.findAllResidences());
    }

}
