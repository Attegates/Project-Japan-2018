/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.UI.layout.Colour;
import com.atteg.MeasurementPersistJPA.UI.layout.MeasurementList;
import com.atteg.MeasurementPersistJPA.UI.layout.TestLayout;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.BaseScale;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Atte
 */
@SpringUI
@Theme("valo")
public class MyUI extends UI {
    
    //Navigator navigator;

    private VerticalLayout layout;

    @Autowired
    MeasurementList measurementList;

    @Override
    protected void init(VaadinRequest request) {
        setupLayout();
        addHeader();
        addMeasurementList();
        //this.createChart();
        //createThreeColourChart(22.0);
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("Measurements");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);

    }

    private void addMeasurementList() {
        layout.addComponent(measurementList);
    }

    private void createChart() {

        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();

        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().backgroundColor("#6ec1f4").label("Low").data(20.0))
                .addDataset(
                        new BarDataset().backgroundColor("#2ed352").label("Normal").data(5.0))
                .addDataset(
                        new BarDataset().backgroundColor("#d22d2d").label("High").data(10.0))
                .and();

        barConfig.
                options()
                .responsive(true)
                .scales()
                .add(Axis.X, new DefaultScale()
                        .stacked(true)
                        .barThickness(100))
                .add(Axis.Y, new DefaultScale()
                        .stacked(true)
                        .barThickness(100))
                .and()
                .annotation()
                .line()
                .vertical()
                .xAxis0ScaleID()
                .borderColor("#000000")
                .borderWidth(5)
                .drawTime(DrawTime.afterDraw)
                .value(22.0)
                .label()
                .backgroundColor("#000000")
                .fontColor("#ffffff")
                .content("Result")
                .position(LineLabel.Position.bottom)
                .and()
                .and()
                .and()
                .events()
                .done();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        //chart.setHeight("50%");
        //chart.setSizeFull();
        layout.addComponent(chart);
    }

    private void createThreeColourChart(double result) {
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.BLUE.getHexCode()).label("Low").data(20.0))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN.getHexCode()).label("Normal").data(5.0))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED.getHexCode()).label("High").data(10.0))
                .and();

        barConfig.
                options()
                .responsive(true)
                .scales()
                .add(Axis.X, new DefaultScale()
                        .stacked(true)
                        .barThickness(100))
                .add(Axis.Y, new DefaultScale()
                        .stacked(true)
                        .barThickness(100))
                .and()
                .annotation()
                .line()
                .vertical()
                .xAxis0ScaleID()
                .borderColor("#000000")
                .borderWidth(5)
                .drawTime(DrawTime.afterDraw)
                .value(result)
                .label()
                .backgroundColor("#000000")
                .fontColor("#ffffff")
                .content("Result")
                .position(LineLabel.Position.bottom)
                .and()
                .and()
                .and()
                .events()
                .done();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        //chart.setHeight("50%");
        //chart.setSizeFull();
        layout.addComponent(chart);
    }

}
