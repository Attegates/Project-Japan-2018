/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Attegates
 */
public class LogoutButton extends Button {

    public LogoutButton() {
        super("Logout");
        this.addClickListener(e -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().getPage().setLocation(LoginUI.PATH);
        });
    }

}
