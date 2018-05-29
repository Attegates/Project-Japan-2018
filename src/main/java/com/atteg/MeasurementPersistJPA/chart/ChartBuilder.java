/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.chart;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Sex;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import java.util.List;
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

    public ChartJs visceralFatChart(double result) {
        BarChartConfig barConfig = this.baseConfig(result);
        
        double[] intervals = ValueIntervals.VISCERALFAT;
        
        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN).label("Healthy").data(intervals[0]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("Excessive").data(intervals[1]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        return chart;
    }

    public ChartJs fatPercentChart(double result, Sex sex) {
        BarChartConfig barConfig = this.baseConfig(result);
        barConfig.horizontal();
        
        double[] intervals = sex == Sex.MALE ? ValueIntervals.FATPERCENTMALE : ValueIntervals.FATPERCENTFEMALE; 
        
        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.LBLUE).
                                label("Underfat").
                                data(intervals[0]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.GREEN).
                                label("Healthy").
                                data(intervals[1]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.YELLOW).
                                label("Overfat").
                                data(intervals[2]))
                .addDataset(
                        new BarDataset().
                                backgroundColor(Colour.RED).
                                label("Obese").
                                data(intervals[3]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("60%");
        //chart.setHeight("50%");
        //chart.setSizeFull();
        return chart;
    }
    
    /*
    public ChartJs timelineChart(List<Measurement> measurements, )
    */
}
