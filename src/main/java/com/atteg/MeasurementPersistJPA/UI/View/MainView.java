package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.UI.layout.MeasurementList;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Atte
 */
@SpringView(name = MainView.NAME)
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "";

    @Autowired
    MeasurementList measurementList;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addHeader();
        addMeasurementList();
        Link lnk = new Link("MeasurementView", new ExternalResource("#!" + MeasurementView.NAME));

        addComponent(lnk);
    }

    private void addHeader() {
        Label header = new Label("Measurements");
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);
    }

    private void addMeasurementList() {
        addComponent(measurementList);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
