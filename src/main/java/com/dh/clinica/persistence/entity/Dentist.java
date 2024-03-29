package com.dh.clinica.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dentist")
public class Dentist {

    @Id
    @SequenceGenerator(name="dentistSequence",sequenceName="dentistSequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentistSequence")
    private Long id;

    private String name;
    private String lastname;
    private Integer register;

    @OneToMany(mappedBy = "dentist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Turn> turns = new HashSet<>();

    public Dentist(){
    }

    public Dentist(String name, String lastname, Integer register) {
        this.name = name;
        this.lastname = lastname;
        this.register = register;
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

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", register=" + register +
                '}';
    }
}
