package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import main.algonquin.cst8288.FinalJavaProject.model.FoodItem;
import main.algonquin.cst8288.FinalJavaProject.service.FoodItemService;


@WebServlet("/foodItem/*") // Adjust the URL pattern based on your application's routing structure
public class FoodItemController extends HttpServlet {
    private FoodItemService foodItemService;

    @Override
    public void init() throws ServletException {
        // Example of simple initialization, replace with more sophisticated setup as needed
        this.foodItemService = new FoodItemService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
    	
        String action = request.getPathInfo(); // Using getPathInfo() for handling subpaths in a more flexible way

        if (action == null) {
            action = "/list"; // Default action
        }

        switch(action) {
            case "/list":
                listFoodItems(request, response);
                break;
            case "/identifySurplus":
                identifySurplus(request, response);
                break;
            // Additional cases as needed
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Example for handling adding a new food item
        String action = request.getPathInfo();

        if ("/add".equals(action)) {
            addFoodItem(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listFoodItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodItem> foodItems = foodItemService.listFoodItems();
        request.setAttribute("foodItems", foodItems);
        request.getRequestDispatcher("/foodItemList.jsp").forward(request, response);
    }

    private void identifySurplus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodItem> surplusFoodItems = foodItemService.identifySurplus();
        request.setAttribute("surplusFoodItems", surplusFoodItems);
        request.getRequestDispatcher("/surplusFoodItems.jsp").forward(request, response);
    }

    private void addFoodItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract information from request
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        // Assume similar extraction for other fields like expirationDate, price, etc.

        // Create FoodItem object and populate fields
        FoodItem foodItem = new FoodItem();
        foodItem.setName(name);
        foodItem.setQuantity(quantity);
        // Set other fields accordingly

        // Call service to add food item
        foodItemService.updateFoodItem(foodItem);

        // Redirect or forward to a confirmation page
        response.sendRedirect(request.getContextPath() + "/foodItem/list"); // Adjust as necessary
    }
}
