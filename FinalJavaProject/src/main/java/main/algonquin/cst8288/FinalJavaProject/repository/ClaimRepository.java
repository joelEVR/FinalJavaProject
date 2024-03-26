package main.algonquin.cst8288.FinalJavaProject.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.Claim;

public class ClaimRepository {
    private String url = "jdbc:yourDatabaseUrl"; // Customize with your database URL
    private String user = "yourDatabaseUser";    // Customize with your database user
    private String password = "yourDatabasePassword"; // Customize with your database password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void save(Claim claim) {
        String query = "INSERT INTO claims (food_item_id, charity_id, claim_date) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, claim.getFoodItemId());
            ps.setLong(2, claim.getCharityId());
            ps.setTimestamp(3, new Timestamp(claim.getClaimDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Additional methods as needed...
}
