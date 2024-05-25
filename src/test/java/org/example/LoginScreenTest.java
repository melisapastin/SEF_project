package org.example;

import org.example.LoginScreen;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginScreenTest {

    @Test
    public void testCustomerLogin() {
        LoginScreen loginScreen = new LoginScreen();
        JTextField emailField = new JTextField("customer1@example.com");
        JPasswordField passwordField = new JPasswordField("customer1pass");
        loginScreen.isCustomerLogin = true;
        loginScreen.isManagerLogin = false;

        // Simulate button click
        loginScreen.getLoginButton().doClick();
    }

    @Test
    public void testManagerLogin() {
        LoginScreen loginScreen = new LoginScreen();
        JTextField emailField = new JTextField("manager1@example.com");
        JPasswordField passwordField = new JPasswordField("manager1pass");
        loginScreen.isCustomerLogin = false;
        loginScreen.isManagerLogin = true;

        // Simulate button click
        loginScreen.getLoginButton().doClick();
    }
}
