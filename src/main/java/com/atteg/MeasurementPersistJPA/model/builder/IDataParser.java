/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.model.builder;

import java.util.Map;

/**
 *
 * @author Atte
 */
public interface IDataParser {
    public Map<String, String> parseData(String s);
}
