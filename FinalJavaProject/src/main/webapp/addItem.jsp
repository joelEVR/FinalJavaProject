<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Item</title>
</head>
<body>
    <h2>Add New Item to Donate</h2>
    <form action="ItemDonatedServlet" method="post">
        <input type="hidden" name="action" value="add">
        <div>
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <div>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>
        </div>
        <div>
            <label for="pickupLocation">Pickup Location:</label>
            <input type="text" id="pickupLocation" name="pickupLocation" required>
        </div>
        <div>
            <label for="expirationDate">Expiration Date:</label>
            <input type="text" id="expirationDate" name="expirationDate" placeholder="YYYY-MM-DD" required>
        </div>
        <div>
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="available">Available</option>
                <option value="reserved">Reserved</option>
                <option value="exchanged">Exchanged</option>
            </select>
        </div>
        <button type="submit">Add Item</button>
    </form>
</body>
</html>