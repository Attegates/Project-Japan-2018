/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
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
        this.button.addClickListener(event -> addComponent(createThreeColourChart(22.0)));
        addComponent(text);
        addComponent(button);
    }

    private ChartJs createThreeColourChart(double result) {
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
        return chart;
    }

}
