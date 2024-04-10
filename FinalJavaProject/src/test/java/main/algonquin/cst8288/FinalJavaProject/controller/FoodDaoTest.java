package main.algonquin.cst8288.FinalJavaProject.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import main.algonquin.cst8288.FinalJavaProject.model.Food;
import main.algonquin.cst8288.FinalJavaProject.controller.FoodDao;

public class FoodDaoTest {
    private FoodDao foodDao;

    @Before
    public void setUp() throws Exception {
        foodDao = new FoodDao();
    }

    @Test
    public void testInsertFoodItem() {
        
        Food foodItem = new Food();
        foodItem.setFoodName("Test Food");
        foodItem.setAmount(100);
        foodItem.setExpirationDate(LocalDate.of(2024, 05, 31));
        foodItem.setUserID(1); // assue a exist user
        foodItem.setStatus(FoodItemStatus.AVAILABLE);
        foodItem.setPrice(9.99);
        foodItem.setDiscount(0);
        foodItem.setFoodLocation("Test Location");
        foodItem.setSubscription(false);

        
        boolean result = foodDao.insertFoodItem(foodItem);
        
        assertTrue("The food item should be inserted successfully.", result);
    }
    
    @Test
    public void testUpdateFoodItem() {
        
        Food foodItemToUpdate = foodDao.findById(1);
        if (foodItemToUpdate != null) {
            //uodate data of foodItem
            foodItemToUpdate.setFoodName("Updated Test Food");
            foodItemToUpdate.setAmount(150);
            foodItemToUpdate.setExpirationDate(LocalDate.of(2024, 5, 1));
            foodItemToUpdate.setPrice(10.99);
            foodItemToUpdate.setDiscount(5);
            foodItemToUpdate.setFoodLocation("Updated Location");
            foodItemToUpdate.setSubscription(true);

            // attempt to update data to DB
            boolean result = foodDao.updateFoodItem(foodItemToUpdate);
            
            assertTrue("The food item should be updated successfully.", result);

            
            Food updatedFoodItem = foodDao.findById(1);
            assertTrue("Food name should be updated.", "Updated Test Food".equals(updatedFoodItem.getFoodName()));
            assertTrue("Food amount should be updated.", 150 == updatedFoodItem.getAmount());
            assertTrue("Food expiration date should be updated.", LocalDate.of(2024, 5, 1).equals(updatedFoodItem.getExpirationDate()));
            assertTrue("Food price should be updated.", 10.99 == updatedFoodItem.getPrice());
            assertTrue("Food discount should be updated.", 5 == updatedFoodItem.getDiscount());
            assertTrue("Food location should be updated.", "Updated Location".equals(updatedFoodItem.getFoodLocation()));
            assertTrue("Food subscription should be updated.", updatedFoodItem.getSubscription());
        }
    }
}