package main.algonquin.cst8288.FinalJavaProject.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.loginregister.DBConnection;
import main.algonquin.cst8288.FinalJavaProject.loginregister.User;
import main.algonquin.cst8288.FinalJavaProject.model.Food;

public class FoodDao {
	
public FoodDao(){}
	
public List<Food> getAllFoodItemsByUser(int userId, String userType) {
    List<Food> foodItems = new ArrayList<>();
    String sql = buildQueryBasedOnUserType(userType, userId);

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        if ("retailers".equals(userType)) {
            pstmt.setInt(1, userId);
        }
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Food foodItem = new Food();
            foodItem.setFoodID(rs.getInt("foodid"));
            foodItem.setFoodName(rs.getString("foodname"));
            foodItem.setAmount(rs.getInt("quantity"));
            foodItem.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
            foodItem.setUserID(rs.getInt("userid"));
            foodItem.setStatus(rs.getString("status"));
            foodItem.setPrice(rs.getBigDecimal("price").doubleValue());
            foodItem.setDiscount(rs.getBigDecimal("discount").doubleValue());
            foodItem.setFoodLocation(rs.getString("foodLocation"));
            foodItem.setSubscription(rs.getBoolean("subscription"));
            foodItems.add(foodItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return foodItems;
}

private String buildQueryBasedOnUserType(String userType, int userId) {
    
	if ("RETAILER".equals(userType)) {
		
        return "SELECT * FROM food WHERE userid = ?";
    } else if ("CHARITY".equals(userType)) {
    	
        return "SELECT * FROM food WHERE discount = 100";
    } else if ("CONSUMER".equals(userType)) {
    	
        return "SELECT * FROM food WHERE discount > 0 AND discount < 100";
    } else {
        return "SELECT * FROM food"; 
    }
}

public boolean insertFoodItem(Food foodItem) {
    String sql = "INSERT INTO food (foodname, amount, expiration_date, userid, status, price, discount, foodLocation, subscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, foodItem.getFoodName());
        pstmt.setInt(2, foodItem.getAmount());
        pstmt.setDate(3, Date.valueOf(foodItem.getExpirationDate()));
        pstmt.setInt(4, foodItem.getUserID());
        pstmt.setString(5, foodItem.getStatus());
        pstmt.setBigDecimal(6, BigDecimal.valueOf(foodItem.getPrice()));
        pstmt.setBigDecimal(7, BigDecimal.valueOf(foodItem.getDiscount()));
        pstmt.setString(8, foodItem.getFoodLocation());
        pstmt.setBoolean(9, foodItem.getSubscription());

        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean deleteFoodItem(int foodID) {
    String sql = "DELETE FROM food_items WHERE foodid = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, foodID);
        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
public boolean updateFoodItem(Food foodItem) {
	
	boolean statusUpdated = false;
	
	 String sql = "UPDATE food SET foodname = ?, amount = ?, expiration_date = ?, userid = ?, status = ?, price = ?, discount = ?, foodLocation = ?, subscription = ? WHERE foodid = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, foodItem.getFoodName());
        pstmt.setInt(2, foodItem.getAmount());
        pstmt.setDate(3, Date.valueOf(foodItem.getExpirationDate()));
        pstmt.setInt(4, foodItem.getUserID());
        pstmt.setString(5, foodItem.getStatus());
        pstmt.setBigDecimal(6, BigDecimal.valueOf(foodItem.getPrice()));
        pstmt.setBigDecimal(7, BigDecimal.valueOf(foodItem.getDiscount()));
        pstmt.setString(8, foodItem.getFoodLocation());
        pstmt.setBoolean(9, foodItem.getSubscription());
        pstmt.setInt(10, foodItem.getFoodID());

        int affectedRows = pstmt.executeUpdate();
        
        statusUpdated = affectedRows > 0;

     // Trigger notifications if the item status is updated to "SURPLUS"
        if (statusUpdated && "SURPLUS".equalsIgnoreCase(foodItem.getStatus())) {
            sendSurplusNotifications(foodItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return statusUpdated;
}



    
    public void sendSurplusNotifications(Food updatedFoodItem) {
        // Example: Fetch all users who have opted-in for notifications
        List<User> usersToNotify = getAllUsersWithNotificationsEnabled();

        for (User user : usersToNotify) {
        	System.out.println("Notification Email to: " + user.getEmail() + ";\n\n\n  Notification: The food status is updated to Surplus, please log in the system to check!");
        }    
    }

    private List<User> getAllUsersWithNotificationsEnabled() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT userID, email FROM users WHERE notification = TRUE";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userID"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // lack of content in this method
    private List<User> getUsersSubscribedToFoodItem(Food foodItem) {
        List<User> users = new ArrayList<>();
        // Implement fetching of users subscribed to the specific food item
        // This method should query your database to find users who have subscribed to the food item matching the criteria
        return users;
    }
    
    
    
    public boolean addSubscription(int userId, int foodId) {
        String userEmail = getUserEmailById(userId); 
        String sql = "INSERT INTO subscriptions (userId, foodId, userEmail) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, foodId);
            pstmt.setString(3, userEmail);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendSubscriptionNotifications(int foodId) {
        // First, check if the foodId corresponds to a SURPLUS item
        if (isFoodItemSurplus(foodId)) {
            String sql = "SELECT userEmail FROM subscriptions WHERE foodId = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, foodId);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    String userEmail = rs.getString("userEmail");
                    // Simulate sending an email notification
                    System.out.println("Subscription Email to: " + userEmail + "; Subscription: A food item you are subscribed to is now marked as SURPLUS. Please log in to check.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Utility method to check if a food item is marked as SURPLUS
    private boolean isFoodItemSurplus(int foodId) {
        String sql = "SELECT status FROM food WHERE foodid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, foodId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "SURPLUS".equals(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to fetch a user's email by userId
    private String getUserEmailById(int userId) {
        String sql = "SELECT email FROM users WHERE userID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Or handle appropriately
    }
    
    public Food getFoodItemById(int foodID) {
        String sql = "SELECT * FROM food_items WHERE foodid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, foodID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Food foodItem = new Food();
                foodItem.setFoodID(rs.getInt("foodid"));
                foodItem.setFoodName(rs.getString("foodname"));
                foodItem.setAmount(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
                foodItem.setUserID(rs.getInt("userid"));
                foodItem.setStatus(rs.getString("status"));
                foodItem.setPrice(rs.getBigDecimal("price").doubleValue());
                foodItem.setDiscount(rs.getBigDecimal("discount").doubleValue());
                foodItem.setFoodLocation(rs.getString("foodLocation"));
                foodItem.setSubscription(rs.getBoolean("subscription"));
                return foodItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

