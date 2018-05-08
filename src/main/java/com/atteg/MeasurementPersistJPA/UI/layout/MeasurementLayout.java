/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.model.Measurement;
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

    public MeasurementLayout() {
        
        text = new TextField();
        text.setCaption("aaaaaaaaaa");
        
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        
        addComponent(text);

    }

}
