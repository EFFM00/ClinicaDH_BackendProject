package com.dh.clinica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "turn")
public class Turn {

    @Id
    @SequenceGenerator(name="turnSequence",sequenceName="turnSequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turnSequence")
    private Long id;

    private String date;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    public Turn() {
    }

    public Turn(String date, Patient patient, Dentist dentist) {
        this.date = date;
        this.patient = patient;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
