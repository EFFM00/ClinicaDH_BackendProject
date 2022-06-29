package com.dh.clinica.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String lastname;

    private String dni;

    private String dateAdmission;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residence_id")
    private Residence residence;

    public Patient() {
    }

    public Patient(String name, String lastname, String dni, String dateAdmission, Residence residence) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.dateAdmission = dateAdmission;
        this.residence = residence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(String dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", dateAdmission='" + dateAdmission + '\'' +
                '}';
    }
}
