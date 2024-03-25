package main.algonquin.cst8288.FinalJavaProject.model;

import java.util.Date;

public class FoodItem {
    private Long id;
    private String name;
    private int quantity;
    private Date expirationDate;
    private Long retailerId;
    private FoodItemStatus status;
    private double price;
    private int averageDailySales;

    // Constructors, getters, setters, and other methods

   
    /**
     * Method to check if the food item is surplus based on its expiration date and excess of demand.
     * Surplus items are those with expiry dates within the next one week and have an excess of 
     * supply compared to demand.
     *
     * @return true if the food item is surplus, false otherwise
     */
    public boolean isSurplus() {
        // Calculate the current date
        Date currentDate = new Date();

        // Calculate the date one week from now
        long oneWeekInMillis = 7 * 24 * 60 * 60 * 1000;
        Date oneWeekFromNow = new Date(currentDate.getTime() + oneWeekInMillis);

        // Check if the expiration date is within one week from now and if there's an excess of supply compared to demand
        if (expirationDate.before(oneWeekFromNow)) {

			// Calculate the expected demand for the next week
            double expectedDemand = averageDailySales * 7;

            // Check if the quantity is significantly higher than the expected demand
            return quantity > (expectedDemand * 1.3); // Consider an excess of 30%
        }

        return false;
    }
    
//    public Date parseDate(String dateString) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return sdf.parse(dateString);
//    }
}

