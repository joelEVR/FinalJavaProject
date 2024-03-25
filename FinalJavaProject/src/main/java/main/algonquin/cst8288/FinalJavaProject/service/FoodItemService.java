package main.algonquin.cst8288.FinalJavaProject.service;

import java.util.List;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.model.Subscription;
import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.repository.FoodItemRepository;
import main.algonquin.cst8288.FinalJavaProject.repository.UserRepository;
import main.algonquin.cst8288.FinalJavaProject.utility.NotificationUtility;

public class FoodItemService {
    private FoodItemRepository foodItemRepository;
    private SubscriptionService subscriptionService;
    private UserRepository userRepository;
    // Assuming an EmailService is available for sending emails
    private NotificationUtility notificationUtility;

    // Constructor or setter method to inject FoodItemRepository dependency
    public FoodItemService() {
        // Direct instantiation for simplicity, consider using Dependency Injection if available
        this.foodItemRepository = new FoodItemRepository();
    }
 
    /**
     * Constructs a FoodItemService with the necessary dependencies for managing food items,
     * user subscriptions, and sending notifications.
     *
     * @param subscriptionService the service for managing user subscriptions
     * @param userRepository the repository for accessing user data
     * @param notificationUtility utility class for sending notifications (emails, SMS, etc.)
     */
    public FoodItemService(SubscriptionService subscriptionService, UserRepository userRepository, NotificationUtility notificationUtility) {
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
        this.notificationUtility = notificationUtility;
    }
    
    public void updateFoodItem(FoodItem foodItem) {
        // Ensure the food item exists before attempting to update
        if (foodItem.getId() != null && foodItemRepository.findById(foodItem.getId()) != null) {
            foodItemRepository.update(foodItem);
        }
    }

    /**
     * Notifies subscribers about surplus food items based on their subscription preferences.
     * This method identifies eligible subscriptions and sends notifications using the preferred
     * communication method specified by the user (email or SMS).
     *
     * @param surplusFoodItem the surplus food item to notify subscribers about
     */
    public void notifySubscribersOfSurplusFood(FoodItem surplusFoodItem) {

        // Assuming we're notifying all subscribers about any surplus food, without location/type filters.
        List<Subscription> allSubscriptions = subscriptionService.getAllSubscriptions(); // You'd need to add this method.
        for (Subscription subscription : allSubscriptions) {
            // Directly fetching the user; adjustment for userRepository usage.
            User user = userRepository.findById(subscription.getUserId());
            if (user != null) {
            	String message = "Surplus food available: " + surplusFoodItem.getName() 
            	+ ". Available quantity: " + surplusFoodItem.getQuantity();
            	
                if ("email".equals(subscription.getCommunicationMethod())) {
                    notificationUtility.sendEmailNotification(user, message);
                } 
//                else if ("sms".equals(subscription.getCommunicationMethod())) {
//                    notificationUtility.sendSmsNotification(user, message);
//                }
            }
        }
    }

    
    public List<FoodItem> identifySurplus() {
        // Business logic to identify surplus food items
        List<FoodItem> allItems = foodItemRepository.findAll();
        allItems.forEach(item -> {
            if (item.isSurplus()) {
                // Assuming you have a method in FoodItemRepository to mark an item as surplus
                foodItemRepository.markAsSurplus(item.getId());
            }
        });
        // Optionally return the list of surplus items or update method signature as needed
        return foodItemRepository.findSurplusItems(); // Assuming this method fetches items marked as surplus
    }

    public List<FoodItem> listFoodItems() {
        return foodItemRepository.findAll();
    }

    // Implement the findById method in service
    public FoodItem findFoodItemById(Long id) {
        return foodItemRepository.findById(id);
    }
}
