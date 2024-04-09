package main.algonquin.cst8288.FinalJavaProject.model;

import java.time.LocalDate;

import main.algonquin.cst8288.FinalJavaProject.controller.FoodItemStatus;

public class Food {
	
	private int foodID;
    private String foodName;
    private int amount;
    private LocalDate expirationDate;
    private int userID;
    private FoodItemStatus status;
    private double price;
    private double discount;
    private String foodLocation;
    private boolean subscription;
    
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public FoodItemStatus getStatus() {
		return status;
	}
	public void setStatus(FoodItemStatus status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getFoodLocation() {
		return foodLocation;
	}
	public void setFoodLocation(String foodLocation) {
		this.foodLocation = foodLocation;
	}
	public boolean getSubscription() {
		return subscription;
	}
	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
    
    

}
