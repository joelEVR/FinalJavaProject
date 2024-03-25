package main.algonquin.cst8288.FinalJavaProject.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Claim {
    private Long id;
    private Long foodItemId;
    private Long charityId; // Assuming this is the ID of a User of type CHARITY
    private Timestamp claimDate;
    
    // Constructors, getters, and setters   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public Long getCharityId() {
		return charityId;
	}
	public void setCharityId(Long charityId) {
		this.charityId = charityId;
	}
	public Timestamp getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Timestamp timestamp) {
		this.claimDate = timestamp;
	}

//	public void setClaimDate(Date claimDate) {
//		this.claimDate = claimDate;
//	}
    
}
