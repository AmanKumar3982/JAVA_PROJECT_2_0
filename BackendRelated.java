//THIS IS A DEVELOPING PROJECT NOT FULLY DEVELOPED PROJECT.....
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BackendRelated {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/railway";
    private static final String USER = "root";
    private static final String PASS = "Aman@2023";

    public static boolean bookTicket(long pnr, String name, int age, String gender, String source, String destination) {
        String query = "INSERT INTO tickets (pnr, name, age, gender, source, destination) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

             // Display the entered PNR

            pstmt.setLong(1, pnr);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, gender);
            pstmt.setString(5, source);
            pstmt.setString(6, destination);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateTicket(long pnr, String field, String newValue) {
        String[] validFields = {"name", "age", "gender", "source", "destination"};
        boolean isValidField = false;

        for (String validField : validFields) {
            if (validField.equals(field.toLowerCase())) {
                isValidField = true;
                break;
            }
        }

        if (!isValidField) {
            System.out.println("Invalid field: " + field);
            return false;
        }

        String query = "UPDATE tickets SET " + field.toLowerCase() + " = ? WHERE pnr = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newValue);
            pstmt.setLong(2, pnr);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean removeTicket(long pnr) {
        String query = "DELETE FROM tickets WHERE pnr = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, pnr);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ResultSet viewTicket(long pnr) {
        String query = "SELECT * FROM tickets WHERE pnr = ?";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, pnr);
            return pstmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
