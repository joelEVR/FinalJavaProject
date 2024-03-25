package main.algonquin.cst8288.FinalJavaProject.repository;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.Subscription;

import java.sql.*;
import java.util.ArrayList;


public class SubscriptionRepository {
    private String url = "jdbc:yourDatabaseUrl";
    private String user = "yourDatabaseUser";
    private String password = "yourDatabasePassword";

    // Method to open a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Method to save a new subscription
    public void save(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (user_id, location, communication_method, food_preferences) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, subscription.getUserId());
            ps.setString(2, subscription.getLocation());
            ps.setString(3, subscription.getCommunicationMethod());
            ps.setString(4, subscription.getFoodPreferences());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subscription.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to find subscriptions by location and food preferences
    public List<Subscription> findByLocationAndFoodPreferences(String location, String foodPreferences) {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE location = ? AND food_preferences = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, location);
            ps.setString(2, foodPreferences);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                subscriptions.add(extractSubscription(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subscriptions;
    }

    // Utility method to extract a subscription from a ResultSet
    private Subscription extractSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getLong("id"));
        subscription.setUserId(rs.getLong("user_id"));
        subscription.setLocation(rs.getString("location"));
        subscription.setCommunicationMethod(rs.getString("communication_method"));
        subscription.setFoodPreferences(rs.getString("food_preferences"));
        return subscription;
    }

    // Additional methods as needed...
}

