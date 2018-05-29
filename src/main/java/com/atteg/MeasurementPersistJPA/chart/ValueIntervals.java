/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.chart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author VStore
 */
public class ValueIntervals {

    /**
     * [12, 22, 27, 40]
     * 0 - 12 Underfat, 12 - 22 healthy, 22 - 27 overfat. 27 -> obese
     */
    public static final double[] FATPERCENTMALE = {12, 22 - 12, 27 - 22, 40 - 27};

    /** [22, 33, 40, 55]
     * 0 - 22 Underfat, 22 - 33 healthy, 33 - 40 overfat. 40 -> obese
     */
    public static final double[] FATPERCENTFEMALE = {22, 33, 40, 55};

}
