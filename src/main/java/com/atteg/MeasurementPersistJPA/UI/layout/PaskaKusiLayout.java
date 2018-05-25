/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.model.LeftArm;
import com.atteg.MeasurementPersistJPA.model.LeftLeg;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Results;
import com.atteg.MeasurementPersistJPA.model.RightArm;
import com.atteg.MeasurementPersistJPA.model.RightLeg;
import com.atteg.MeasurementPersistJPA.model.Torso;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Atte
 */
public class PaskaKusiLayout extends VerticalLayout {
    
    private final Measurement m;
    private final Results r;
    private final LeftArm la;
    private final LeftLeg ll;
    private final RightArm ra;
    private final RightLeg rl;
    private final Torso t;
    private final StringBuilder sb;
    
    public PaskaKusiLayout(Measurement m) {
        this.m = m;
        this.r = m.getResults();
        this.la = m.getLeftArm();
        this.ll = m.getLeftLeg();
        this.ra = m.getRightArm();
        this.rl = m.getRightLeg();
        this.t = m.getTorso();
        this.sb = new StringBuilder();
        Button btn = new Button();
        btn.setCaption("Age: " + r.getAge());
        Button btn2 = new Button();
        btn2.setCaption("Visceral fat: " + r.getAge());
        addComponents(btn, btn2);
    }
    
}
