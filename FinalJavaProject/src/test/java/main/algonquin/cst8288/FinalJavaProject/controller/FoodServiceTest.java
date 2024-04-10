package main.algonquin.cst8288.FinalJavaProject.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import main.algonquin.cst8288.FinalJavaProject.model.Food;

public class FoodServiceTest {
    private FoodService foodService;

    @Before
    public void setUp() throws Exception {
        foodService = new FoodService();
    }

    @Test
    public void testAddFoodItem() {
        Food foodItem = new Food();
     
        foodItem.setFoodName("Apple");
        foodItem.setAmount(100);
        foodItem.setExpirationDate(LocalDate.now().plusDays(7)); // Assume ExpirationDate is after 7 dyas
        foodItem.setUserID(1); // Assuem user is No1
        foodItem.setStatus(FoodItemStatus.AVAILABLE); 
        foodItem.setPrice(1.99); 
        foodItem.setDiscount(0); // Assue no Discount
        foodItem.setFoodLocation("Ottawa"); // 设置食品位置
        foodItem.setSubscription(false); // Assume not subscribe
        boolean result = foodService.addFoodItem(foodItem);
        assertTrue(result);
    }
}
