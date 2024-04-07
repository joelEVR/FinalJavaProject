<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/css_register.css">
<title>Add New Item</title>
</head>
<body class="align">

	<div class="grid">
		<h2 class="text--center">Add New Item to Donate</h2>
		<form action="ItemDonatedServlet" method="post" class="form register">
			<input type="hidden" name="action" value="add">

			<div class="form__field">
				<label for="title"><img src="svg/name.svg" width="20px"
					height="20px" alt="item_logo"> <span class="hidden">Title</span></label>
				<input type="text" id="title" name="title" class="form__input"
					placeholder="Title" required>
			</div>
			<div class="form__field">
				<label for="description"><img src="svg/name.svg"
					width="20px" height="20px" alt="description_logo"> <span
					class="hidden">Description</span></label>
				<textarea id="description" name="description" class="form__input"
					placeholder="Description" required></textarea>
			</div>
			<div class="form__field">
				<label for="quantity"><img src="svg/name.svg" width="20px"
					height="20px" alt="quantity_logo"> <span class="hidden">Quantity</span></label>
				<input type="number" id="quantity" name="quantity"
					class="form__input" placeholder="Quantity" required>
			</div>
			<div class="form__field">
				<label for="pickupLocation"><img src="svg/name.svg"
					width="20px" height="20px" alt="location_logo"> <span
					class="hidden">Pickup Location</span></label> <input type="text"
					id="pickupLocation" name="pickupLocation" class="form__input"
					placeholder="Pickup Location" required>
			</div>
			<div class="form__field">
				<label for="expirationDate"><img src="svg/name.svg"
					width="20px" height="20px" alt="calendar_logo"> <span
					class="hidden">Expiration Date (YYYY-MM-DD)</span></label> <input
					type="date" id="expirationDate" name="expirationDate"
					class="form__input" placeholder="Expiration Date" required>

			</div>
			<div class="form__field">
				<label for="status"><img src="svg/name.svg" width="20px"
					height="20px" alt="status_logo"> <span class="hidden">Status</span></label>
				<select id="status" name="status" class="form__input" required>
					<option value="available">Available</option>
					<option value="reserved">Reserved</option>
					<option value="exchanged">Exchanged</option>
				</select>
			</div>
			<div class="form__field">
				<input type="submit" value="Add Item" />
			</div>
		</form>
	</div>

</body>
</html>
