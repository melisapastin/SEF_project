package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerScreen extends JFrame {

    // Constructor to set up the manager screen
    public ManagerScreen() {
        // Set the title of the frame
        setTitle("Animal Shelter Manager Screen");

        // Set the size of the frame
        setSize(400, 300);

        // Specify the close operation for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();

        // Set a grid layout with 2 rows and 2 columns, and gaps between components
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        // Create buttons for different actions
        JButton addAnimalButton = new JButton("Add Animal");
        JButton editAnimalButton = new JButton("Edit Animal");
        JButton deleteAnimalButton = new JButton("Delete Animal");
        JButton seeApplicationsButton = new JButton("See Applications");

        // Add action listener for the Add Animal button
        addAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the animal's name
                String name = JOptionPane.showInputDialog("Enter the animal's name:");

                // Check if the user canceled the input dialog
                if (name == null) {
                    return; // Exit if the user canceled the input
                }

                // Prompt the user for the animal's species
                String species = JOptionPane.showInputDialog("Enter the animal's species (e.g., dog, cat, fish):");

                // Check if the user canceled the input dialog
                if (species == null) {
                    return; // Exit if the user canceled the input
                }

                // Prompt the user for the animal's age
                String ageString = JOptionPane.showInputDialog("Enter the animal's age:");

                // Check if the user canceled the input dialog
                if (ageString == null) {
                    return; // Exit if the user canceled the input
                }

                try {
                    int age = Integer.parseInt(ageString); // Convert age to integer
                    // Perform the action to add the animal using the provided details
                    JOptionPane.showMessageDialog(null, "Animal added:\nName: " + name + "\nSpecies: " + species + "\nAge: " + age);
                } catch (NumberFormatException ex) {
                    // Show an error message if the age is not a valid integer
                    JOptionPane.showMessageDialog(null, "Invalid age. Please enter a valid integer for the age.");
                }
            }
        });

        // Add action listener for the Edit Animal button
        editAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Edit Animal button clicked.");
                // Implement the Edit Animal functionality here
            }
        });

        // Add action listener for the Delete Animal button
        deleteAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Delete Animal button clicked.");
                // Implement the Delete Animal functionality here
            }
        });

        // Add action listener for the See Applications button
        seeApplicationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "See Applications button clicked.");
                // Implement the See Applications functionality here
            }
        });

        // Add buttons to the panel
        panel.add(addAnimalButton);
        panel.add(editAnimalButton);
        panel.add(deleteAnimalButton);
        panel.add(seeApplicationsButton);

        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ManagerScreen();
            }
        });
    }
}
