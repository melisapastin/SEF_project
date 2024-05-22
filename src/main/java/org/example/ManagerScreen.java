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

    // List to store adoption applications
    private List<Application> applications;

    // Constructor to set up the manager screen
    public ManagerScreen() {
        // Initialize the lists of animals and applications
        animals = new ArrayList<>();
        animals=org.example.AnimalDatabase.getAnimals();
        applications = new ArrayList<>();

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
                    org.example.AnimalDatabase.AddInAnimalDatabase(name,species,age);
                    animals=org.example.AnimalDatabase.getAnimals();
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
                   animals= org.example.AnimalDatabase.DeleteAnimalFromAnimalDatabase(selectedAnimalName);
                    JOptionPane.showMessageDialog(null, "Animal deleted: " + selectedAnimalName);
                }
            }
        });

        // Add action listener for the See Applications button
        seeApplicationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (applications.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No adoption applications available.");
                    return;
                }

                // Create an array of application descriptions for selection
                String[] applicationDescriptions = new String[applications.size()];
                for (int i = 0; i < applications.size(); i++) {
                    Application app = applications.get(i);
                    applicationDescriptions[i] = "Name: " + app.getAnimalName() + ", Species: " + app.getAnimalSpecies() + ", Age: " + app.getAnimalAge() + ", Message: " + app.getMessage();
                }

                // Prompt the user to select an application to process
                String selectedApplicationDescription = (String) JOptionPane.showInputDialog(
                        null,
                        "Select an application to process:",
                        "See Applications",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        applicationDescriptions,
                        applicationDescriptions[0]
                );

                // Check if the user canceled the selection
                if (selectedApplicationDescription == null) {
                    return; // Exit if the user canceled the selection
                }

                // Find the selected application
                Application selectedApplication = null;
                for (Application app : applications) {
                    String appDescription = "Name: " + app.getAnimalName() + ", Species: " + app.getAnimalSpecies() + ", Age: " + app.getAnimalAge() + ", Message: " + app.getMessage();
                    if (appDescription.equals(selectedApplicationDescription)) {
                        selectedApplication = app;
                        break;
                    }
                }

                if (selectedApplication != null) {
                    // Prompt the user to accept or reject the application
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to accept this application?\n" + selectedApplicationDescription,
                            "Accept or Reject Application",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        // Find and remove the adopted animal from the list
                        Animal adoptedAnimal = null;
                        for (Animal animal : animals) {
                            if (animal.getName().equals(selectedApplication.getAnimalName()) &&
                                    animal.getSpecies().equals(selectedApplication.getAnimalSpecies()) &&
                                    animal.getAge() == selectedApplication.getAnimalAge()) {
                                adoptedAnimal = animal;
                                break;
                            }
                        }

                        if (adoptedAnimal != null) {
                            animals.remove(adoptedAnimal);
                            JOptionPane.showMessageDialog(null, "Application accepted. Animal adopted: " + adoptedAnimal.getName());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Application rejected.");
                    }

                    // Remove the processed application from the list
                    applications.remove(selectedApplication);
                }
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

    public void addApplication(Application application) {
        // Add the application to the list of applications
        applications.add(application);

        // Display a confirmation message to the manager
        JOptionPane.showMessageDialog(this, "New application received for " + application.getAnimalName() + ".");
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

// Application class to store adoption application details
class Application {
    private String animalName;
    private String animalSpecies;
    private int animalAge;
    private String message;

    // Constructor to initialize the application details
    public Application(String animalName, String animalSpecies, int animalAge, String message) {
        this.animalName = animalName;
        this.animalSpecies = animalSpecies;
        this.animalAge = animalAge;
        this.message = message;
    }

    // Getters for the application details
    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public String getMessage() {
        return message;
    }
}