/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.chart.ChartBuilder;
import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Attegates
 */
public class TimelineLayout extends VerticalLayout {

    private final ComboBox<ResultOption> comboBox;
    private final List<Measurement> measurements;
    private final ChartBuilder chartBuilder;

    private final Map<ResultOption, String> captionMap;

    private final HorizontalLayout content;
    private final Panel chartPanel;

    public TimelineLayout(List<Measurement> measurements, ChartBuilder chartBuilder) {
        this.comboBox = new ComboBox<>();
        this.measurements = measurements;
        this.chartBuilder = chartBuilder;
        this.captionMap = initMap();
        this.content = new HorizontalLayout();
        this.content.setSizeFull();
        this.chartPanel = new Panel();
        
        comboBox.setItems(ResultOption.values());
        ItemCaptionGenerator<ResultOption> captionGenerator = (ResultOption r) -> captionMap.get(r);
        comboBox.setItemCaptionGenerator(captionGenerator);
        comboBox.setTextInputAllowed(false);

        comboBox.addValueChangeListener(e -> {
            if (e.getValue() == null) {
                content.removeComponent(chartPanel);
                return;
            }
            ChartJs chart = chartBuilder.timelineChart(measurements, e.getValue(), captionMap.get(e.getValue()));
            setChartToContent(chart);
        });

        content.addComponent(comboBox);

        addComponent(content);
    }

    private void setChartToContent(ChartJs chart) {
        content.removeComponent(chartPanel);
        chart.setWidth("90%");
        chartPanel.setContent(chart);
        content.addComponent(chartPanel);

        content.setExpandRatio(comboBox, 1.0f);
        content.setExpandRatio(chartPanel, 2.0f);
    }

    private Map<ResultOption, String> initMap() {
        Map<ResultOption, String> map = new HashMap<>();
        map.put(ResultOption.BMI, "BMI");
        map.put(ResultOption.BONEMASS, "Bone Mass");
        map.put(ResultOption.FATPERCENT, "Fat Percent");
        map.put(ResultOption.METABOLICAGE, "Metabolic Age");
        map.put(ResultOption.MUSCLEMASS, "Muscle Mass");
        map.put(ResultOption.VISCERALFAT, "Visceral Fat");
        map.put(ResultOption.WATERPERCENT, "Water Percent");
        map.put(ResultOption.WEIGHT, "Weight");

        return map;
    }

}
