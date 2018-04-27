/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.model.builder;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atte
 */
@Component
public class DataParser implements IDataParser {

    @Override
    public Map<String, String> parseData(String dataString) {
        Map<String, String> parsedValues = new HashMap<>();
        String s = dataString;
        s = s.replaceAll("\"", "");
        String[] arr = s.split(",");
        
        for (int i = 0; i < arr.length - 1; i += 2) {
            parsedValues.put(arr[i], arr[i + 1]);
        }
        
        return parsedValues;
    }

}
