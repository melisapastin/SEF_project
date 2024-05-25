package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDatabase {

    // JDBC URL, username, and password of PostgreSQL server
    private static final String url = "jdbc:postgresql://localhost:5432/newDB";
    private static final String user = "postgres";
    private static final String password = "DCproject123";

    // JDBC variables for opening, closing and querying from the database
    private static Connection connection;
    private static Statement statement;
    public static void AddInAnimalDatabase(String name, String species, int age) {
        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();


            InsertTestResultsIntoDatabase(name,species,age);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void InsertTestResultsIntoDatabase(String name, String species,int age) throws SQLException {

        String query = String.format("INSERT INTO animals(name, species, age) VALUES('%s', '%s','%d')",name,species,age);
        statement.executeUpdate(query);
    }
    public static List<Animal> getAnimals() {
        String querySQL = "SELECT name, species , age FROM animals";
        List<Animal> animalList = new ArrayList<>();

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

             ResultSet rs = statement.executeQuery(querySQL);

            while (rs.next()) {
                String name = rs.getString("name");
                String species= rs.getString("species");
                int age = rs.getInt("age");
                Animal animal = new Animal(name,species,age);
                animalList.add(animal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return animalList;
    }
    public static List<Animal>DeleteAnimalFromAnimalDatabase(String animalName) {
        String querySQL = "DELETE FROM animals WHERE name = '"+animalName+"'";
        List<Animal> animalList = new ArrayList<>();

        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            statement.executeUpdate(querySQL);
            animalList = AnimalDatabase.getAnimals();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return animalList;
    }

    public static List<Animal>EditAnimalFromDatabase(String animalName,String newName, String species, int age){
        String updateSQL = "UPDATE animals SET name = ?, species = ? , age=? WHERE name = ?";
        List<Animal> animalList = new ArrayList<>();
        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(updateSQL);

                pstmt.setString(1, newName);
                pstmt.setString(2, species);
                pstmt.setInt(3, age);
                pstmt.setString(4, animalName);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Updated animal: " + animalName + " to " + newName + " with type " + species);
                } else {
                    System.out.println("No animal found with the name: " + animalName);
                }

                animalList = AnimalDatabase.getAnimals();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return animalList;

    }
}