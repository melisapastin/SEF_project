package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManagerScreen extends JFrame {
    // List to store animals in the shelter
    private List<Animal> animals;

    // Constructor to set up the manager screen
    public ManagerScreen() {
        // Initialize the list of animals
        animals = new ArrayList<>();

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
                    // Add the new animal to the list
                    animals.add(new Animal(name, species, age));
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
                if (animals.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No animals available to edit.");
                    return;
                }

                // Create an array of animal names for selection
                String[] animalNames = new String[animals.size()];
                for (int i = 0; i < animals.size(); i++) {
                    animalNames[i] = animals.get(i).getName();
                }

                // Prompt the user to select an animal to edit
                String selectedAnimalName = (String) JOptionPane.showInputDialog(
                        null,
                        "Select an animal to edit:",
                        "Edit Animal",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        animalNames,
                        animalNames[0]
                );

                // Check if the user canceled the selection
                if (selectedAnimalName == null) {
                    return; // Exit if the user canceled the selection
                }

                // Find the selected animal
                Animal selectedAnimal = null;
                for (Animal animal : animals) {
                    if (animal.getName().equals(selectedAnimalName)) {
                        selectedAnimal = animal;
                        break;
                    }
                }

                if (selectedAnimal != null) {
                    // Prompt the user for the new details
                    String newName = JOptionPane.showInputDialog("Enter the new name:", selectedAnimal.getName());
                    if (newName == null) {
                        return;
                    }

                    String newSpecies = JOptionPane.showInputDialog("Enter the new species:", selectedAnimal.getSpecies());
                    if (newSpecies == null) {
                        return;
                    }

                    String newAgeString = JOptionPane.showInputDialog("Enter the new age:", selectedAnimal.getAge());
                    if (newAgeString == null) {
                        return;
                    }

                    try {
                        int newAge = Integer.parseInt(newAgeString);
                        // Update the animal's details
                        selectedAnimal.setName(newName);
                        selectedAnimal.setSpecies(newSpecies);
                        selectedAnimal.setAge(newAge);
                        JOptionPane.showMessageDialog(null, "Animal details updated:\nName: " + newName + "\nSpecies: " + newSpecies + "\nAge: " + newAge);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid age. Please enter a valid integer for the age.");
                    }
                }
            }
        });

        // Add action listener for the Delete Animal button
        deleteAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (animals.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No animals available to delete.");
                    return;
                }

                // Create an array of animal names for selection
                String[] animalNames = new String[animals.size()];
                for (int i = 0; i < animals.size(); i++) {
                    animalNames[i] = animals.get(i).getName();
                }

                // Prompt the user to select an animal to delete
                String selectedAnimalName = (String) JOptionPane.showInputDialog(
                        null,
                        "Select an animal to delete:",
                        "Delete Animal",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        animalNames,
                        animalNames[0]
                );

                // Check if the user canceled the selection
                if (selectedAnimalName == null) {
                    return; // Exit if the user canceled the selection
                }

                // Find and remove the selected animal
                Animal selectedAnimal = null;
                for (Animal animal : animals) {
                    if (animal.getName().equals(selectedAnimalName)) {
                        selectedAnimal = animal;
                        break;
                    }
                }

                if (selectedAnimal != null) {
                    animals.remove(selectedAnimal);
                    JOptionPane.showMessageDialog(null, "Animal deleted: " + selectedAnimalName);
                }
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

// Animal class to store animal details
class Animal {
    private String name;
    private String species;
    private int age;

    // Constructor to initialize the animal's details
    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    // Getters and setters for the animal's details
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
