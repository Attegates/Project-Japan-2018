/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.chart;

/**
 *
 * @author VStore
 */
public class ValueIntervals {

    /**
     * [12, 22, 27, 40]
     * 0 to 12 Underfat, 12 to 22 healthy, 22 to 27 overfat. 27+ obese
     */
    public static final double[] FATPERCENTMALE = {12, 22 - 12, 27 - 22, 40 - 27};

    /** [22, 33, 40, 55]
     * 0 to 22 Underfat, 22 to 33 healthy, 33 to 40 overfat. 40+ obese
     */
    public static final double[] FATPERCENTFEMALE = {22, 33 - 22, 40 - 33, 60 - 40};
    
    /**
     * 
     * 0 to 12 Healthy, 12 to 60 Excessive.
     */
    public static final double[] VISCERALFAT = {12, 60 - 12};

}
