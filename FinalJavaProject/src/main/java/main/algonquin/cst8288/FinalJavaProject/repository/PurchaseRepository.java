package main.algonquin.cst8288.FinalJavaProject.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.algonquin.cst8288.FinalJavaProject.model.Purchase;

public class PurchaseRepository {
    private String url = "jdbc:yourDatabaseUrl"; // Customize with your actual database URL
    private String user = "yourDatabaseUser";    // Customize with your actual database user
    private String password = "yourDatabasePassword"; // Customize with your actual database password

    // Helper method to open database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void save(Purchase purchase) {
        String query = "INSERT INTO purchases (consumer_id, food_item_id, quantity, purchase_price, purchase_date) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setLong(1, purchase.getConsumerId());
            ps.setLong(2, purchase.getFoodItemId());
            ps.setInt(3, purchase.getQuantity());
            ps.setDouble(4, purchase.getPurchasePrice());
            ps.setDate(5, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Additional methods as needed
}

