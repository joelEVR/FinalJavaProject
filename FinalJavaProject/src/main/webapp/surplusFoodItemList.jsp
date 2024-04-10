<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Surplus Food Items</title>
</head>
<body>
<h2>Surplus Food Items</h2>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Expiration Date</th>
        <th>Status</th>
        <th>Price</th>
        <th>Average Daily Sales</th>
    </tr>
    <%@ page import="java.util.List"%>
    <%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>

    <%@ page import="java.time.format.DateTimeFormatter"%>
    <%
        List<Food> surplusFoodItems = (List<Food>) request.getAttribute("surplusFoodItems");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Food item : surplusFoodItems) {
    %>
    <tr>
            <td><%= item.getFoodName() %></td>
            <td><%= item.getAmount() %></td>
            <td><%= item.getExpirationDate() %></td>
            <td><%= item.getStatus() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getDiscount() %></td>
            <td><%= item.getFoodLocation() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

