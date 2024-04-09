/**
 * Student Name: Ting Cheng
 * Professor: Sazzad Hossain
 * Due Date: April 9,2024
 * Description:  CST8288-031 Final Project  
 * Modify Date: April 9,2024 
 */
package main.algonquin.cst8288.FinalJavaProject.controller;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.Food;

/**
 * This class is a connection class between FoodDao and FoodServlet to make the implementation 
 * of food related operation works.
 */
public class FoodService {
	private FoodDao foodDao;
/**
 * constructor method
 */
    public FoodService() {
        this.foodDao = new FoodDao();
    }
/**
 * This method involk get the food list by different user type
 * @param userId the id of user
 * @param userType the type of user
 * @return the list contains matched food items
 */
    public List<Food> getAllFoodItemsByUser(int userId, String userType) {
        return foodDao.getAllFoodItemsByUser(userId, userType);
    }
    
    
/**
 * This method used to involk the add food item into the database
 * @param foodItem the item of food want to added
 * @return boolean value true for success false for fail
 */
    public boolean addFoodItem(Food foodItem) {
        return foodDao.insertFoodItem(foodItem);
    }
    
    
/**
 * This method used to involk update the food item operation
 * @param foodItem the item of food want to update
 * @return boolean value true for success false for fail
 */
    public boolean updateFoodItem(Food foodItem) {
        return foodDao.updateFoodItem(foodItem);
    }

    /**
     * This method used to involk delete food operation
     * @param foodID the food id which be deleted
     * @return  boolean value true for success false for fail
     */
    public boolean deleteFoodItem(int foodID) {
        return foodDao.deleteFoodItem(foodID);
    }
    
    
    /**
     * This method used to involk add subscription operation
     * @param userId the subscription operated user's id 
     * @param foodId the subscribed food id
     */
    public void addSubscription(int userId, int foodId) {
        foodDao.addSubscription(userId, foodId);
    }

    
    /**
     * This method used to involk send the notification to the subscribor
     * @param foodId the food id which is subscribed
     */
    public void sendSubscriptionNotifications(int foodId) {
        foodDao.sendSubscriptionNotifications(foodId);
    }
/**
 * This method used to invlok find the food item by food id
 * @param foodID the id of food
 * @return the food item
 */
	public Food findFoodItemById(int foodID) {
		return foodDao.findById(foodID);
	}

	/**
	 * This method used to involk the identify surplus food
	 * @return the list of food which is surplus status
	 */
    public List<Food> identifySurplus() {
        return foodDao.findSurplusItems();
    }
}
