package main.algonquin.cst8288.FinalJavaProject.controller;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.service.UserService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserService userService = new UserService();
        Optional<User> user = userService.authenticateUser(email, password);
        
        if (user.isPresent()) {
            // Successful authentication. You can store user info in session, etc.
            request.getSession().setAttribute("user", user.get());
            response.sendRedirect("/home"); // Redirect to a home page or dashboard
        } else {
            // Authentication failed. Set an error message and redirect back to the login page.
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login JSP page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
