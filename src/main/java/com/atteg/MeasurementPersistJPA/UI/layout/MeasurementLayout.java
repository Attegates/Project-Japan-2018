/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.UI.View.MeasurementView;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.vaadin.data.Binder;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Atte
 */
public class MeasurementLayout extends HorizontalLayout {

    //private final TextField text;
    private final Link link;

    public MeasurementLayout(Measurement measurement) {
        link = new Link(measurement.getIdDateString(), new ExternalResource("#!" + MeasurementView.NAME + "/" + measurement.getId()));
        
        //text = new TextField();
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        //text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        //text.setReadOnly(true);

        //Binder<Measurement> binder = new Binder<>();
        //binder.forField(text).bind(Measurement::getIdDateString, null);
        //binder.setBean(measurement);

        //addComponentsAndExpand(text);
        addComponentsAndExpand(link);

    }

}
