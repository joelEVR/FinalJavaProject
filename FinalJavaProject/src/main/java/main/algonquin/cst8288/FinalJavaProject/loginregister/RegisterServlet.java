package main.algonquin.cst8288.FinalJavaProject.loginregister;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén los parámetros del formulario
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userType = request.getParameter("userType");
        Boolean notification = Boolean.parseBoolean(request.getParameter("notification"));

        // Inicializa el mensaje de error
        String errorMessage = null;

        // Validación del lado del servidor
        if(name == null || name.isEmpty()) {
            errorMessage = "Name is required.";
        } else if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage = "Invalid email format.";
        } else if(password == null || password.length() < 8) {
            errorMessage = "Password must be at least 8 characters long.";
        } else if(!password.equals(confirmPassword)) {
            errorMessage = "Passwords do not match.";
        } else if(userType == null ) {
            errorMessage = "You must select user type.";
        }
        // Si hay un mensaje de error, reenvía al usuario al formulario de registro con el mensaje de error
        if(errorMessage != null) {
            request.setAttribute("errMessage", errorMessage);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Si la validación pasa, procede con la creación del usuario
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType); 
        user.setNotification(notification);

        RegisterDao registerDao = new RegisterDao();
        String userRegistered = registerDao.registerUser(user);

        if (userRegistered.equals("SUCCESS")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
