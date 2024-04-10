<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.util.*"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.controller.FoodItemStatus"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Food Item</title>
</head>
<body>
    <h2>Edit Food Item</h2>
    <form action="<%=request.getContextPath()%>/food/update" method="post">
        FoodID: <input type="number" name="foodId" value="<%= ((Food)request.getAttribute("foodItem")).getFoodID() %>"><br>
        Name: <input type="text" name="foodName" value="<%= ((Food)request.getAttribute("foodItem")).getFoodName() %>"><br>
        Amount: <input type="number" name="amount" value="<%= ((Food)request.getAttribute("foodItem")).getAmount() %>"><br>
        Expiration Date: <input type="date" name="expirationDate" value="<%= ((Food)request.getAttribute("foodItem")).getExpirationDate().toString() %>"><br>
<%--         UserID: <input type="number" name="userID" value="<%= ((Food)request.getAttribute("foodItem")).getUserID() %>"><br> --%>
        Status:
        <select name="status">
            <% for (FoodItemStatus status : FoodItemStatus.values()) { %>
                <option value="<%= status %>" <%= ((Food)request.getAttribute("foodItem")).getStatus() == status ? "selected" : "" %>><%= status %></option>
            <% } %>
        </select><br>
        Price: <input type="text" name="price" value="<%= ((Food)request.getAttribute("foodItem")).getPrice() %>"><br>
                
        Discount: <input type="number" name="discount" value="<%= ((Food)request.getAttribute("foodItem")).getDiscount() %>"><br>
        FoodLocation: <input type="text" name="foodLocation" value="<%= ((Food)request.getAttribute("foodItem")).getFoodLocation() %>"><br>
        <input type="submit" value="Update Food Item">
    </form>
    <a href="<%=request.getContextPath()%>/food/list">Cancel</a>
</body>
</html>
