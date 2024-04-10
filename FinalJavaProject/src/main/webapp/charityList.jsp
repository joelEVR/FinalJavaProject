<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.model.Food"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Charity Organization Update Subscription and Purchase</title>
</head>
<body>
    <h2>Update Subscription and Purchase</h2>
    <form action="<%=request.getContextPath()%>/food/updatePurchase" method="post">
        Food ID: <input type="hidden" name="foodId" value="<%=request.getParameter("foodId")%>"><br>
        User ID: 
        Subscription: 
        <select name="newSubscriptionStatus">
            <option value="true">Yes</option>
            <option value="false">No</option>
        </select><br>
        Purchase Amount: <input type="number" name="purchaseAmount" min="1" step="1" required><br>
        <input type="submit" value="Submit">
    </form>
    <a href="<%=request.getContextPath()%>/food/charity">Back to List</a>
</body>
</html>