package main.algonquin.cst8288.FinalJavaProject.model;

import java.time.LocalDate;
import java.util.Date;

public class FoodItem {
    private Long id;
    private String name;
    private int quantity;
    private LocalDate expirationDate;
    private Long retailerId;
    private FoodItemStatus status;
    private double price;
    private int averageDailySales;
   
	
    /**
     * Method to check if the food item is surplus based on its expiration date and excess of demand.
     * Surplus items are those with expiry dates within the next one week and have an excess of 
     * supply compared to demand.
     *
     * @return true if the food item is surplus, false otherwise
     */
    public boolean isSurplus() {
    	LocalDate currentDate = LocalDate.now();
    	LocalDate oneWeekFromNow = currentDate.plusWeeks(1);
    	
        if (expirationDate.isBefore(oneWeekFromNow) || expirationDate.isEqual(oneWeekFromNow)) {
            double expectedDemand = averageDailySales * 7; // Expected demand for the next week
            return quantity > (expectedDemand * 1.3); // Checking for excess of 30% over expected demand
        }
        
        return false;
    }

    
    // Constructors, getters, setters, and other methods
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public LocalDate getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}


	public Long getRetailerId() {
		return retailerId;
	}


	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
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


	public int getAverageDailySales() {
		return averageDailySales;
	}


	public void setAverageDailySales(int averageDailySales) {
		this.averageDailySales = averageDailySales;
	}


    





}

