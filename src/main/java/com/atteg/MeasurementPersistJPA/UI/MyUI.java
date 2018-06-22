/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.UI.View.MainView;
import com.atteg.MeasurementPersistJPA.UI.View.TestView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atte
 */
@SpringUI(path = MyUI.PATH)
@Theme("valo")
public class MyUI extends UI {

    public static final String PATH = "/main";
    
    Navigator navigator;
    
    @Autowired
    SpringViewProvider viewProvider;
    
    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.addView(MainView.NAME, MainView.class);
        navigator.addView(TestView.NAME, TestView.class);
    }
}
