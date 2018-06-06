/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI.layout;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import com.atteg.MeasurementPersistJPA.model.Results;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Atte
 */
public class MeasurementLayout extends VerticalLayout {

    private final ComboBox<Measurement> comboBox;
    private final List<Measurement> measurements;

    private final List<Button> buttons;

    private ButtonLayout buttonLayout;
    

    public MeasurementLayout(List<Measurement> measurements) {
        this.measurements = measurements;
        this.buttons = new ArrayList<>();
        this.buttonLayout = new ButtonLayout();
        comboBox = new ComboBox<>("Measurements");
        comboBox.setItemCaptionGenerator(Measurement::getIdDateString);
        comboBox.setItems(measurements);
        comboBox.addValueChangeListener(e -> {
            removeComponent(this.buttonLayout);
            buttonLayout.updateButtons(e.getValue());
            addComponent(buttonLayout);
        });
        addComponent(comboBox);

    }

    private class ButtonLayout extends VerticalLayout {
        
        private void updateButtons(Measurement m) {
            removeAllComponents();
            if (m == null) {
                return;
            }

            buttons.clear();
            Results r = m.getResults();
            Button weight = new Button();
            Button fatPercent = new Button();
            Button muscleMass = new Button();
            Button boneMass = new Button();
            Button bMI = new Button();
            Button visceralFat = new Button();
            Button metabolicAge = new Button();
            Button waterMass = new Button();
            weight.setCaption("" + r.getWeight());
            buttons.addAll(Arrays.asList(weight, fatPercent, muscleMass,
                    boneMass, bMI, visceralFat,
                    metabolicAge, waterMass));
            addComponents(buttons.toArray(new Button[buttons.size()]));
        }
    }

    // Luo napit
    // Lisää eventlistenerit (tyhjennä chart/info, piirrä uusi).
    /*
    Weight
    Fat %
    Muscle mass
    Bone mass
    BMI
    Visceral fat
    Metabolic age
    Water mass
    Limbs (yks iso kuva jossa eritelty raajojen rasva %, (rasvamassa?) ja lihasmassa)
     */
}
