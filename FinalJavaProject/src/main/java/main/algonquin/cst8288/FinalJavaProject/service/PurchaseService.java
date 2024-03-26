package main.algonquin.cst8288.FinalJavaProject.service;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.model.Purchase;
import main.algonquin.cst8288.FinalJavaProject.repository.FoodItemRepository;
import main.algonquin.cst8288.FinalJavaProject.repository.PurchaseRepository;

public class PurchaseService {
    private FoodItemRepository foodItemRepository;
    private PurchaseRepository purchaseRepository;
    
    // Dependency Injection Constructor
    public PurchaseService(FoodItemRepository foodItemRepository, PurchaseRepository purchaseRepository) {
        this.foodItemRepository = foodItemRepository;
        this.purchaseRepository = purchaseRepository;
    }
    
    public PurchaseService() {
		// TODO Auto-generated constructor stub	
	}

    public boolean makePurchase(Long consumerId, Long foodItemId, int quantity) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId);
        if (foodItem == null || foodItem.getQuantity() < quantity) {
            // Food item does not exist or not enough quantity
            return false;
        }

        // Assuming a fixed discount rate for simplicity; adjust as needed
        double discountRate = 0.1; // 10% discount
        double finalPrice = foodItem.getPrice() * quantity * (1 - discountRate);

        // Update inventory
        foodItem.setQuantity(foodItem.getQuantity() - quantity);
        foodItemRepository.update(foodItem);

        // Save purchase details
        Purchase purchase = new Purchase();
        purchase.setConsumerId(consumerId);
        purchase.setFoodItemId(foodItemId);
        purchase.setQuantity(quantity);
        purchase.setPurchasePrice(finalPrice);
        purchase.setPurchaseDate(new java.util.Date()); // Set to current date
        purchaseRepository.save(purchase);

        return true;
    }
    
    // Additional methods as needed
}