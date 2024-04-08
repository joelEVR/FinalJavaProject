package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.algonquin.cst8288.FinalJavaProject.model.Food;

@WebServlet("/food")
public class FoodServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private FoodService foodService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.foodService = new FoodService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        String userType = (String) session.getAttribute("userType");
        int userId = (Integer) session.getAttribute("userId"); // Ensure "userId" is set during login

        List<Food> foodItems = foodService.getAllFoodItemsByUser(userId, userType);
        request.setAttribute("foodItems", foodItems);

        // Dispatch request to the respective JSP based on user type
        String path = getUserJspPath(userType);
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addFoodItem(request, response);
                break;
            case "update":
                updateFoodItem(request, response);
                break;
            case "delete":
                deleteFoodItem(request, response);
                break;
        }
    }

    private void addFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract information from request
        String foodName = request.getParameter("foodName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int userId = Integer.parseInt(request.getParameter("userId")); // Or get from session
        String status = request.getParameter("status"); // Assuming status is provided, or set a default
        String foodLocation = request.getParameter("foodLocation");
        String subscription = request.getParameter("subscription");
        
        Food foodItem = new Food();
        foodItem.setFoodName(foodName);
        foodItem.setAmount(quantity);
        foodItem.setExpirationDate(expirationDate);
        foodItem.setUserID(userId);
        foodItem.setStatus(status);
        foodItem.setPrice(price);
        foodItem.setDiscount(discount);
        foodItem.setFoodLocation(foodLocation);
        
        boolean value = false;
        if(subscription == "1") value = true;
        	
        foodItem.setSubscription(value);
  
        foodService.addFoodItem(foodItem);

        // Redirect to the doGet method to display updated list
        doGet(request, response);
    }

    private void updateFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	int foodID = Integer.parseInt(request.getParameter("foodID"));
        String foodName = request.getParameter("foodName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        String status = request.getParameter("status");
        String foodLocation = request.getParameter("foodLocation");
        boolean subscription = Boolean.parseBoolean(request.getParameter("subscription"));
        
        Food foodItem = foodService.getFoodItemById(foodID);
        if (foodItem != null) {
            foodItem.setFoodName(foodName);
            foodItem.setAmount(quantity);
            foodItem.setExpirationDate(expirationDate);
            foodItem.setPrice(price);
            foodItem.setDiscount(discount);
            foodItem.setStatus(status);
            foodItem.setFoodLocation(foodLocation);
            foodItem.setSubscription(subscription);
            
            boolean isUpdated = foodService.updateFoodItem(foodItem);
            
            // Check if the food item's status was updated to "SURPLUS" and if so, trigger notifications
            if (isUpdated && "SURPLUS".equalsIgnoreCase(foodItem.getStatus())) {
                foodService.sendSubscriptionNotifications(foodItem.getFoodID());
            }
        }
        
        // After updating the food item, redirect to the listing page
        response.sendRedirect("food?action=list");
    }

    private void deleteFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int foodID = Integer.parseInt(request.getParameter("foodID"));
        foodService.deleteFoodItem(foodID);
        
        // Redirect to the doGet method to display updated list
        doGet(request, response);
    }

    private String getUserJspPath(String userType) {
        switch (userType) {
            case "RETAILOR":
                return "retailorList.jsp";
            
            case "CONSUMER":
                return "consumerList.jsp";
            
            case "CHARITY":
                return "charityList.jsp";
           
            default:
                return "login.jsp"; // Or any appropriate fallback
        }
    }


}
