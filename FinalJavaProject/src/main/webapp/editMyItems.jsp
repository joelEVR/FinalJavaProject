<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page
	import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit My Items</title>
</head>
<body>
	<h2>My Items</h2>
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Location</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<ItemDonated> items = (List<ItemDonated>) request.getAttribute("userItems");
			if (items != null) {
				for (ItemDonated item : items) {
			%>
			<tr>
				<td><%=item.getTitle()%></td>
				<td><%=item.getDescription()%></td>
				<td><%=item.getQuantity()%></td>
				<td><%=item.getPickupLocation()%></td>
				<td><a
					href="ItemDonatedServlet?action=edit&itemId=<%=item.getItemId()%>">Edit</a>
					<a
					href="ItemDonatedServlet?action=delete&itemId=<%=item.getItemId()%>">Delete</a>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5">No items found.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>