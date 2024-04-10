/**
 * Student Name: Ting Cheng
 * Professor: Sazzad Hossain
 * Due Date: April 9,2024
 * Description:  CST8288-031 Final Project  
 * Modify Date: April 9,2024 
 */
package main.algonquin.cst8288.FinalJavaProject.model;

import java.time.LocalDate;

import main.algonquin.cst8288.FinalJavaProject.controller.FoodItemStatus;

/**
 * This is the food class used to define teh attributes and getter setter method of food
 */
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

    /**
     * Getter of foodId
     * @return the ID of food
     */
	public int getFoodID() {
		return foodID;
	}
	/**
	 * Setter of food
	 * @param foodID set the ID of food
	 */
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	/**
	 * Getter of foodName
	 * @return name of food
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * Setter of foodName
	 * @param foodName set name of food
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * Getter of amount
	 * @return the inventory amount of food item
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * Setter of food inventory amount
	 * @param amount the inventory amount of food
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * Getter of expiration date of food
	 * @return the expiration data of food item
	 */
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	/**
	 * Setter of food expiration date
	 * @param expirationDate the expiration data of food item
	 */
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * Getter of user id
	 * @return the id of user
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * Setter of user id
	 * @param userID the id of user
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * Getter of food status
	 * @return food status
	 */
	public FoodItemStatus getStatus() {
		return status;
	}
	/**
	 * Setter of food status
	 * @param status food status
	 */
	public void setStatus(FoodItemStatus status) {
		this.status = status;
	}
	/**
	 * Getter of food price
	 * @return food price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Setter of food price
	 * @param price food price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Getter of food discount
	 * @return food discount
	 */
	public double getDiscount() {
		return discount;
	}
	/**
	 * Setter of food discount
	 * @param discount food discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	/**
	 * Getter of food location
	 * @return food location
	 */
	public String getFoodLocation() {
		return foodLocation;
	}
	/**
	 * Setter of food location
	 * @param foodLocation food location
	 */
	public void setFoodLocation(String foodLocation) {
		this.foodLocation = foodLocation;
	}
	/**
	 * Getter of subscription
	 * @return the subscription
	 */
	public boolean getSubscription() {
		return subscription;
	}
	
	/** 
	 * Setter of subscription
	 * @param subscription the subscription
	 */
	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
    
}
