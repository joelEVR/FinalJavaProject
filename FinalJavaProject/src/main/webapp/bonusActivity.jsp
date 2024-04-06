<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonated"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to the Donation Platform</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<h2>Welcome to the Donation Platform</h2>

	<!-- Buttons to add and edit items -->
	<div>
		<a href="addItem.jsp"><button>Add Item to Donate</button></a> <a
			href="editMyItems.jsp"><button>Edit My Published Items</button></a>
	</div>

	<!-- Table to display locations -->
	<h3>Locations</h3>
	<%-- <%
	List<String> locations = (List<String>) request.getAttribute("locationList");
	if (locations != null) {
		out.println("Locations list size in JSP: " + locations.size()); // Debugging line in JSP
		for (String location : locations) {
	%>
	<tr>
		<td><%=location%></td>
	</tr>
	<%
	}
	} else {
	out.println("Locations list is null in JSP."); // Debugging line in JSP
	}
	%> --%>
	<%-- <table>
		<thead>
			<tr>
				<th>Location</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<String> locations = (List<String>) request.getAttribute("locationList");
			if (locations != null) {
				for (String location : locations) {
			%>
			<tr>
				<td><%=location%></td>
			</tr>
			<%
			}
			}
			%>
		</tbody>

	</table> --%>
	
	<!-- Lista desplegable para seleccionar ubicaciones -->
    <form action="ItemDonatedServlet" method="get">
        <input type="hidden" name="action" value="showItemsByLocation">
        <select name="location" onchange="this.form.submit()">
            <option>Select Location</option>
            <% 
            List<String> locations = (List<String>) request.getAttribute("locationList");
            if (locations != null) {
                for (String location : locations) {
            %>
                <option value="<%= location %>"><%= location %></option>
            <% 
                }
            }
            %>
        </select>
    </form>

    <!-- Tabla para mostrar los ítems de la ubicación seleccionada -->
    <h3>Items at Selected Location</h3>
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Quantity</th>
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
                <td><%= item.getTitle() %></td>
                <td><%= item.getDescription() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getExpirationDate() %></td>
                <td><%= item.getStatus() %></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="5">No items found for the selected location.</td>
            </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</body>

</html>
