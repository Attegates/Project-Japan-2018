/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

/**
 *
 * @author Atte
 */
public enum Colour {
    GREEN("#2ff72b"),
    BLUE("#70c7f9"),
    RED("#ff3d3d"),
    YELLOW("#f7f32c");
    
    private final String hexCode;
    
    public String getHexCode() {
        return this.hexCode;
    }
    
    private Colour(String hexCode) {
        this.hexCode = hexCode;
    }
    
}
