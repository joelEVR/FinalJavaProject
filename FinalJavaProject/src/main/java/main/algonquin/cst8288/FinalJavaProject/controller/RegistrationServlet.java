package main.algonquin.cst8288.FinalJavaProject.controller;

import main.algonquin.cst8288.FinalJavaProject.model.User;
import main.algonquin.cst8288.FinalJavaProject.model.UserType;
import main.algonquin.cst8288.FinalJavaProject.security.HashingUtility;
import main.algonquin.cst8288.FinalJavaProject.service.UserService;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        
        // Password hashing (consider using BCrypt)
        String hashedPassword = HashingUtility.hashPassword(password);
        
        User newUser = new User(name, email, hashedPassword, UserType.valueOf(userType.toUpperCase()));
        
        UserService userService = new UserService();
        userService.registerUser(newUser);
        
        // Redirect or forward to login page or success page
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to registration JSP page
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}

