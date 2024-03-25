package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.algonquin.cst8288.FinalJavaProject.service.ClaimService;
import main.algonquin.cst8288.FinalJavaProject.service.FoodItemService;

@WebServlet("/claim")
public class ClaimController extends HttpServlet {
    
    private ClaimService claimService;
    private FoodItemService foodItemService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize your services here
        this.claimService = new ClaimService();
        this.foodItemService = new FoodItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle GET requests, possibly for displaying a form to claim a food item
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Example for processing a claim
        String foodItemId = request.getParameter("foodItemId");
        String charityId = request.getParameter("charityId");
        
        try {
            Long itemId = Long.parseLong(foodItemId);
            Long charityIdLong = Long.parseLong(charityId);

            // Assuming a method exists in ClaimService to handle the claim logic ?????
            boolean success = claimService.createClaim(itemId, charityIdLong);

            if (success) {
                // Redirect or forward to a success page
                response.sendRedirect(request.getContextPath() + "/claimSuccess.jsp");
            } else {
                // Handle failure (e.g., item already claimed, does not exist, etc.)
                response.sendRedirect(request.getContextPath() + "/claimFailure.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid IDs
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

