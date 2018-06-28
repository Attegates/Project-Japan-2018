/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author attegates
 */
@SpringUI(path = AddMeasurementUI.PATH)
public class AddMeasurementUI extends UI {

    public final static String PATH = "/measurements/new";
    
    @Autowired
    AddMeasurementFormFactory addMeasurementFormFactory;
    
    @Override
    protected void init(VaadinRequest request) {
        setContent(addMeasurementFormFactory.createComponent());
    }
    
}
