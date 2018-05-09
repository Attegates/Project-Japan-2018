/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Atte
 */
public class MeasurementLayout extends HorizontalLayout {

    private final TextField text;

    public MeasurementLayout(Measurement measurement) {

        text = new TextField();
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        text.setReadOnly(true);
        
        Binder<Measurement> binder = new Binder<>();
        binder.forField(text).bind(Measurement::toString, null);
        binder.setBean(measurement);
        
        
        //addComponent(text);
        addComponentsAndExpand(text);

    }

}
