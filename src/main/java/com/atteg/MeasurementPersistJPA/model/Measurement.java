/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Atte
 */
@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "measurement_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private LeftLeg leftLeg;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private LeftArm leftArm;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private RightLeg rightLeg;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private RightArm rightArm;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private Torso torso;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "measurement")
    private Results results;

    public Measurement() {

    }

    public Measurement(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LeftLeg getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(LeftLeg leftLeg) {
        this.leftLeg = leftLeg;
    }

    public LeftArm getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(LeftArm leftArm) {
        this.leftArm = leftArm;
    }

    public RightLeg getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(RightLeg rightLeg) {
        this.rightLeg = rightLeg;
    }

    public RightArm getRightArm() {
        return rightArm;
    }

    public void setRightArm(RightArm rightArm) {
        this.rightArm = rightArm;
    }

    public Torso getTorso() {
        return torso;
    }

    public void setTorso(Torso torso) {
        this.torso = torso;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Measurement{" + "id=" + id + ", date=" + date + ", leftLeg=" + leftLeg + ", leftArm=" + leftArm + ", rightLeg=" + rightLeg + ", rightArm=" + rightArm + ", torso=" + torso + ", results=" + results + '}';
    }
    
    public String getIdDateString() {
        return "Measurement " + id + " on " + date;
    }
    
    



}
