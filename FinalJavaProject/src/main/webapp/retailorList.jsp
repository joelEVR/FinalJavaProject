<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.util.*"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Food Item</title>
</head>
<body>
    <h2>Edit Food Item</h2>
    <form action="<%=request.getContextPath()%>/food/update" method="post">
        <input type="hidden" name="id" value="<%= ((Food)request.getAttribute("foodItem")).getFoodID() %>">
        Name: <input type="text" name="name" value="<%= ((Food)request.getAttribute("foodItem")).getFoodName() %>"><br>
        Quantity: <input type="number" name="quantity" value="<%= ((Food)request.getAttribute("foodItem")).getAmount() %>"><br>
        Expiration Date: <input type="date" name="expirationDate" value="<%= ((Food)request.getAttribute("foodItem")).getExpirationDate().toString() %>"><br>
        Status:
        <select name="status">
            <% for (FoodItemStatus status : FoodItemStatus.values()) { %>
                <option value="<%= status %>" <%= ((Food)request.getAttribute("foodItem")).getStatus() == status ? "selected" : "" %>><%= status %></option>
            <% } %>
        </select><br>
        Price: <input type="text" name="price" value="<%= ((Food)request.getAttribute("foodItem")).getPrice() %>"><br>
        FoodLocation: <input type="number" name="averageDailySales" value="<%= ((Food)request.getAttribute("foodItem")).getFoodLocation() %>"><br>
        <input type="submit" value="Update Food Item">
    </form>
    <a href="<%=request.getContextPath()%>/foodItem/list">Cancel</a>
</body>
</html>
