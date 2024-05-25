package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends JFrame {
    // Declare buttons for customer and manager login options
    private JButton customerButton, managerButton;

    // Declare a panel to hold login fields
    private JPanel loginPanel;

    // Declare login button as an instance variable
    private JButton loginButton;

    // Flags to determine which login type is selected
    boolean isCustomerLogin = false;
    boolean isManagerLogin = false;

    // Define email-password combinations for customers and managers
    private static final String CUSTOMER_EMAIL_1 = "customer1@example.com";
    private static final String CUSTOMER_PASSWORD_1 = "customer1pass";
    private static final String CUSTOMER_EMAIL_2 = "customer2@example.com";
    private static final String CUSTOMER_PASSWORD_2 = "customer2pass";
    private static final String MANAGER_EMAIL_1 = "manager1@example.com";
    private static final String MANAGER_PASSWORD_1 = "manager1pass";
    private static final String MANAGER_EMAIL_2 = "manager2@example.com";
    private static final String MANAGER_PASSWORD_2 = "manager2pass";

    // Define email-password combinations for customers and managers
    private static final String CUSTOMER_EMAIL_1 = "customer1@example.com";
    private static final String CUSTOMER_PASSWORD_1 = "customer1pass";
    private static final String CUSTOMER_EMAIL_2 = "customer2@example.com";
    private static final String CUSTOMER_PASSWORD_2 = "customer2pass";
    private static final String MANAGER_EMAIL_1 = "manager1@example.com";
    private static final String MANAGER_PASSWORD_1 = "manager1pass";
    private static final String MANAGER_EMAIL_2 = "manager2@example.com";
    private static final String MANAGER_PASSWORD_2 = "manager2pass";

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

        // Initialize login button
        loginButton = new JButton("Login");
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

                // Check which login type is selected and validate credentials
                if (isCustomerLogin) {
                    if ((email.equals(CUSTOMER_EMAIL_1) && password.equals(CUSTOMER_PASSWORD_1)) ||
                            (email.equals(CUSTOMER_EMAIL_2) && password.equals(CUSTOMER_PASSWORD_2))) {
                        // Navigate to Customer screen
                        dispose(); // Close the login screen
                        List<Animal> sampleAnimals = new ArrayList<>();
                        sampleAnimals.add(new Animal("Buddy", "dog", 3));
                        sampleAnimals.add(new Animal("Mittens", "cat", 2));
                        sampleAnimals.add(new Animal("Goldie", "fish", 1));
                        new CustomerScreen(sampleAnimals, null).setVisible(true); // Pass null as manager screen for now
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password for customer.");
                    }
                } else if (isManagerLogin) {
                    if ((email.equals(MANAGER_EMAIL_1) && password.equals(MANAGER_PASSWORD_1)) ||
                            (email.equals(MANAGER_EMAIL_2) && password.equals(MANAGER_PASSWORD_2))) {
                        // Navigate to Manager screen
                        dispose(); // Close the login screen
                        new ManagerScreen().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password for manager.");
                    }
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

    // Method to return the login button
    public JButton getLoginButton() {
        return loginButton;
    }
}
