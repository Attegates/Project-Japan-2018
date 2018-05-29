/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atte
 */
@SpringView(name = MeasurementView.NAME)
public class MeasurementView extends VerticalLayout implements View {

    public static final String NAME = "measurements";

    @Autowired
    MeasurementRepository measurementRepository;
    
    @Autowired ChartBuilder chartBuilder;

    ComboBox<Measurement> comboBox;

    private List<Measurement> measurements;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponent(new Label("drhhafaeeryeryreyaery"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        comboBox = new ComboBox<>("Measurements");
        this.measurements = measurementRepository.findAll();
        comboBox.setItemCaptionGenerator(Measurement::getIdDateString);
        comboBox.setItems(measurements);
        addComponent(comboBox);
        addComponent(chartBuilder.fatPercentChart(measurements.get(0).getResults().getFatPercent()));

    }

}
