package main.algonquin.cst8288.FinalJavaProject.controller;


import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import main.algonquin.cst8288.FinalJavaProject.model.User;

import main.algonquin.cst8288.FinalJavaProject.service.UserService;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Assuming UserService is set up to accept a UserRepository for its constructor
//        this.userService = new UserService(new UserRepository()); ????
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            logout(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Default redirect if action is not recognized
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "register":
                register(request, response);
                break;
            case "login":
                login(request, response);
                break;
            default:
                response.sendRedirect("login.jsp"); // Redirect if the action is not recognized
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract registration details from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType"); // Assuming UserType is handled as a string here

        boolean success = userService.registerUser(name, email, password, userType);

        if (success) {
            // Registration successful, redirect to login page
            response.sendRedirect("login.jsp");
        } else {
            // Registration failed, redirect back to registration form or show error
            response.sendRedirect("register.jsp?error=true");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> user = userService.authenticateUser(email, password);

        if (user != null) {
            // Authentication successful, create a session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home.jsp"); // Redirect to a home page or dashboard
        } else {
            // Authentication failed, redirect back to login with error
            response.sendRedirect("login.jsp?error=true");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Invalidate the session to logout the user
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp"); // Redirect to login page after logout
    }
}

