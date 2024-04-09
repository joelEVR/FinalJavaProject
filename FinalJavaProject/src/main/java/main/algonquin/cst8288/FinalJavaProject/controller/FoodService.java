package main.algonquin.cst8288.FinalJavaProject.controller;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.Food;




public class FoodService {
	private FoodDao foodDao;

    public FoodService() {
        this.foodDao = new FoodDao();
    }

    public List<Food> getAllFoodItemsByUser(int userId, String userType) {
        return foodDao.getAllFoodItemsByUser(userId, userType);
    }

    public boolean addFoodItem(Food foodItem) {
        return foodDao.insertFoodItem(foodItem);
    }

    public boolean updateFoodItem(Food foodItem) {
        return foodDao.updateFoodItem(foodItem);
    }

    public boolean deleteFoodItem(int foodID) {
        return foodDao.deleteFoodItem(foodID);
    }
    
    public void addSubscription(int userId, int foodId) {
        foodDao.addSubscription(userId, foodId);
    }

    public void sendSubscriptionNotifications(int foodId) {
        foodDao.sendSubscriptionNotifications(foodId);
    }

	public Food findFoodItemById(int foodID) {
		return foodDao.findById(foodID);
	}

    public List<Food> identifySurplus() {
        return foodDao.findSurplusItems();
    }
}
