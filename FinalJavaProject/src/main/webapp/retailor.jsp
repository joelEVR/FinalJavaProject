<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.util.List"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Food Item Management</title>
</head>
<body>
    <h2>Food Item Management</h2>
    
    <!-- Food Item Form for Adding/Updating -->
    <form action="<%=request.getContextPath()%>/food" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="foodName"><br>
        Amount: <input type="number" name="amount" value=""><br>
        Expiration Date: <input type="date" name="expirationDate" value=""><br>
        Status: <select name="status">
            <option value="AVAILABLE">Available</option>
            <option value="SURPLUS">Surplus</option>
            <option value="CLAIMED">Claimed</option>
            <option value="SOLD">Sold</option>
        </select><br>
        Price: <input type="text" name="price" value=""><br>
        Food Location: <input type="text" name="foodLocation" value=""><br>
        Subscription: <select name="subscription">
        <option value="false">No</option>
        <option value="true">Yes</option>
    </select><br>
        <input type="submit" value="Save As New Food Item">
    </form>

    <!-- Food Items List -->
    <h3>Food Items List</h3>
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
            <th>FoodLocation</th>
            <th>Actions</th>
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
            <td><%= item.getFoodLocation() %></td>
            <td>
                <a href="<%=request.getContextPath()%>/food/markSurplus?id=<%=item.getFoodID()%>">MarkSurplus</a>
                 | <a href="<%=request.getContextPath()%>/food/edit?id=<%=item.getFoodID()%>">Edit</a> |
                <a href="<%=request.getContextPath()%>/food/delete?id=<%=item.getFoodID()%>" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <% 
                }
            } 
        %>
    </table>
</body>
</html>

	