/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Atte
 */
//@UIScope
//@SpringComponent
public class TestLayout extends VerticalLayout {
    private final Button button;
    private final TextField text;

    public TestLayout() {
        this.button = new Button();
        this.text = new TextField();
        
        text.addContextClickListener(event -> removeComponent(this.text));
        
        
        text.setValue("aaa");
        this.button.addClickListener(event -> removeComponent(this.text));
        addComponent(text);
        addComponent(button);
    }
    
    
}
