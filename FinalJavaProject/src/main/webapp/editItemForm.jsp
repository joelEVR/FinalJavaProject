<%@ page import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<title>Edit Item Form</title>
</head>
<body class="align">
    <%
    ItemDonated itemToEdit = (ItemDonated) request.getAttribute("itemToEdit");
    %>
    <p class="text--center">
        <span style="color: red; text-transform: uppercase; font-weight: 500">
            <%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%>
        </span>
    </p>

    <div class="grid">
        <h2 class="text--center">Edit Item</h2>
        <form action="ItemDonatedServlet" method="post" class="form register">
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="itemId" value="<%=itemToEdit.getItemId()%>" />

            <h4>Title</h4>
            <div class="form__field">
                <label for="title"><i class="fas fa-heading"></i></label>
                <input type="text" id="title" name="title" class="form__input" value="<%=itemToEdit.getTitle()%>" required />
            </div>

            <h4>Description</h4>
            <div class="form__field">
                <label for="description"><i class="fas fa-file-alt"></i></label>
                <textarea id="description" name="description" class="form__input" required><%=itemToEdit.getDescription()%></textarea>
            </div>

            <h4>Quantity</h4>
            <div class="form__field">
                <label for="quantity"><i class="fas fa-sort-numeric-up-alt"></i></label>
                <input type="number" id="quantity" name="quantity" class="form__input" value="<%=itemToEdit.getQuantity()%>" required />
            </div>

            <h4>Pickup Location</h4>
            <div class="form__field">
                <label for="pickupLocation"><i class="fas fa-map-marker-alt"></i></label>
                <input type="text" id="pickupLocation" name="pickupLocation" class="form__input" value="<%=itemToEdit.getPickupLocation()%>" required />
            </div>

            <h4>Contact Method</h4>
            <div class="form__field">
                <label for="contactMethod"><i class="fas fa-envelope"></i></label>
                <input type="text" id="contactMethod" name="contactMethod" class="form__input" value="<%=itemToEdit.getContactMethod()%>" required />
            </div>

            <h4>Expiration Date</h4>
            <div class="form__field">
                <label for="expirationDate"><i class="fas fa-calendar-alt"></i></label>
                <input type="date" id="expirationDate" name="expirationDate" class="form__input" value="<%=itemToEdit.getExpirationDate()%>" required />
            </div>

            <h4>Status</h4>
            <div class="form__field">
                <label for="status"><i class="fas fa-info-circle"></i></label>
                <select id="status" name="status" class="form__input">
                    <option value="available" <%= "available".equals(itemToEdit.getStatus()) ? "selected" : "" %>>Available</option>
                    <option value="reserved" <%= "reserved".equals(itemToEdit.getStatus()) ? "selected" : "" %>>Reserved</option>
                    <option value="exchanged" <%= "exchanged".equals(itemToEdit.getStatus()) ? "selected" : "" %>>Exchanged</option>
                </select>
            </div>

            <div class="form__field">
                <input type="submit" value="Save Changes" class="form__button" />
            </div>

            <div class="form__field">
                <a href="ItemDonatedServlet?action=loadLocations" class="form__button">Come back to Look Items</a>
            </div>
        </form>
    </div>
</body>
</html>
