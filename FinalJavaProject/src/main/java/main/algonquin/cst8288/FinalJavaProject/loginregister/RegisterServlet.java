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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        Boolean notification = Boolean.parseBoolean(request.getParameter("notification"));
        User user = new User();

      
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType); 
        user.setNotification(notification);

        RegisterDao registerDao = new RegisterDao();

            String userRegistered = registerDao.registerUser(user);

            if (userRegistered.equals("SUCCESS"))
            {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else
            {
                request.setAttribute("errMessage", userRegistered);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
    }
}