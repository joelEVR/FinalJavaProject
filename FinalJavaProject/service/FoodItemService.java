package main.algonquin.cst8288.FinalJavaProject.service;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.repository.FoodItemRepository;

public class FoodItemService {
    private FoodItemRepository foodItemRepository;

    // Constructor or setter method to inject FoodItemRepository dependency

    public void addFoodItem(FoodItem foodItem) {
        // Additional business logic for adding a new food item
        foodItemRepository.save(foodItem);
    }

    public void updateFoodItem(FoodItem foodItem) {
        // Additional business logic for updating a food item
        foodItemRepository.update(foodItem);
    }

    public void identifySurplus() {
        // Additional business logic for identifying surplus food items
        // Example: Check for items nearing expiration and update their status to surplus
    	List<FoodItem> surplusItems = foodItemRepository.findSurplusItems();
    }

    public List<FoodItem> listFoodItems() {
        // Additional business logic for listing food items
        return foodItemRepository.findAll();
    }

    // Other methods for food item management
}
