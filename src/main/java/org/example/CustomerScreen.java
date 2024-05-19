package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerScreen extends JFrame {
    // List to store animals in the shelter
    private List<Animal> animals;
    // List to store filtered animals based on the species
    private List<Animal> filteredAnimals;
    // Reference to the manager screen for application submission
    private ManagerScreen managerScreen;
    // UI components
    private DefaultListModel<String> animalListModel;
    private JList<String> animalList;

    // Constructor to set up the customer screen
    public CustomerScreen(List<Animal> animals, ManagerScreen managerScreen) {
        this.animals = animals;
        this.filteredAnimals = new ArrayList<>(animals);
        this.managerScreen = managerScreen;

        // Set the title of the frame
        setTitle("Animal Shelter Customer Screen");

        // Set the size of the frame
        setSize(500, 400);

        // Specify the close operation for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a list model and JList to display animals
        animalListModel = new DefaultListModel<>();
        animalList = new JList<>(animalListModel);
        updateAnimalList();

        // Add the animal list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(animalList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));

        // Create the Filter button
        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String species = JOptionPane.showInputDialog("Enter the species to filter by (e.g., dog, cat):");
                if (species != null) {
                    applyFilter(species);
                }
            }
        });

        // Create the Apply button
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = animalList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Animal selectedAnimal = filteredAnimals.get(selectedIndex);
                    String message = JOptionPane.showInputDialog("Enter your application message for " + selectedAnimal.getName() + ":");
                    if (message != null && !message.trim().isEmpty()) {
                        submitApplication(selectedAnimal, message);
                    } else {
                        JOptionPane.showMessageDialog(null, "Application message cannot be empty.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an animal to apply for.");
                }
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(filterButton);
        buttonPanel.add(applyButton);

        // Add the button panel to the top of the main panel
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Add the main panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    // Method to update the animal list displayed on the screen
    private void updateAnimalList() {
        animalListModel.clear();
        for (Animal animal : filteredAnimals) {
            animalListModel.addElement(animal.getName() + " (" + animal.getSpecies() + ", " + animal.getAge() + " years)");
        }
    }

    // Method to apply a filter based on species
    private void applyFilter(String species) {
        filteredAnimals.clear();
        for (Animal animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                filteredAnimals.add(animal);
            }
        }
        updateAnimalList();
    }

    // Method to submit an application for an animal
    private void submitApplication(Animal animal, String message) {
        Application application = new Application(animal.getName(), animal.getSpecies(), animal.getAge(), message);
        managerScreen.addApplication(application);  // Add the application to the manager's list
        JOptionPane.showMessageDialog(null, "Application submitted for " + animal.getName() + ".");
    }

    // Main method to run the customer screen application
    public static void main(String[] args) {
        // Sample data for testing
        List<Animal> sampleAnimals = new ArrayList<>();
        sampleAnimals.add(new Animal("Buddy", "dog", 3));
        sampleAnimals.add(new Animal("Mittens", "cat", 2));
        sampleAnimals.add(new Animal("Goldie", "fish", 1));

        // Create an instance of ManagerScreen
        ManagerScreen managerScreen = new ManagerScreen();

        // Create an instance of CustomerScreen with sample data
        new CustomerScreen(sampleAnimals, managerScreen);
    }
}

// Assume the ManagerScreen class and other classes (Animal, Application) are already defined
// as provided in the previous code snippet.

