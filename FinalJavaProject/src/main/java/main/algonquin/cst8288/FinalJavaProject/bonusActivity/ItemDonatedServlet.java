package main.algonquin.cst8288.FinalJavaProject.bonusActivity;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.algonquin.cst8288.FinalJavaProject.loginregister.DBConnection;

public class ItemDonatedServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			addItem(request, response);
			break;
		case "update":
			updateItem(request, response);
			break;
		case "delete":
			deleteItem(request, response);
			break;
		default:
			response.sendRedirect("login.jsp");
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("loadLocations".equals(action)) {
			extractUniqueLocation(request, response);
		} else if ("showItemsByLocation".equals(action)) {
			showItemsByLocation(request, response);
		} else if ("loadUserItems".equals(action)) {
			loadUserItems(request, response);
		} else if ("delete".equals(action)) { 
		} else if ("edit".equals(action)) { 
			editItem(request, response);
		} else {
			response.sendRedirect("login.jsp"); 
		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		ItemDonated item = extractItemFromRequest(request);

	    if (item.getQuantity() < 1) {
	        request.setAttribute("errorMessage", "Quantity must be at least 1.");
	        request.getRequestDispatcher("/addItemForm.jsp").forward(request, response);
	        return;
	    }

	    LocalDate expirationDate = LocalDate.parse(item.getExpirationDate());
	    if (expirationDate.isBefore(LocalDate.now())) {
	        request.setAttribute("errorMessage", "Expiration date must be in the future.");
	        request.getRequestDispatcher("/addItemForm.jsp").forward(request, response);
	        return;
	    }

	    try {
	        Connection con = DBConnection.getConnection();
	        ItemDonatedDAO dao = new ItemDonatedDAO(con);
	        if (dao.addItemDonated(item)) {
	            response.sendRedirect(request.getContextPath() + "/ItemDonatedServlet?action=loadLocations");
	        } else {
	            throw new Exception("Error adding the item.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("addItemForm.jsp");
	    }
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    ItemDonated item = extractItemFromRequest(request);
	    item.setItemId(Integer.parseInt(request.getParameter("itemId"))); 
	    
	    if (item.getQuantity() < 1) {
	        request.setAttribute("errorMessage", "Quantity must be at least 1.");
	        request.setAttribute("itemToEdit", item); 
	        request.getRequestDispatcher("/editItemForm.jsp").forward(request, response);
	        return;
	    }

	    LocalDate expirationDate = LocalDate.parse(item.getExpirationDate());
	    if (expirationDate.isBefore(LocalDate.now())) {
	        request.setAttribute("errorMessage", "Expiration date must be in the future.");
	        request.setAttribute("itemToEdit", item); 
	        request.getRequestDispatcher("/editItemForm.jsp").forward(request, response);
	        return;
	    }

	    try {
	        Connection con = DBConnection.getConnection();
	        ItemDonatedDAO dao = new ItemDonatedDAO(con);
	        if (dao.updateItemDonated(item)) {
	            response.sendRedirect("ItemDonatedServlet?action=loadUserItems");
	        } else {
	            throw new Exception("Error updating the item.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("editItemForm.jsp"); 
	    }
	}

	private void editItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		try {
			Connection con = DBConnection.getConnection();
			ItemDonatedDAO dao = new ItemDonatedDAO(con);
			ItemDonated item = dao.getItemById(itemId);
			if (item != null) {
				request.setAttribute("itemToEdit", item);
				request.getRequestDispatcher("/editItemForm.jsp").forward(request, response);
			} else {
				response.sendRedirect("login.jsp"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp");
		}
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int userId = (Integer) request.getSession().getAttribute("userId");

		try {
			Connection con = DBConnection.getConnection();
			ItemDonatedDAO dao = new ItemDonatedDAO(con);
			if (dao.deleteItemDonated(itemId, userId)) {
				response.sendRedirect("ItemDonatedServlet?action=loadUserItems");
			} else {
				throw new Exception("Error al eliminar el ítem.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp");
		}
	}

	private ItemDonated extractItemFromRequest(HttpServletRequest request) {

		ItemDonated item = new ItemDonated();

		item.setUserId((Integer) request.getSession().getAttribute("userId"));
		item.setTitle(request.getParameter("title"));
		item.setDescription(request.getParameter("description"));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		item.setPickupLocation(request.getParameter("pickupLocation"));
		item.setExpirationDate(request.getParameter("expirationDate"));
		item.setStatus(request.getParameter("status"));
		item.setContactMethod(request.getParameter("contactMethod"));
		return item;
	}

	protected void extractUniqueLocation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DBConnection.getConnection();
			ItemDonatedDAO dao = new ItemDonatedDAO(con);
			List<String> locations = dao.getUniqueLocations();
		
			request.setAttribute("locationList", locations);
			request.getRequestDispatcher("bonusActivity.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp");
		}
	}

	protected void showItemsByLocation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedLocation = request.getParameter("location");
		try {
			Connection con = DBConnection.getConnection();
			ItemDonatedDAO dao = new ItemDonatedDAO(con);

			List<ItemDonated> items = dao.getItemsByLocation(selectedLocation);
			request.setAttribute("itemsList", items);

			List<String> locations = dao.getUniqueLocations();
			request.setAttribute("locationList", locations);

			request.getRequestDispatcher("bonusActivity.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp");
		}
	}

	protected void loadUserItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false); 
		if (session != null && session.getAttribute("userId") != null) {
			int userId = (Integer) session.getAttribute("userId"); 

			try {
				Connection con = DBConnection.getConnection();
				ItemDonatedDAO dao = new ItemDonatedDAO(con);
				List<ItemDonated> userItems = dao.getItemsByUserId(userId);
				request.setAttribute("userItems", userItems);
				request.getRequestDispatcher("/manageMyItems.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("login.jsp");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}