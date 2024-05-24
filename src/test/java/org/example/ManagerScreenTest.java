package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerScreenTest {

    @Test
    public void testAddAnimal() {
        ManagerScreen managerScreen = new ManagerScreen();

        // Add the animal
        Animal animal = new Animal("Buddy", "Dog", 3);
        managerScreen.getAnimals().add(animal);

        // Check if the animal is added correctly
        assertTrue(managerScreen.getAnimals().contains(animal));
    }

    @Test
    public void testDeleteAnimal() {
        ManagerScreen managerScreen = new ManagerScreen();

        // Add the animal
        Animal animal = new Animal("Buddy", "Dog", 3);
        managerScreen.getAnimals().add(animal);

        // Delete the animal
        managerScreen.getAnimals().remove(animal);

        // Check if the animal is deleted correctly
        assertFalse(managerScreen.getAnimals().contains(animal));
    }
}
