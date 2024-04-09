<%@ page
    import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>
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
            <input type="hidden" name="action" value="update" /> <input
                type="hidden" name="itemId" value="<%=itemToEdit.getItemId()%>" />

            <div class="form__field">
                <label for="title"><i class="fas fa-heading"></i> <span class="hidden">Title</span></label>
                <input type="text" id="title" name="title" class="form__input"
                    value="<%=itemToEdit.getTitle()%>" required />
            </div>

            <div class="form__field">
                <label for="description"><i class="fas fa-file-alt"></i> <span class="hidden">Description</span></label>
                <textarea id="description" name="description" class="form__input"><%=itemToEdit.getDescription()%></textarea>
            </div>

            <div class="form__field">
                <label for="quantity"><i class="fas fa-sort-numeric-up-alt"></i> <span class="hidden">Quantity</span></label>
                <input type="number" id="quantity" name="quantity" class="form__input"
                    value="<%=itemToEdit.getQuantity()%>" required />
            </div>

            <div class="form__field">
                <label for="pickupLocation"><i class="fas fa-map-marker-alt"></i> <span class="hidden">Pickup Location</span></label>
                <input type="text" id="pickupLocation" name="pickupLocation" class="form__input"
                    value="<%=itemToEdit.getPickupLocation()%>" required />
            </div>

            <div class="form__field">
                <label for="contactMethod"><i class="fas fa-envelope"></i> <span class="hidden">Contact Method</span></label>
                <input type="text" id="contactMethod" name="contactMethod" class="form__input"
                    value="<%=itemToEdit.getContactMethod()%>" required />
            </div>

            <div class="form__field">
                <label for="expirationDate"><i class="fas fa-calendar-alt"></i> <span class="hidden">Expiration Date</span></label>
                <input type="date" id="expirationDate" name="expirationDate" class="form__input"
                    value="<%=itemToEdit.getExpirationDate()%>" required />
            </div>

            <div class="form__field">
                <label for="status"><i class="fas fa-info-circle"></i> <span class="hidden">Status</span></label>
                <select id="status" name="status" class="form__input">
                    <option value="available" <%=itemToEdit.getStatus().equals("available") ? "selected" : ""%>>Available</option>
                    <option value="reserved" <%=itemToEdit.getStatus().equals("reserved") ? "selected" : ""%>>Reserved</option>
                    <option value="exchanged" <%=itemToEdit.getStatus().equals("exchanged") ? "selected" : ""%>>Exchanged</option>
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
