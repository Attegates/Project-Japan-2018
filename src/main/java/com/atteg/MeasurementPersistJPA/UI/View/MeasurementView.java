/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
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
        System.out.println("init");
        //System.out.println(measurementRepository.count());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("enter");
        System.out.println(measurementRepository.count());

    }

}
