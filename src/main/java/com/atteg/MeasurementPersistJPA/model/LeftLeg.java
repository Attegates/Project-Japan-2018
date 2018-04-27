/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Atte
 */
@Entity
@Table(name = "left_leg")
public class LeftLeg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fat_percent")
    private double fatPercent;
    
    @Column(name = "fat_mass")
    private double fatMass;
    
    @Column(name = "muscle_mass")
    private double muscleMass;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurement_id", nullable = false)
    private Measurement measurement;

    public LeftLeg() {
    }

    public LeftLeg(double fatPercent, double fatMass, double muscleMass) {
        this.fatPercent = fatPercent;
        this.fatMass = fatMass;
        this.muscleMass = muscleMass;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public double getFatMass() {
        return fatMass;
    }

    public void setFatMass(double fatMass) {
        this.fatMass = fatMass;
    }

    public double getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(double muscleMass) {
        this.muscleMass = muscleMass;
    }
    
    

    @Override
    public String toString() {
        return "LeftLeg{" + "id=" + id + ", fatPercent=" + fatPercent + '}';
    }
    
    
    

}
