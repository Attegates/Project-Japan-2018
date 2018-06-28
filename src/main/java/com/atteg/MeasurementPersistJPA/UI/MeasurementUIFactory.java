/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.UI.layout.MeasurementLayout;
import com.atteg.MeasurementPersistJPA.UI.layout.TimelineLayout;
import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author attegates
 */
@org.springframework.stereotype.Component
public class MeasurementUIFactory {

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    ChartBuilder chartBuilder;
    
    List<Measurement> measurements;

    private enum Option {
        Single, Timeline
    };

    private class Measurementview {

        private VerticalLayout root;
        private Panel topPanel;
        private RadioButtonGroup<Option> radioGroup;
        private Button add;
        private LogoutButton logout;
        private Panel contentPanel;

        public Measurementview init() {
            root = new VerticalLayout();
            root.setMargin(true);

            topPanel = new Panel();
            contentPanel = new Panel();

            radioGroup = new RadioButtonGroup<>();
            radioGroup.setItems(Option.Single, Option.Timeline);
            radioGroup.addValueChangeListener(e -> {
               if (e.getValue() == Option.Single) {
                   contentPanel.setContent(new MeasurementLayout(measurements, chartBuilder));
               } else if (e.getValue() == Option.Timeline) {
                   contentPanel.setContent(new TimelineLayout(measurements, chartBuilder));
               }
            });

            add = new Button("Add new");
            add.addClickListener(e -> {
                UI.getCurrent().getPage().setLocation(AddMeasurementUI.PATH);
            });
            logout = new LogoutButton();
            
            measurements = measurementRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            
            return this;
        }

        public Component layout() {
            root.addComponent(topPanel);
            topPanel.setContent(new HorizontalLayout(radioGroup, add, logout));
            root.addComponent(contentPanel);
            return root;
        }
    }

    public Component createComponent() {
        return new Measurementview().init().layout();
    }

}
