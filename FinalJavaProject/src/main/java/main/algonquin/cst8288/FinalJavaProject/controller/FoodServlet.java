package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.algonquin.cst8288.FinalJavaProject.loginregister.User;
import main.algonquin.cst8288.FinalJavaProject.model.Food;



@WebServlet("/food/*")
public class FoodServlet extends HttpServlet{
	
//	private static final long serialVersionUID = 1L;
	private FoodService foodService;
	private FoodDao foodDao;
	private int userid=0;

    //Called by the servlet container to indicate the servlet is being placed into service.
    @Override
    public void init() throws ServletException {
        this.foodService = new FoodService();
        // Initial foodItemRepository
        this.foodDao = new FoodDao();
    }

    //Handle GET requests. It uses the request's path info to determine the action to take
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	String action = request.getPathInfo();	
        try {
            if (action == null || action.equals("/list")) {
                listFoodItems(request, response);// need consider UserType and UserId
            } else if (action.equals("/delete")) {
                deleteFoodItem(request, response);
            } else if (action.equals("/listSurplus")) { 
                listSurplusFoodItems(request, response);
            } else if (action.equals("/edit")) {
                prepareEditForm(request, response);
            } else if ("/markSurplus".equals(action)) {
                markAsSurplus(request, response);
            }else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    
    	String action = request.getPathInfo();
      
        try {
            if (action == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
                return;
            }
            if (action.equals("/insert")) {
            	insertOrUpdateFoodItem(request, response, true);
            } else if (action.equals("/update")) {
            	insertOrUpdateFoodItem(request, response, false);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    
    // Helper Methods - handle listing food items
    private void listFoodItems(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
        HttpSession session = request.getSession();
                
        int userId = (Integer) session.getAttribute("userId");
        String userType = (String) session.getAttribute("userType");

        //Calls the listFoodItems method of foodItemService to retrieve a list of all food items
    	List<Food> listFoodItems = foodService.getAllFoodItemsByUser(userId, userType);
    	    	
    	//Attaches the list of food items to the request object. 
        request.setAttribute("listFoodItems",listFoodItems);
        //Uses the request dispatcher to forward the request and response objects to the JSP page 
        
        
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/retailor.jsp");
        dispatcher.forward(request, response);
    }
   
  
    private void deleteFoodItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        

    	int foodID = Integer.parseInt(request.getParameter("foodId"));
        foodService.deleteFoodItem(foodID);
        
        // Redirect to the doGet method to display updated list
        response.sendRedirect(request.getContextPath() +"/food/list");//typo "foodItem/list"
    }

    // Handles identifying surplus food items    
    private void listSurplusFoodItems(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	List<Food> surplusFoodItems = foodService.identifySurplus();
        request.setAttribute("surplusFoodItems", surplusFoodItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/surplusFoodItemList.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void prepareEditForm(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	
        int foodID = Integer.parseInt(request.getParameter("foodId"));
        
        Food foodItem = foodService.findFoodItemById(foodID); // Assuming this method exists and fetches the item
        if (foodItem != null) {
            request.setAttribute("foodItem", foodItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/retailorList.jsp"); 
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/food/list");
        }
    }
    
    private void markAsSurplus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int foodID = Integer.parseInt(request.getParameter("foodID"));
        foodDao.markAsSurplus(foodID); // Not in foodItemService
        response.sendRedirect(request.getContextPath() + "/food/list");
    }
 
    
    //Flag isNew to distinguish between add and update operations so that form submissions 
    //can be handled in the same method, simplifying your code
    private void insertOrUpdateFoodItem(HttpServletRequest request, HttpServletResponse response,boolean isNew) 
    		throws ServletException, IOException {
        // Extract information from request
        String name = request.getParameter("foodName");
        // Converted from a String to an integer
        int quantity = Integer.parseInt(request.getParameter("amount"));
        LocalDate expirationDate = LocalDate.parse(request.getParameter("expirationDate"));
        int userid = Integer.parseInt(request.getParameter("userID"));
        FoodItemStatus status = FoodItemStatus.valueOf(request.getParameter("status").toUpperCase());
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        String foodLocation = request.getParameter("foodLocation");
        boolean subscription = false;
        
        
        // Create FoodItem object and populate fields
        Food foodItem = new Food();
        if (!isNew) {
        	
        	int foodID = Integer.parseInt(request.getParameter("foodId"));
     	
            foodItem.setFoodID(foodID);
        }
        foodItem.setFoodName(name);
        foodItem.setAmount(quantity);
        foodItem.setExpirationDate(expirationDate);
        foodItem.setUserID(userid);
        foodItem.setStatus(status);
        foodItem.setPrice(price);
        foodItem.setDiscount(discount);
        foodItem.setFoodLocation(foodLocation);
        foodItem.setSubscription(subscription);        
        if (isNew) {
        	foodDao.insertFoodItem(foodItem); 
        } else {
            foodService.updateFoodItem(foodItem);
        }
        response.sendRedirect(request.getContextPath() + "/food/list");
    }
    
    private String getUserJspPath(String userType) {
        switch (userType) {
            case "RETAILER":
                return "retailor.jsp";
            
            case "CONSUMER":
                return "consumerList.jsp";
            
            case "CHARITY":
                return "charityList.jsp";
           
            default:
                return "login.jsp"; // Or any appropriate fallback
        }
    }


}
