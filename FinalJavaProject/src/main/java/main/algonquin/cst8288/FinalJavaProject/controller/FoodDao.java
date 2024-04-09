package main.algonquin.cst8288.FinalJavaProject.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        
    try (Connection con = DBConnection.getConnection();   		
         PreparedStatement pst = con.prepareStatement(sql)) {
    	
    	
        if ("RETAILER".equals(userType)) {//type with "retailer"
            pst.setInt(1, userId);
        }
        
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
        	// Convert each record in the result set to a FoodItem object and adding to its list.
            foodItems.add(extractFoodItemFromResultSet(rs));
 
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
        pstmt.setString(5, foodItem.getStatus().toString());// convert to String
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
    String sql = "DELETE FROM food WHERE foodid = ?";
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
        pstmt.setString(5, foodItem.getStatus().toString());
        pstmt.setBigDecimal(6, BigDecimal.valueOf(foodItem.getPrice()));
        pstmt.setBigDecimal(7, BigDecimal.valueOf(foodItem.getDiscount()));
        pstmt.setString(8, foodItem.getFoodLocation());
        pstmt.setBoolean(9, foodItem.getSubscription());
        pstmt.setInt(10, foodItem.getFoodID());

        int affectedRows = pstmt.executeUpdate();
        
        statusUpdated = affectedRows > 0;

     // Trigger notifications if the item status is updated to "SURPLUS"
        if (statusUpdated && "SURPLUS".equalsIgnoreCase(foodItem.getStatus().toString())) {
            sendSurplusNotifications(foodItem);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return statusUpdated;
}


	// Related EditForm
	public Food findById(int foodID) {
    String sql = "SELECT * FROM food WHERE foodid = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, foodID);
//        ResultSet rs = pstmt.executeQuery();
//
//        if (rs.next()) {
//            Food foodItem = new Food();
//            foodItem.setFoodID(rs.getInt("foodid"));
//            foodItem.setFoodName(rs.getString("foodname"));
//            foodItem.setAmount(rs.getInt("quantity"));
//            foodItem.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
//            foodItem.setUserID(rs.getInt("userid"));
//            foodItem.setStatus(FoodItemStatus.valueOf(rs.getString("status")));//
//            foodItem.setPrice(rs.getBigDecimal("price").doubleValue());
//            foodItem.setDiscount(rs.getBigDecimal("discount").doubleValue());
//            foodItem.setFoodLocation(rs.getString("foodLocation"));
//            foodItem.setSubscription(rs.getBoolean("subscription"));
//            return foodItem;
//        }
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return extractFoodItemFromResultSet(rs);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    // Utility method to avoid repetition
    private Food extractFoodItemFromResultSet(ResultSet rs) throws SQLException {
        Food item = new Food();

        item.setFoodID(rs.getInt("foodid"));
        item.setFoodName(rs.getString("foodname"));
        item.setAmount(rs.getInt("amount"));
        item.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
//        Timestamp timestamp = rs.getTimestamp("expiration_date");
//        if (timestamp != null) {
//            // Convert Timestamp to LocalDate
//            LocalDate expirationDate = timestamp.toLocalDateTime().toLocalDate();
//            item.setExpirationDate(expirationDate);
//        }
        item.setUserID(rs.getInt("userid"));
        item.setStatus(FoodItemStatus.valueOf(rs.getString("status")));
        item.setPrice(rs.getBigDecimal("price").doubleValue());
        item.setDiscount(rs.getBigDecimal("discount").doubleValue());
        item.setFoodLocation(rs.getString("foodLocation"));
        item.setSubscription(rs.getBoolean("subscription"));
        return item;    
    }

    // These 2 method related to SuplusFood
    // filter the suplus and list them
    public List<Food> findSurplusItems() {
        List<Food> surplusItems = new ArrayList<>();
        String query = "SELECT * FROM food WHERE status = 'SURPLUS'";
        try (Connection conn = DBConnection.getConnection();
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

    // Modify food status as Surplus
    public void markAsSurplus(int id) {
        String query = "UPDATE food SET status = 'SURPLUS' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
    
    public void sendSurplusNotifications(Food updatedFoodItem) {
        // Example: Fetch all users who have opted-in for notifications
        List<User> usersToNotify = getAllUsersWithNotificationsEnabled();

        for (User user : usersToNotify) {
        	System.out.println("Notification Email to: " + user.getEmail() + ";\nNotification: The food status is updated to Surplus, please log in the system to check!");
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
    public String getUserEmailById(int userId) {
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
    

    public boolean updateSubscriptionStatus(int foodId, boolean newStatus) {
        String sql = "UPDATE food SET subscription = ? WHERE foodid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, newStatus);
            pstmt.setInt(2, foodId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean purchaseAndUpdateInventory(int foodId, int purchaseAmount) {
        // Fetch current inventory
        String fetchSql = "SELECT amount FROM food WHERE foodid = ?";
        int currentAmount = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement fetchStmt = conn.prepareStatement(fetchSql)) {
            
            fetchStmt.setInt(1, foodId);
            try (ResultSet rs = fetchStmt.executeQuery()) {
                if (rs.next()) {
                    currentAmount = rs.getInt("amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Update inventory if sufficient stock is available
        if (currentAmount >= purchaseAmount) {
            String updateSql = "UPDATE food SET amount = amount - ? WHERE foodid = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                
                updateStmt.setInt(1, purchaseAmount);
                updateStmt.setInt(2, foodId);
                int affectedRows = updateStmt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Not enough inventory for purchase.");
            return false;
        }
    }
    
    
    public boolean addSubscriptionRecord(int userId, int foodId, String userEmail) {
        String insertSql = "INSERT INTO subscription (userId, foodId, userEmail) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            
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
}

