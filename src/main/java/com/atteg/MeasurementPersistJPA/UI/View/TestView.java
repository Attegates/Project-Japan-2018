/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.View;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Attegates
 */
@SpringView(name = TestView.NAME)
public class TestView extends VerticalLayout implements View{
    
    public static final String NAME = "TESTVIEW"; 
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
    
}
