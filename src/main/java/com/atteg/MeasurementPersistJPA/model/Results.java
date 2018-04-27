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
@Table(name = "results")
public class Results implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age")
    private int age;
    @Column(name = "height")
    private double height;
    @Column(name = "weight")
    private double weight;
    @Column(name = "fat_percent")
    private double fatPercent;
    @Column(name = "fat_mass")
    private double fatMass;
    @Column(name = "muscle_mass")
    private double muscleMass;
    @Column(name = "bone_bass")
    private double boneMass;
    @Column(name = "bmi")
    private double bMI;
    @Column(name = "visceral_fat")
    private int visceralFat;
    @Column(name = "metabolic_age")
    private int metabolicAge;
    @Column(name = "fat_free_mass")
    private double fatFreeMass;
    @Column(name = "body_water_mass")
    private double bodyWaterMass;
    @Column(name = "body_water_percent")
    private double bodyWaterPercent;
    @Column(name = "icw_mass")
    private double iCWMass;
    @Column(name = "ecw_mass")
    private double eCWMass;
    @Column(name = "ecw_percent")
    private double eCWPercent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurement_id", nullable = false)
    private Measurement measurement;

    public Results() {
    }

    public Results(int age, double height, double weight, double fatPercent, double fatMass, double muscleMass, double boneMass, double bMI, int visceralFat, int metabolicAge, double fatFreeMass, double bodyWaterMass, double bodyWaterPercent, double iCWMass, double eCWMass, double eCWPercent) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.fatPercent = fatPercent;
        this.fatMass = fatMass;
        this.muscleMass = muscleMass;
        this.boneMass = boneMass;
        this.bMI = bMI;
        this.visceralFat = visceralFat;
        this.metabolicAge = metabolicAge;
        this.fatFreeMass = fatFreeMass;
        this.bodyWaterMass = bodyWaterMass;
        this.bodyWaterPercent = bodyWaterPercent;
        this.iCWMass = iCWMass;
        this.eCWMass = eCWMass;
        this.eCWPercent = eCWPercent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
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

    public double getBoneMass() {
        return boneMass;
    }

    public void setBoneMass(double boneMass) {
        this.boneMass = boneMass;
    }

    public double getbMI() {
        return bMI;
    }

    public void setbMI(double bMI) {
        this.bMI = bMI;
    }

    public int getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(int visceralFat) {
        this.visceralFat = visceralFat;
    }

    public int getMetabolicAge() {
        return metabolicAge;
    }

    public void setMetabolicAge(int metabolicAge) {
        this.metabolicAge = metabolicAge;
    }

    public double getFatFreeMass() {
        return fatFreeMass;
    }

    public void setFatFreeMass(double fatFreeMass) {
        this.fatFreeMass = fatFreeMass;
    }

    public double getBodyWaterMass() {
        return bodyWaterMass;
    }

    public void setBodyWaterMass(double bodyWaterMass) {
        this.bodyWaterMass = bodyWaterMass;
    }

    public double getBodyWaterPercent() {
        return bodyWaterPercent;
    }

    public void setBodyWaterPercent(double bodyWaterPercent) {
        this.bodyWaterPercent = bodyWaterPercent;
    }

    public double getiCWMass() {
        return iCWMass;
    }

    public void setiCWMass(double iCWMass) {
        this.iCWMass = iCWMass;
    }

    public double geteCWMass() {
        return eCWMass;
    }

    public void seteCWMass(double eCWMass) {
        this.eCWMass = eCWMass;
    }

    public double geteCWPercent() {
        return eCWPercent;
    }

    public void seteCWPercent(double eCWPercent) {
        this.eCWPercent = eCWPercent;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Results{" + "id=" + id + ", age=" + age + ", height=" + height + ", weight=" + weight + ", fatPercent=" + fatPercent + ", fatMass=" + fatMass + ", muscleMass=" + muscleMass + ", boneMass=" + boneMass + ", bMI=" + bMI + ", visceralFat=" + visceralFat + ", metabolicAge=" + metabolicAge + ", fatFreeMass=" + fatFreeMass + ", bodyWaterMass=" + bodyWaterMass + ", bodyWaterPercent=" + bodyWaterPercent + ", iCWMass=" + iCWMass + ", eCWMass=" + eCWMass + ", eCWPercent=" + eCWPercent + '}';
    }
    
    
    

}
