/**
 * Student Name: Ting Cheng
 * Professor: Sazzad Hossain
 * Due Date: April 9,2024
 * Description:  CST8288-031 Final Project  
 * Modify Date: April 9,2024 
 */
package main.algonquin.cst8288.FinalJavaProject.controller;

/**
 * These statuses help manage the lifecycle of food items within the project.
 */
public enum FoodItemStatus {
	/**
	 * the food item is available.
	 */
	AVAILABLE,
	/**
	 * the food item is claimed donate
	 */
	CLAIMED,
	/** 
	 * the food item is sold out
	 */
	SOLD,
	/**
	 * the food item is surplus for user and charitary organization purchase or claim donate.
	 */
	SURPLUS
}