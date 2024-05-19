package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JButton customerButton, managerButton;
    private JPanel loginPanel;
    private boolean isCustomerLogin = false;
    private boolean isManagerLogin = false;

    public LoginScreen() {
        setTitle("Login Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        customerButton = new JButton("Customer");
        managerButton = new JButton("Manager");
        buttonPanel.add(customerButton);
        buttonPanel.add(managerButton);
        add(buttonPanel, BorderLayout.NORTH);

        loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label to align the login button
        loginPanel.add(loginButton);
        loginPanel.setVisible(false);
        add(loginPanel, BorderLayout.CENTER);

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isCustomerLogin = true;
                isManagerLogin = false;
                loginPanel.setVisible(true);
            }
        });

        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isManagerLogin = true;
                isCustomerLogin = false;
                loginPanel.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
}
