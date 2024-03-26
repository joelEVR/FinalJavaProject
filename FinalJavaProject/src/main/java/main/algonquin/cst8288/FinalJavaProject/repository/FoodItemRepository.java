package main.algonquin.cst8288.FinalJavaProject.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.model.FoodItemStatus;

public class FoodItemRepository {
    private String url = "jdbc:yourDatabaseUrl"; // Customize with your database URL
    private String user = "yourDatabaseUser";    // Customize with your database user
    private String password = "yourDatabasePassword"; // Customize with your database password

    // Helper method to open database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Display all the food information
    public List<FoodItem> findAll() {
        List<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM food_items";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                foodItems.add(extractFoodItemFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return foodItems;
    }
 
    // Display the specific food information by ID
    public FoodItem findById(Long id) {
        String query = "SELECT * FROM food_items WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractFoodItemFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Display the specific food information by RetailerId
    public List<FoodItem> findByRetailerId(Long retailerId) {
        List<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM food_items WHERE retailer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, retailerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    foodItems.add(extractFoodItemFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return foodItems;
    }

    // Display the specific food information by SurplusItems
    public List<FoodItem> findSurplusItems() {
        List<FoodItem> surplusItems = new ArrayList<>();
        String query = "SELECT * FROM food_items WHERE status = 'SURPLUS'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                surplusItems.add(extractFoodItemFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return surplusItems;
    }

    // Insert new food information
    public void save(FoodItem item) {
        String query = "INSERT INTO food_items (name, quantity, expiration_date, retailer_id, status, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getQuantity());
//            ps.setDate(3, new java.sql.Date(item.getExpirationDate().getTime())); 
            ps.setDate(3, java.sql.Date.valueOf(item.getExpirationDate()));
            ps.setLong(4, item.getRetailerId());
            ps.setString(5, item.getStatus().toString());
            ps.setDouble(6, item.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Update foods information
    public void update(FoodItem item) {
        String query = "UPDATE food_items SET name = ?, quantity = ?, expiration_date = ?, status = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getQuantity());
            ps.setDate(3, java.sql.Date.valueOf(item.getExpirationDate())); // Adjusted for LocalDate
            ps.setString(4, item.getStatus().toString());
            ps.setDouble(5, item.getPrice());
            ps.setLong(6, item.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Modify food status as Surplus
    public void markAsSurplus(Long id) {
        String query = "UPDATE food_items SET status = 'SURPLUS' WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Utility method to avoid repetition
    private FoodItem extractFoodItemFromResultSet(ResultSet rs) throws SQLException {
        FoodItem item = new FoodItem();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setQuantity(rs.getInt("quantity"));
//        item.setExpirationDate(new Date(rs.getTimestamp("expiration_date").getTime()));
        Timestamp timestamp = rs.getTimestamp("expiration_date");
        if (timestamp != null) {
            // Convert Timestamp to LocalDate
            LocalDate expirationDate = timestamp.toLocalDateTime().toLocalDate();
            item.setExpirationDate(expirationDate);
        }
        item.setRetailerId(rs.getLong("retailer_id"));
        item.setStatus(FoodItemStatus.valueOf(rs.getString("status")));
        item.setPrice(rs.getDouble("price"));
        item.setAverageDailySales(rs.getInt("average_daily_sales"));
        return item;
    }	
 
    // Delete the specific food information
    public void delete(Long id) {
        String query = "DELETE FROM food_items WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
