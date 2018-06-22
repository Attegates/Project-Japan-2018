/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.UI.LogoutButton;
import com.atteg.MeasurementPersistJPA.UI.layout.MeasurementLayout;
import com.atteg.MeasurementPersistJPA.UI.layout.TimelineLayout;
import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Atte
 */
@SpringView(name = MeasurementView.NAME)
public class MeasurementView extends VerticalLayout implements View {

    public static final String NAME = "measurements";

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    ChartBuilder chartBuilder;

    ComboBox<Measurement> comboBox;

    private List<Measurement> measurements;

    /**
     * Content changes to MeasurementLayout or TimelineLayout depending on which
     * is chosen from the radiobutton group.
     */
    AbstractOrderedLayout content;

    private enum Option {
        Single, Timeline
    };

    RadioButtonGroup<Option> group;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.measurements = measurementRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        group = new RadioButtonGroup<>();
        group.setItems(Option.Single, Option.Timeline);
        addComponent(group);
        
        addComponent(new LogoutButton());

        group.addValueChangeListener(e -> {
            if (e.getValue() == Option.Single) {
                ValueChangeSingle();
            } else if (e.getValue() == Option.Timeline) {
                ValueChangeTimeline();
            }
        });
    }

    private void ValueChangeSingle() {
        if (this.content != null) {
            removeComponent(content);
        }
        this.content = new MeasurementLayout(measurements, chartBuilder);
        addComponent(content);
    }

    private void ValueChangeTimeline() {
        if (this.content != null) {
            removeComponent(content);
        }
        this.content = new TimelineLayout(measurements, chartBuilder);
        addComponent(content);
    }

}
