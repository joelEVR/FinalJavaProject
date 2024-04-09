/**
 * Student Name: Ting Cheng
 * Professor: Sazzad Hossain
 * Due Date: April 9,2024
 * Description:  CST8288-031 Final Project  
 * Modify Date: April 9,2024 
 */
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



/**
 * This class is used to performing database operations related to food items.
 */
public class FoodDao {
	/**
	 * constructor mehtod
	 */
public FoodDao(){}
	
/**
 * List all food item by different user type.  The retailor can only find his food item
 * The consumer can find all the surplus food with discount >0 and discount<100; The charitable
 * organization can find all the surplus food with discount = 100
 * 
 * @param userId the id of user who login the project to 
 * @param userType the type of user
 * @return a list of object of find out food item
 */
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

/**
 * This method used to generate the corresponding query to fetch the food item 
 * depends on different user type. Retailer get his food list, consumer gets 
 * all the surplus food item discount>0 and discount<100, charitable organization
 * get all the surplus food item discount = 100
 * @param userType tpye of user
 * @param userId id of user who login the project 
 * @return
 */
public String buildQueryBasedOnUserType(String userType, int userId) {
    
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

/**
 * Insert food item into the fwrp database's food table
 * 
 * @param foodItem the food item want to insert into
 * 
 * @return boolean value show insert success or not, true success or false fail
 */
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

/**
 * Delete the indicate food item from the fswp database
 * 
 * @param foodID the id of food item which want to delete
 * 
 * @return boolean value show delete success or not, true success or false fail
 */
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
    
/**
 * Update the food item in the databse
 * 
 * @param foodItem the food item which want to update in the database
 * 
 * @return boolean value show update success or not, true success or false fail
 */
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


/**
 * Find the food item by food id
 * 
 * @param foodID the id of food item
 * 
 * @return the corresponding food item
 */
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

	/**
	 * This method used to extract the valid food item from database and
	 * return them to the involk method
	 *  
	 * @param rs the ResultSet object
	 * 
	 * @return the list of food item match the ingest request.
	 * 
	 * @throws SQLException handle SQL Exceptions
	 */
    public Food extractFoodItemFromResultSet(ResultSet rs) throws SQLException {
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

    /**
     * This method is to extract the food which status is surplus
     * 
     * @return the surplus list of food
     */

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

/**
 * This method used to change the status of food item to surplus
 * 
 * @param id the food id
 */
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
     
  /**
   * This method used to send notification email to user when food status changed to surplus
   *   
   * @param updatedFoodItem the food item which is updated
   */
    public void sendSurplusNotifications(Food updatedFoodItem) {
        //Fetch all users who want notifications
        List<User> usersToNotify = getAllUsersWithNotificationsEnabled();

        for (User user : usersToNotify) {
        	System.out.println("Notification Email to: " + user.getEmail() + ";\nNotification: The food status is updated to Surplus, please log in the system to check!");
        }    
    }
/**
 * Get all the users who subscribe the notification 
 * 
 * @return the user list
 */
    public List<User> getAllUsersWithNotificationsEnabled() {
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

    /**
     * When user change the status to subscription, add this information to the subscription table
     * 
     * @param userId The subscription user id
     * 
     * @param foodId The food id which user subscribed
     * 
     * @return boolean value true for success and false for fail
     */
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

    /**
     * This method used to send subscription email to user who add subscription 
     * 
     * @param foodId the food id which user subscribed
     */
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
 
    /**
     * This method used to check if the food item is surplus or not
     * 
     * @param foodId the checked food id
     * 
     * @return boolean value true for surplus food, false for not
     */
    public boolean isFoodItemSurplus(int foodId) {
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
    

    /**
     * This method used to fetch user's email by user id
     * 
     * @param userId the id of user
     * 
     * @return the email of user
     */
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
    
/**
 * This method used to update the subscription status of a food item
 * 
 * @param foodId the id of food
 * 
 * @param newStatus the new status, yes for subscribed, no for not subscribed
 * 
 * @return boolean value true for success fails for fail
 */
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
  
    /**
     * This method used to operate the purchase operation to update the food inventory
     * 
     * @param foodId The food id which is involved into the purchase 
     * 
     * @param purchaseAmount purchase amount
     * 
     * @return boolean value true for success false for fail
     */
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
    
    /**
     * This method is insert subscription record into the subscription table in the database
     * @param userId the subscribed user
     * @param foodId the food id which is subscribed
     * @param userEmail the email address of subscribed user
     * @return boolean value true for success false for fail
     */
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

