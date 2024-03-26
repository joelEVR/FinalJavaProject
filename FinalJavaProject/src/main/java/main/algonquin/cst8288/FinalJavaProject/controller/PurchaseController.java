package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import main.algonquin.cst8288.FinalJavaProject.service.PurchaseService;

@WebServlet("/purchase")
public class PurchaseController extends HttpServlet {
    private PurchaseService purchaseService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Assuming PurchaseService can be instantiated here, otherwise use Dependency Injection or Factory if available
        this.purchaseService = new PurchaseService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve consumerId, foodItemId, and quantity from the request
        String consumerIdStr = request.getParameter("consumerId");
        String foodItemIdStr = request.getParameter("foodItemId");
        String quantityStr = request.getParameter("quantity");

        // Convert String parameters to appropriate types
        try {
            Long consumerId = Long.parseLong(consumerIdStr);
            Long foodItemId = Long.parseLong(foodItemIdStr);
            int quantity = Integer.parseInt(quantityStr);

            // Call purchaseService.makePurchase() and handle the result
            boolean success = purchaseService.makePurchase(consumerId, foodItemId, quantity);

            if (success) {
                // Redirect or forward to a success page or message
                request.setAttribute("purchaseSuccess", "The purchase was successful.");
                request.getRequestDispatcher("/purchaseSuccess.jsp").forward(request, response);
            } else {
                // Handle failure (e.g., not enough inventory, item doesn't exist)
                request.setAttribute("purchaseError", "The purchase could not be completed.");
                request.getRequestDispatcher("/purchaseError.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input formats
            request.setAttribute("error", "Invalid input. Please try again.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Implement doGet if you need to display a form or page before making a purchase
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Example: Display a form where users can enter the details of their purchase
        request.getRequestDispatcher("/purchaseForm.jsp").forward(request, response);
    }
}

