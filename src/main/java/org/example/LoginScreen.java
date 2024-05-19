package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    // Declare buttons for customer and manager login options
    private JButton customerButton, managerButton;

    // Declare a panel to hold login fields
    private JPanel loginPanel;

    // Flags to determine which login type is selected
    private boolean isCustomerLogin = false;
    private boolean isManagerLogin = false;

    // Constructor to set up the login screen
    public LoginScreen() {
        // Set the title of the frame
        setTitle("Login Application");

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(400, 250);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create a panel for the customer and manager buttons
        JPanel buttonPanel = new JPanel();

        // Use FlowLayout to arrange buttons in the center with horizontal and vertical gaps
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // Initialize buttons
        customerButton = new JButton("Customer");
        managerButton = new JButton("Manager");

        // Add buttons to the button panel
        buttonPanel.add(customerButton);
        buttonPanel.add(managerButton);

        // Add the button panel to the north (top) of the frame
        add(buttonPanel, BorderLayout.NORTH);

        // Create a panel for the login form
        loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Set a border for the login panel with empty space around it
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create labels and fields for email and password
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to the login panel
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label to align the login button
        loginPanel.add(loginButton);

        // Initially hide the login panel until a login type is selected
        loginPanel.setVisible(false);

        // Add the login panel to the center of the frame
        add(loginPanel, BorderLayout.CENTER);

        // Add action listener to customer button
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Set flags for customer login and show the login panel
                isCustomerLogin = true;
                isManagerLogin = false;
                loginPanel.setVisible(true);
            }
        });

        // Add action listener to manager button
        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Set flags for manager login and show the login panel
                isManagerLogin = true;
                isCustomerLogin = false;
                loginPanel.setVisible(true);
            }
        });

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get email and password from the text fields
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Check which login type is selected and display appropriate message
                if (isCustomerLogin) {
                    JOptionPane.showMessageDialog(null, "Logged in as Customer\nEmail: " + email + "\nPassword: " + password);
                    // Navigate to Customer screen
                } else if (isManagerLogin) {
                    JOptionPane.showMessageDialog(null, "Logged in as Manager\nEmail: " + email + "\nPassword: " + password);
                    // Navigate to Manager screen
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a login type (Customer or Manager).");
                }
            }
        });
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and display the login screen
                new LoginScreen().setVisible(true);
            }
        });
    }
}
