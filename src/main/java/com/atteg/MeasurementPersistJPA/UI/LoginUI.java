/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Attegates
 */
@SpringUI(path = LoginUI.PATH)
public class LoginUI extends UI {
    public static final String PATH = "/login";
    
    @Autowired
    LoginFormFactory loginFormFactory;

    @Override
    protected void init(VaadinRequest request) {
        setContent(loginFormFactory.createComponent());
    }
    
    
}
