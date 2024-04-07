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
        //Copying all the input parameters in to local variables

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType"); // Get userType from the request
        String notification = request.getParameter("notificaiton");
        User user = new User();

      
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType); // Set userType for the user
        user.setNotification(notification);
             
        RegisterDao registerDao = new RegisterDao();

            //insert user data in to the database.
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
