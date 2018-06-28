/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.User;
import com.atteg.MeasurementPersistJPA.model.builder.DataParser;
import com.atteg.MeasurementPersistJPA.model.builder.MeasurementBuilder;
import com.atteg.MeasurementPersistJPA.repository.MeasurementRepository;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author attegates
 */
@org.springframework.stereotype.Component
public class AddMeasurementFormFactory {
    
    @Autowired
    MeasurementRepository measurementRepository;

    private class MeasurementForm {

        private VerticalLayout root;
        private Panel panel;
        private TextField measurementStringField;
        private Button addButton;

        private MeasurementForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");
            panel = new Panel("Add new measurement");
            panel.setSizeUndefined();
            measurementStringField = new TextField("String from the device");
            addButton = new Button("Add new measurement");
            addButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            return this;
        }
        
        private Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            
            FormLayout form = new FormLayout();
            form.addComponents(measurementStringField, addButton);
            addButton.addClickListener(e -> {
                // DOES NOT CHECK THE STRING AT ALL
                Measurement m = new MeasurementBuilder(new DataParser()).buildMeasurement(measurementStringField.getValue());
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                m.setUser(user);
                measurementRepository.save(m);
                UI.getCurrent().getPage().setLocation(MeasurementUI.PATH);
            });
            panel.setContent(form);
            
            return root;
        }
    }

    public Component createComponent() {

        return new MeasurementForm().init().layout();
    }

}
