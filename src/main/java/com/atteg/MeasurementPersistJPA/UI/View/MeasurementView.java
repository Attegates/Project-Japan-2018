/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.UI.layout.PaskaKusiLayout;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Results;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atte
 */
@SpringView(name = MeasurementView.NAME)
public class MeasurementView extends VerticalLayout implements View {

    public static final String NAME = "measurement";

    @Autowired
    MeasurementRepository measurementRepository;

    private Measurement measurement;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponent(new Label("drhhafaeeryeryreyaery"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Should have 1 and only 1 parameter that is the measurement id.
        if (event.getParameters() != null && isLong(event.getParameters())) {
            Long id = Long.parseLong(event.getParameters());
            Optional<Measurement> m = measurementRepository.findById(id);

            measurement = m.get();
            

            addComponent(new PaskaKusiLayout(measurement));
        }

    }

    private boolean isLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
