/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.chart;

import com.atteg.MeasurementPersistJPA.UI.layout.ResultOption;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Sex;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.annotation.DrawTime;
import com.byteowls.vaadin.chartjs.options.annotation.LineLabel;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
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

    /**
     * Normal range 50 to 65.
     */
    private final double[] WATERPERCENTMALE = {50, 65 - 50, 100 - 65};
    private final int WATERMASSMALEMIDDLE = 50;

    /**
     * Normal range 45 to 60.
     */
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

    public ChartJs timelineChart(List<Measurement> measurements, ResultOption option, String label) {
        LineChartConfig lineConfig = new LineChartConfig();

        String[] labels = getLabelsForTimelineChart(measurements);
        Double[] data = getDataForTimelineChart(measurements, option);

        lineConfig.options()
                .responsive(true)
                .title()
                .display(true)
                .text(label)
                .and()
                .events()
                .scales()
                .add(Axis.X, new CategoryScale()
                        .display(true))
                .add(Axis.Y, new LinearScale()
                        .display(true)
                        .scaleLabel()
                        .display(true)
                        .labelString("")
                        .and()
                        .position(Position.LEFT))
                .and()
                .done();

        lineConfig.data()
                .labels(labels)
                .addDataset(
                        new LineDataset().
                                label("").
                                fill(false).
                                data(data).
                                backgroundColor(Colour.BLACK).
                                borderColor(Colour.BLACK).
                                lineTension(0))
                .and();

        ChartJs chart = new ChartJs(lineConfig);
        chart.setJsLoggingEnabled(true);
        return chart;
    }

    private Double[] getDataForTimelineChart(List<Measurement> measurements, ResultOption option) {
        // Ensure measurements is sorted (by date)
        measurements.sort(null);
        Double[] data = new Double[measurements.size()];

        switch (option) {
            case WEIGHT:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getWeight();
                }
                break;
            case FATPERCENT:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getFatPercent();
                }
                break;
            case MUSCLEMASS:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getMuscleMass();
                }
                break;
            case BONEMASS:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getBoneMass();
                }
                break;
            case BMI:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getbMI();
                }
                break;
            case VISCERALFAT:
                for (int i = 0; i < data.length; i++) {                    
                    data[i] = (double)measurements.get(i).getResults().getVisceralFat();
                }
                break;
            case METABOLICAGE:
                for (int i = 0; i < data.length; i++) {
                    data[i] = (double)measurements.get(i).getResults().getMetabolicAge();
                }
                break;
            case WATERPERCENT:
                for (int i = 0; i < data.length; i++) {
                    data[i] = measurements.get(i).getResults().getBodyWaterPercent();
                }
                break;
            default:
                return null;
        }
        return data;
    }

    private String[] getLabelsForTimelineChart(List<Measurement> measurements) {
        // Ensure measurements is sorted (by date)
        measurements.sort(null);
        String[] labels = new String[measurements.size()];

        for (int i = 0; i < labels.length; i++) {
            labels[i] = measurements.get(i).getDate().toString();
        }

        return labels;
    }
}
