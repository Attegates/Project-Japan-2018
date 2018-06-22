package com.atteg.MeasurementPersistJPA.UI.View;

import com.atteg.MeasurementPersistJPA.UI.LoginUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addHeader();
        Link lnk = new Link("MeasurementView", new ExternalResource("#!" + MeasurementView.NAME));
        Link lnktest = new Link("TestView", new ExternalResource("#!" + TestView.NAME));

        addComponent(lnk);
        addComponent(lnktest);

        Button logout = new Button("Logout");
        logout.addClickListener(e -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().getPage().setLocation(LoginUI.PATH);
        });
    }

    private void addHeader() {
        Label header = new Label("Measurements");
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
