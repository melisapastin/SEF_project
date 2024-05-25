package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDatabase {
    private static final String url = "jdbc:postgresql://localhost:5432/newDB";
    private static final String user = "postgres";
    private static final String password = "DCproject123";

    public static void addApplicationToDatabase(String animalName, String animalSpecies, int animalAge, String message) {
        String insertSQL = "INSERT INTO applications (animal_name, animal_species, animal_age, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, animalName);
            pstmt.setString(2, animalSpecies);
            pstmt.setInt(3, animalAge);
            pstmt.setString(4, message);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application added to the database successfully.");
            } else {
                System.out.println("Failed to add the application to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    public static List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        String selectSQL = "SELECT * FROM applications";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                String animalName = rs.getString("animal_name");
                String animalSpecies = rs.getString("animal_species");
                int animalAge = rs.getInt("animal_age");
                String message = rs.getString("message");

                Application application = new Application(animalName, animalSpecies, animalAge, message);
                applications.add(application);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return applications;
    }
    public static List<Application> deleteApplicationFromDatabase(String animalName, String animalSpecies, int animalAge, String message) {
        String deleteSQL = "DELETE FROM applications WHERE animal_name = ? AND animal_species = ? AND animal_age = ? AND message = ?";

        try (Connection conn = DriverManager.getConnection( url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, animalName);
            pstmt.setString(2, animalSpecies);
            pstmt.setInt(3, animalAge);
            pstmt.setString(4, message);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application deleted from the database successfully.");
            } else {
                System.out.println("Failed to delete the application from the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        List<Application> applications = new ArrayList<>();
        applications=getAllApplications();
        return applications;
    }
}
