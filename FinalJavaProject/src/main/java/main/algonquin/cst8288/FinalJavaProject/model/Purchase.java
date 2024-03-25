package main.algonquin.cst8288.FinalJavaProject.model;

//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Date;
import java.time.LocalDate;

public class Purchase {
    private Long id;
    private Long consumerId;
    private Long foodItemId;
    private int quantity;
    private double purchasePrice;
    private java.util.Date purchaseDate;
    
    // Constructors, getters, and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	public Long getFoodItemId() {
		return foodItemId;
	}
	public void setFoodItemId(Long foodItemId) {
		this.foodItemId = foodItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public java.util.Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(java.util.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

    
}
