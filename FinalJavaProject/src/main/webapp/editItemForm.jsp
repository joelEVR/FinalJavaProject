<%@ page
	import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
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
				<label for="title"><img src="svg/title.svg" width="20px"
					height="20px" alt="title_logo"> <span class="hidden">Title</span></label>
				<input type="text" id="title" name="title" class="form__input"
					value="<%=itemToEdit.getTitle()%>" required />
			</div>

			<div class="form__field">
				<label for="description"><img src="svg/description.svg"
					width="20px" height="20px" alt="description_logo"> <span
					class="hidden">Description</span></label>
				<textarea id="description" name="description" class="form__input"><%=itemToEdit.getDescription()%></textarea>
			</div>

			<div class="form__field">
				<label for="quantity"><img src="svg/quantity.svg"
					width="20px" height="20px" alt="quantity_logo"> <span
					class="hidden">Quantity</span></label> <input type="number" id="quantity"
					name="quantity" class="form__input"
					value="<%=itemToEdit.getQuantity()%>" required />
			</div>

			<div class="form__field">
				<label for="pickupLocation"><img src="svg/location.svg"
					width="20px" height="20px" alt="pickupLocation_logo"> <span
					class="hidden">Pickup Location</span></label> <input type="text"
					id="pickupLocation" name="pickupLocation" class="form__input"
					value="<%=itemToEdit.getPickupLocation()%>" required />
			</div>

			<div class="form__field">
				<label for="expirationDate"><img src="svg/calendar.svg"
					width="20px" height="20px" alt="expirationDate_logo"> <span
					class="hidden">Expiration Date</span></label> <input type="date"
					id="expirationDate" name="expirationDate" class="form__input"
					value="<%=itemToEdit.getExpirationDate()%>" required />
			</div>

			<div class="form__field">
				<label for="status"><img src="svg/status.svg" width="20px"
					height="20px" alt="status_logo"> <span class="hidden">Status</span></label>
				<select id="status" name="status" class="form__input">
					<option value="available"
						<%=itemToEdit.getStatus().equals("available") ? "selected" : ""%>>Available</option>
					<option value="reserved"
						<%=itemToEdit.getStatus().equals("reserved") ? "selected" : ""%>>Reserved</option>
					<option value="exchanged"
						<%=itemToEdit.getStatus().equals("exchanged") ? "selected" : ""%>>Exchanged</option>
				</select>
			</div>

			<div class="form__field">
				<input type="submit" value="Save Changes" class="form__button" />
			</div>

			<div class="form__field">
				<a href="ItemDonatedServlet?action=loadLocations"
					class="form__button">Come back to Look Items</a>
			</div>
		</form>
	</div>
</body>
</html>
