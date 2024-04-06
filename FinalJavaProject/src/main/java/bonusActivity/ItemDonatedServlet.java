package bonusActivity;

import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
                response.sendRedirect("error.jsp"); // Página de error si la acción no se reconoce
                break;
        }
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Crear instancia de ItemDonated y establecer sus propiedades desde los parámetros de la solicitud
        ItemDonated item = extractItemFromRequest(request);
        // Por defecto, el itemId será 0 para nuevos ítems, esto debería manejarse en el DAO para asignar un nuevo ID
        try {
            Connection con = DBConnection.getConnection();
            ItemDonatedDAO dao = new ItemDonatedDAO(con);
            if (dao.addItemDonated(item)) { // Método para agregar ítem en el DAO
                response.sendRedirect("itemsList.jsp"); // Redirigir al usuario a la lista de ítems
            } else {
                throw new Exception("Error al agregar el ítem.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Similar a addItem, pero llama a dao.updateItemDonated()
        ItemDonated item = extractItemFromRequest(request);
        item.setItemId(Integer.parseInt(request.getParameter("itemId"))); // Establecer itemId para la actualización

        try {
            Connection con = DBConnection.getConnection();
            ItemDonatedDAO dao = new ItemDonatedDAO(con);
            if (dao.updateItemDonated(item)) {
                response.sendRedirect("itemDetails.jsp?itemId=" + item.getItemId()); // Redirigir al usuario a la página de detalles
            } else {
                throw new Exception("Error al actualizar el ítem.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
                response.sendRedirect("itemsList.jsp");
            } else {
                throw new Exception("Error al eliminar el ítem.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private ItemDonated extractItemFromRequest(HttpServletRequest request) {
        // Este método extrae los datos del ítem de la solicitud y los asigna a un nuevo objeto ItemDonated
        ItemDonated item = new ItemDonated();
        item.setUserId((Integer) request.getSession().getAttribute("userId"));
        item.setTitle(request.getParameter("title"));
        item.setDescription(request.getParameter("description"));
        item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        item.setPickupLocation(request.getParameter("pickupLocation"));
        item.setExpirationDate(request.getParameter("expirationDate"));
        item.setStatus(request.getParameter("status"));
        return item;
    }
}
