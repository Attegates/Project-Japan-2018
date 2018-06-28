/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.UI;

import com.atteg.MeasurementPersistJPA.security.RegisterUserService;
import com.atteg.MeasurementPersistJPA.security.UsernameExistsException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Attegates
 */
@org.springframework.stereotype.Component
public class SignupFormFactory {

    @Autowired
    RegisterUserService registerUserService;

    private class SignupForm {

        private VerticalLayout root;
        private Panel panel;

        private TextField username;
        private PasswordField password;
        private PasswordField passwordAgain;
        private Button saveButton;

        public SignupForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel("Signup");
            panel.setSizeUndefined();

            saveButton = new Button("Sign Up");
            saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            saveButton.setClickShortcut(KeyCode.ENTER);

            username = new TextField("Username");
            password = new PasswordField("Password");
            passwordAgain = new PasswordField("Password again");

            /*
            Currently only checks that passwords match
            and username does not already exist.
             */
            saveButton.addClickListener(event -> {
                if (!password.getValue().equals(passwordAgain.getValue())) {
                    Notification.show("Error", "Passwords do not match!", Notification.Type.ERROR_MESSAGE);
                    password.clear();
                    passwordAgain.clear();
                    return;
                }
                try {
                    registerUserService.save(username.getValue(), password.getValue());
                    UI.getCurrent().getPage().setLocation(LoginUI.PATH);
                } catch (UsernameExistsException ex) {
                    Notification.show("Error", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            });

            return this;
        }

        private Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(password);
            signupLayout.addComponent(passwordAgain);

            signupLayout.addComponent(saveButton);

            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);

            panel.setContent(signupLayout);

            return root;

        }

    }

    public Component createComponent() {
        return new SignupForm().init().layout();
    }

}
