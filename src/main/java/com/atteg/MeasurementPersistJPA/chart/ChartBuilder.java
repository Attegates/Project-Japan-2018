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

    /**
     * [12, 22, 27, 40] 0 to 12 Underfat, 12 to 22 healthy, 22 to 27 overfat.
     * 27+ obese
     */
    private static final double[] FATPERCENTMALE = {12, 22 - 12, 27 - 22, 40 - 27};
    private static final int FATPERCENTMALEMIDDLE = 20;

    /**
     * [22, 33, 40, 55] 0 to 22 Underfat, 22 to 33 healthy, 33 to 40 overfat.
     * 40+ obese
     */
    private static final double[] FATPERCENTFEMALE = {22, 33 - 22, 40 - 33, 60 - 40};
    private static final int FATPERCENTFEMALEMIDDLE = 30;

    /**
     *
     * 0 to 12 Healthy, 12 to 60 Excessive.
     */
    private static final double[] VISCERALFAT = {12, 60 - 12};
    private static final int VISCERALFATMIDDLE = 30;

    /**
     * Less than 18.5 underweight, 18.5 to 25 normal, 25 to 30 overweight, 30+
     * obese.
     */
    private final double[] BMI = {18.5, 25 - 18.5, 30 - 25, 50 - 30};
    private final int BMIMIDDLE = 25;

    private final double[] WATERPERCENTMALE = {50, 65 - 50, 100 - 65};
    private final int WATERMASSMALEMIDDLE = 50;

    private final double[] WATERPERCENTFEMALE = {45, 60 - 45, 100 - 60};
    private final int WATERMASSFEMALEMIDDLE = 50;

    private BarChartConfig baseConfig(double result, int middle) {
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
                .content("Result: " + result)
                .position(LineLabel.Position.bottom).xAdjust(result > middle ? 40 : -40) // Adjust to right or left
                .and()
                .and()
                .and()
                .events() // No events
                .done();

        return barConfig;
    }

    public ChartJs visceralFatChart(double result) {
        BarChartConfig barConfig = this.baseConfig(result, VISCERALFATMIDDLE);

        barConfig.
                data()
                .labels("Level")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN).label("Healthy").data(VISCERALFAT[0]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("Excessive").data(VISCERALFAT[1]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        return chart;
    }

    public ChartJs fatPercentChart(double result, Sex sex) {
        BarChartConfig barConfig = this.baseConfig(result, sex == Sex.MALE ? FATPERCENTMALEMIDDLE : FATPERCENTFEMALEMIDDLE);
        barConfig.horizontal();

        double[] intervals = sex == Sex.MALE ? FATPERCENTMALE : FATPERCENTFEMALE;

        barConfig.
                data()
                .labels("Percent")
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
        return chart;
    }

    public ChartJs waterPercentChart(double result, Sex sex) {
        BarChartConfig barConfig = this.baseConfig(result, sex == Sex.MALE ? WATERMASSMALEMIDDLE : WATERMASSFEMALEMIDDLE);

        double[] arr = sex == Sex.MALE ? WATERPERCENTMALE : WATERPERCENTFEMALE;

        barConfig.
                data()
                .labels("Percent")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("Abnormal").data(arr[0]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN).label("Normal").data(arr[1]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("Abnormal").data(arr[2]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        return chart;
    }

    public ChartJs bMIChart(double result) {
        BarChartConfig barConfig = this.baseConfig(result, BMIMIDDLE);

        barConfig.
                data()
                .labels("BMI")
                .addDataset(
                        new BarDataset().backgroundColor(Colour.LBLUE).label("Underweight").data(BMI[0]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.GREEN).label("Normal").data(BMI[1]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.YELLOW).label("Overweight").data(BMI[2]))
                .addDataset(
                        new BarDataset().backgroundColor(Colour.RED).label("Obese").data(BMI[3]))
                .and();

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        return chart;
    }

    /*
    public ChartJs timelineChart(List<Measurement> measurements, )
     */
}
