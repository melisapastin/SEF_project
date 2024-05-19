import java.sql.*;

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

            // Generate random data and insert into the animals table

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
}
