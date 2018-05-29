/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.chart;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atte
 */
@Component
public class ChartBuilder {
    

    private BarChartConfig baseConfig(double result) {
        BarChartConfig barConfig = new BarChartConfig();

        barConfig.horizontal();

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

        return barConfig;
    }

    public ChartJs createThreeColourChart(double result) {
        BarChartConfig barConfig = this.baseConfig(result);
        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.LBLUE).label("Low").data(20.0))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN).label("Normal").data(5.0))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("High").data(10.0))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        return chart;
    }

    public ChartJs fatPercentChart(double result) {
        BarChartConfig barConfig = this.baseConfig(result);
        barConfig.horizontal();
        
        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.LBLUE).
                                label("Underfat").
                                data(ValueIntervals.FATPERCENTMALE[0]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.GREEN).
                                label("Healthy").
                                data(ValueIntervals.FATPERCENTMALE[1]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.YELLOW).
                                label("Overfat").
                                data(ValueIntervals.FATPERCENTMALE[2]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.RED).
                                label("Obese").
                                data(ValueIntervals.FATPERCENTMALE[3]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        //chart.setHeight("50%");
        //chart.setSizeFull();
        return chart;
    }

}
