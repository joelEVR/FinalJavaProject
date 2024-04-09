<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>Add New Item</title>
</head>
<body class="align">

    <div class="grid">
        <h2 class="text--center">Add New Item to Donate</h2>
        <form action="ItemDonatedServlet" method="post" class="form register">
            <input type="hidden" name="action" value="add">

            <p class="text--center">
                <span
                    style="color: red; text-transform: uppercase; font-weight: 500">
                    <%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%>
                </span>
            </p>

            <div class="form__field">
                <label for="title"><i class="fa fa-heading"></i> <span class="hidden">Title</span></label>
                <input type="text" id="title" name="title" class="form__input"
                    placeholder="Title" required>
            </div>

            <div class="form__field">
                <label for="description"><i class="fa fa-heading"></i> <span class="hidden">Description</span></label>
                <textarea id="description" name="description" class="form__input"
                    placeholder="Description" required></textarea>
            </div>

            <div class="form__field">
                <label for="quantity"><i class="fa fa-sort-numeric-up"></i> <span class="hidden">Quantity</span></label>
                <input type="number" id="quantity" name="quantity"
                    class="form__input" placeholder="Quantity" required>
            </div>

            <div class="form__field">
                <label for="pickupLocation"><i class="fa fa-map-marker-alt"></i> <span class="hidden">Pickup Location</span></label> 
                <input type="text" id="pickupLocation" name="pickupLocation" class="form__input"
                    placeholder="Pickup Location" required>
            </div>

            <div class="form__field">
                <label for="contactMethod"><i class="fa fa-envelope"></i> <span class="hidden">Contact Method</span></label> 
                <input type="text" id="contactMethod" name="contactMethod" class="form__input"
                    placeholder="Contact Method" required>
            </div>

            <div class="form__field">
                <label for="expirationDate"><i class="fa fa-calendar-alt"></i> <span class="hidden">Expiration Date (YYYY-MM-DD)</span></label> 
                <input type="date" id="expirationDate" name="expirationDate"
                    class="form__input" placeholder="Expiration Date" required>
            </div>

            <div class="form__field">
                <input type="submit" value="Add Item" />
            </div>

            <div class="form__field">
                <a href="ItemDonatedServlet?action=loadLocations"
                    class="form__button">Come back to Look Items</a>
            </div>
        </form>
    </div>

</body>
</html>
