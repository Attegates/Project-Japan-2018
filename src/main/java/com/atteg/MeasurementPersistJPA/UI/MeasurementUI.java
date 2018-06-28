 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author attegates
 */
@SpringUI(path = MeasurementUI.PATH)
public class MeasurementUI extends UI {
    public final static String PATH = "/measurements";
    
    @Autowired
    MeasurementUIFactory measurementUIFactory;

    @Override
    protected void init(VaadinRequest request) {
        setContent(measurementUIFactory.createComponent());
    }
    
    
}
