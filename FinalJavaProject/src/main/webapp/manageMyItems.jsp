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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<title>Edit My Items</title>
</head>
<body class="align">
	<div class="grid">
		<h2 class="text--center">My Items</h2>
		<h3 class="text--center">Items to edit</h3>
	</div>
	<div class="gridtable">

		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Quantity</th>
					<th>Location</th>
					<th>Expiration Date</th>
					<th>Status</th>					

					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<ItemDonated> items = (List<ItemDonated>) request.getAttribute("userItems");
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

					<td class="td-center">
						<div class="icon-container">
							<a
								href="ItemDonatedServlet?action=edit&itemId=<%=item.getItemId()%>"
								class="action-icon"> <i class="fas fa-edit"
								style="color: #4CAF50;"></i>
							</a> <a
								href="ItemDonatedServlet?action=delete&itemId=<%=item.getItemId()%>"
								class="action-icon"> <i class="fas fa-trash-alt"
								style="color: #F44336;"></i>
							</a>
						</div>
					</td>


				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="7">No items found.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

	</div>

	<div class="grid">
		<div class="form__field">
			<a href="ItemDonatedServlet?action=loadLocations"
				class="form__button">Come back to Look Items</a>
		</div>
	</div>
</body>
</html>
