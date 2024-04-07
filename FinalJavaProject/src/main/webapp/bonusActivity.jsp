<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page
	import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/css_JoelVelasquez.css">
<title>Welcome to the Donation Platform</title>
</head>
<body class="align">
	<div class="grid">
		<h2 class="text--center">Welcome to the Donation Platform</h2>

		<div class="form__field">
			<a href="addItem.jsp" class="form__button">Add Item to Donate</a>
		</div>

		<div class="form__field">
			<a href="ItemDonatedServlet?action=loadUserItems"
				class="form__button">Edit My Published Items</a>
		</div>


		<h3 class="text--center">Locations</h3>
		<form action="ItemDonatedServlet" method="get"
			class="form select-location">
			<input type="hidden" name="action" value="showItemsByLocation">
			<div class="form__field">
				<select name="location" class="form__input"
					onchange="this.form.submit()">
					<option>Select Location</option>
					<%
					List<String> locations = (List<String>) request.getAttribute("locationList");
					if (locations != null) {
						for (String location : locations) {
					%>
					<option value="<%=location%>"><%=location%></option>
					<%
					}
					}
					%>
				</select>
			</div>
		</form>
	</div>

	<div class="gridtable">

		<h3 class="text--center">Items at Selected Location</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Expiration Date</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<ItemDonated> items = (List<ItemDonated>) request.getAttribute("itemsList");
				if (items != null && !items.isEmpty()) {
					for (ItemDonated item : items) {
				%>
				<tr>
					<td><%=item.getTitle()%></td>
					<td><%=item.getDescription()%></td>
					<td><%=item.getQuantity()%></td>
					<td><%=item.getPickupLocation()%></td>
					<td><%=item.getExpirationDate()%></td>
					<td><%=item.getStatus()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No items found for the selected location.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
