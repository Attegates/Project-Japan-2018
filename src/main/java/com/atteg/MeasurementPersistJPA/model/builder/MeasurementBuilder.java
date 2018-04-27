/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.model.builder;

import com.atteg.MeasurementPersistJPA.model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Atte
 */

public class MeasurementBuilder {

    private final IDataParser dataParser;
    
    public MeasurementBuilder(IDataParser dataParser) {
        this.dataParser = dataParser;
    }

    public Measurement buildMeasurement(String dataString) {
        Map<String, String> parsedData;
        parsedData = this.dataParser.parseData(dataString);
        
        Measurement m = new Measurement();
        
        
        try {
            m.setDate(parseDate("Da", parsedData));
        } catch (ParseException e) {
            System.out.println(e);
        }
        
        m.setLeftArm(buildLeftArm(parsedData));
        m.getLeftArm().setMeasurement(m);
        
        m.setLeftLeg(buildLeftLeg(parsedData));
        m.getLeftLeg().setMeasurement(m);
        
        m.setRightArm(buildRightArm(parsedData));
        m.getRightArm().setMeasurement(m);
        
        m.setRightLeg(buildRightLeg(parsedData));
        m.getRightLeg().setMeasurement(m);
        
        m.setTorso(buildTorso(parsedData));
        m.getTorso().setMeasurement(m);
        
        m.setResults(buildResults(parsedData));
        m.getResults().setMeasurement(m);
        
        return m;
    }

    private Torso buildTorso(Map<String, String> parsedData) {
        Torso torso = new Torso();
        torso.setFatPercent(parseDoubleVal("FT", parsedData));
        torso.setFatMass(parseDoubleVal("fT", parsedData));
        torso.setMuscleMass(parseDoubleVal("mT", parsedData));
        return torso;
    }

    private RightLeg buildRightLeg(Map<String, String> parsedData) {
        RightLeg rightLeg = new RightLeg();
        rightLeg.setFatPercent(parseDoubleVal("FR", parsedData));
        rightLeg.setFatMass(parseDoubleVal("fR", parsedData));
        rightLeg.setMuscleMass(parseDoubleVal("mR", parsedData));
        return rightLeg;
    }

    private RightArm buildRightArm(Map<String, String> parsedData) {
        RightArm rightArm = new RightArm();
        rightArm.setFatPercent(parseDoubleVal("Fr", parsedData));
        rightArm.setFatMass(parseDoubleVal("fr", parsedData));
        rightArm.setMuscleMass(parseDoubleVal("mr", parsedData));
        return rightArm;
    }

    private LeftLeg buildLeftLeg(Map<String, String> parsedData) {
        LeftLeg leftLeg = new LeftLeg();
        leftLeg.setFatPercent(parseDoubleVal("FL", parsedData));
        leftLeg.setFatMass(parseDoubleVal("fL", parsedData));
        leftLeg.setMuscleMass(parseDoubleVal("mL", parsedData));
        return leftLeg;
    }

    private LeftArm buildLeftArm(Map<String, String> parsedData) {
        LeftArm leftArm = new LeftArm();
        leftArm.setFatPercent(parseDoubleVal("Fl", parsedData));
        leftArm.setFatMass(parseDoubleVal("fl", parsedData));
        leftArm.setMuscleMass(parseDoubleVal("ml", parsedData));
        return leftArm;
    }
    
    private Results buildResults(Map<String, String> parsedData) {
        Results results = new Results();
        results.setAge(parseIntVal("AG", parsedData));
        results.setBodyWaterMass(parseDoubleVal("wW", parsedData));
        results.setBodyWaterPercent(parseDoubleVal("ww", parsedData));
        results.setBoneMass(parseDoubleVal("bW", parsedData));
        results.setFatFreeMass(parseDoubleVal("MW", parsedData));
        results.setFatMass(parseDoubleVal("fW", parsedData));
        results.setFatPercent(parseDoubleVal("FW", parsedData));
        results.setHeight(parseDoubleVal("Hm", parsedData));
        results.setMetabolicAge(parseIntVal("rA", parsedData));
        results.setMuscleMass(parseDoubleVal("mW", parsedData));
        results.setVisceralFat(parseIntVal("IF", parsedData));
        results.setWeight(parseDoubleVal("Wk", parsedData));
        results.setbMI(parseDoubleVal("MI", parsedData));
        results.seteCWMass(parseDoubleVal("wO", parsedData));
        results.seteCWPercent(parseDoubleVal("wo", parsedData));
        results.setiCWMass(parseDoubleVal("wI", parsedData));
        return results;
    }

    private double parseDoubleVal(String key, Map<String, String> parsedData) {
        return Double.parseDouble(parsedData.get(key));
    }
    
    private int parseIntVal(String key, Map<String, String> parsedData) {
        return Integer.parseInt(parsedData.get(key));                
    }
    
    private Date parseDate(String key, Map<String, String> parsedData) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(parsedData.get(key));
    }
}
