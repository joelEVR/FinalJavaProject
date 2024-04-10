<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Food Choose</title>
</head>
<body>
    <h2>Consumer Food Purchase</h2>
    
     <!-- Food Items List -->
    <h3>Food Items List</h3>
<%--     <a href="<%=request.getContextPath()%>/foodItem/listSurplus" class="btn btn-info"> --%>
<!--     View Surplus Food Items</a> -->
    
    <a href="<%=request.getContextPath()%>/food?action=list" 
    class="btn btn-info">View All Food Items</a>
    <br><br>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Quantity</th>
            <th>Expiration Date</th>
            <th>Status</th>
            <th>Price</th>
            <th>Discount</th>
            <th>FoodLocation</th>
            <th>Subscription<br>
            <th>Purchase<th>
            
        </tr>
        <% 
            List<Food> foodItems = (List<Food>) request.getAttribute("listFoodItems");
            if (foodItems != null) {
                for (Food item : foodItems) {
        %>
        <tr>
            <td><%= item.getFoodName() %></td>
            <td><%= item.getAmount() %></td>
            <td><%= item.getExpirationDate() %></td>
            <td><%= item.getStatus() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getDiscount() %></td>
            <td><%= item.getFoodLocation() %></td>
            <td><%= item.getSubscription() %></td>
            <td>
<%--                 <a href="<%=request.getContextPath()%>/food/markSurplus?foodId=<%=item.getFoodID()%>">MarkSurplus</a> --%>
                  <a href="<%=request.getContextPath()%>/food/purchase?foodId=<%=item.getFoodID()%>">Purchase</a> 
<%--                 <a href="<%=request.getContextPath()%>/food/delete?foodId=<%=item.getFoodID()%>" onclick="return confirm('Are you sure?')">Delete</a> --%>
            </td>
        </tr>
        <% 
                }
            } 
        %>
    </table>
    
    <form action="<%=request.getContextPath()%>/ItemDonatedServlet" method="get">
    <input type="hidden" name="action" value="loadLocations">
    <input type="submit" value="Back to Locations" class="btn">
    
</body>
</html>