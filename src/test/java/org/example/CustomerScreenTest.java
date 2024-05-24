package org.example;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerScreenTest {

    private CustomerScreen customerScreen;
    private List<Animal> animals;

    @Before
    public void setUp() {
        // Sample data for testing
        animals = new ArrayList<>();
        animals.add(new Animal("Buddy", "dog", 3));
        animals.add(new Animal("Mittens", "cat", 2));
        animals.add(new Animal("Goldie", "fish", 1));

        // Create a mock ManagerScreen object for testing
        ManagerScreen managerScreen = new ManagerScreen();

        // Create an instance of CustomerScreen with sample data
        customerScreen = new CustomerScreen(animals, managerScreen);
    }

    @Test
    public void testApplyFilter() {
        // Filter by "dog"
        customerScreen.applyFilter("dog");
        List<Animal> filteredAnimals = customerScreen.getFilteredAnimals();

        // Check if the filtered animals list contains only dogs
        for (Animal animal : filteredAnimals) {
            assertEquals("dog", animal.getSpecies());
        }
    }

    @Test
    public void testSubmitApplication() {
        // Select the first animal ("Buddy")
        JList<String> animalList = customerScreen.getAnimalList();
        animalList.setSelectedIndex(0);

        // Submit application for the selected animal
        customerScreen.submitApplication(animals.get(0), "I want to adopt Buddy.");

        // Get the list of applications from ManagerScreen
        List<Application> applications = customerScreen.getManagerScreen().getApplications();

        // Check if the application for "Buddy" exists in the applications list
        boolean applicationExists = false;
        for (Application application : applications) {
            if (application.getAnimalName().equals("Buddy")) {
                applicationExists = true;
                break;
            }
        }

        assertTrue(applicationExists);
    }
}
