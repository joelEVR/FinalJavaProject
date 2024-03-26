package main.algonquin.cst8288.FinalJavaProject.service;

import java.util.Date;
import java.sql.Timestamp; // Use Timestamp for datetime fields

import main.algonquin.cst8288.FinalJavaProject.model.Claim;
import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.model.FoodItemStatus;
import main.algonquin.cst8288.FinalJavaProject.repository.ClaimRepository;
import main.algonquin.cst8288.FinalJavaProject.repository.FoodItemRepository;

public class ClaimService {
    private FoodItemRepository foodItemRepository;
    private ClaimRepository claimRepository;

    public ClaimService(FoodItemRepository foodItemRepository, ClaimRepository claimRepository) {
        this.foodItemRepository = foodItemRepository;
        this.claimRepository = claimRepository;
    }

    public ClaimService() {
		// TODO Auto-generated constructor stub
	}

	public void claimFoodItem(Long foodItemId, Long charityId) {
        // Business logic to claim a food item
        // For example, mark the food item as claimed in the database
        // and record the claim in the claims table
        Claim claim = new Claim();
        claim.setFoodItemId(foodItemId);
        claim.setCharityId(charityId);
//        claim.setClaimDate(new Date());
        claim.setClaimDate(new Timestamp(new Date().getTime()));
        claimRepository.save(claim);
        
        // Update the food item status to CLAIMED
        FoodItem foodItem = foodItemRepository.findById(foodItemId);
        if (foodItem != null) {
            foodItem.setStatus(FoodItemStatus.CLAIMED); // Assuming FoodItemStatus is an enum with a CLAIMED value
            foodItemRepository.update(foodItem);
        }
    }

	public boolean createClaim(Long itemId, Long charityIdLong) {
		// TODO Auto-generated method stub
		return false;
	}

    // Additional methods as needed...
}

