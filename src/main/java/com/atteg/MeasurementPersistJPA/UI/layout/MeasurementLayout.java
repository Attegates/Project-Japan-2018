/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Results;
import com.atteg.MeasurementPersistJPA.model.Sex;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Atte
 */
public class MeasurementLayout extends VerticalLayout {

    private final ComboBox<Measurement> comboBox;
    private final List<Measurement> measurements;
    private final ChartBuilder chartBuilder;

    private final Panel panel;

    private final VerticalLayout buttonLayout;

    /**
     * Used to align the buttonlayout and the chart side by side.
     */
    private final HorizontalLayout content;

    public MeasurementLayout(List<Measurement> measurements, ChartBuilder chartBuilder) {
        this.measurements = measurements;
        this.chartBuilder = chartBuilder;
        this.buttonLayout = new VerticalLayout();

        this.content = new HorizontalLayout();
        this.content.setSizeFull();

        this.panel = new Panel();

        comboBox = new ComboBox<>("Measurements");
        comboBox.setItemCaptionGenerator(Measurement::getIdDateString);
        comboBox.setItems(measurements);
        comboBox.setTextInputAllowed(false);
        comboBox.addValueChangeListener(e -> {
            removeComponent(this.content);
            this.content.removeAllComponents();
            updateButtons(e.getValue());
            content.addComponent(buttonLayout);
            addComponent(content);
        });
        addComponent(comboBox);
    }

    private void updateButtons(Measurement m) {
        buttonLayout.removeAllComponents();

        // If no measurement is chosen don't update buttons.
        if (m == null) {
            return;
        }

        Results r = m.getResults();
        Button weight = new Button("Weight: " + r.getWeight() + " kg");
        Button fatPercent = new Button("Fat percent: " + r.getFatPercent() + " %");
        Button muscleMass = new Button("Muscle mass: " + r.getMuscleMass() + " kg");
        Button boneMass = new Button("Bone mass: " + r.getBoneMass() + " kg");
        Button bMI = new Button("BMI: " + r.getbMI());
        Button visceralFat = new Button("Visceral fat level: " + r.getVisceralFat());
        Button metabolicAge = new Button("Metabolic age: " + r.getMetabolicAge());
        Button waterPercent = new Button("Total body water %: " + r.getBodyWaterPercent());

        visceralFat.addClickListener(e -> {
            setChartToContent(chartBuilder.visceralFatChart(r.getVisceralFat()));
        });
        fatPercent.addClickListener(e -> {
            setChartToContent(chartBuilder.fatPercentChart(r.getFatPercent(), r.getSex()));
        });
        waterPercent.addClickListener(e -> {
            setChartToContent(chartBuilder.waterPercentChart(r.getBodyWaterPercent(), r.getSex()));
        });
        bMI.addClickListener(e -> {
            setChartToContent(chartBuilder.bMIChart(r.getbMI()));
        });

        buttonLayout.addComponents(new Button[]{weight, fatPercent, muscleMass,
            boneMass, bMI, visceralFat, metabolicAge, waterPercent});
    }

    private void setChartToContent(ChartJs chart) {
        content.removeComponent(panel);
        chart.setWidth("90%");
        panel.setContent(chart);
        content.addComponent(panel);

        content.setExpandRatio(buttonLayout, 1.0f);
        content.setExpandRatio(panel, 2.0f);
    }

}
