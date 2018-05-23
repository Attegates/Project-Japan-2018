/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atte
 */
@UIScope
@SpringComponent
public class MeasurementList extends VerticalLayout {

    @Autowired
    MeasurementRepository repository;

    private List<Measurement> measurements;
    
    @PostConstruct
    void init() {
        setWidth("80%");
        update();
    }

    private void update() {
        setMeasurements(repository.findAll());
    }

    private void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
        removeAllComponents();
        measurements.forEach(measurement -> {
            MeasurementLayout measurementLayout = new MeasurementLayout(measurement);
            /*
            measurementLayout.addLayoutClickListener(e -> {
                System.out.println(measurement.getId());
            });
            */
            addComponent(measurementLayout);
        });
    }

}
