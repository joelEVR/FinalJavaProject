package main.algonquin.cst8288.FinalJavaProject.loginregister;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response, boolean True, boolean False) throws ServletException, IOException {
        //Copying all the input parameters in to local variables
       // String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType"); // Get userType from the request
        String notification = request.getParameter("notificaiton");
        User user = new User();
        boolean value = False;
        
        //user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType); // Set userType for the user
        
        if(notification == "1") {
        	value = True;
             user.setNotification(value);
             }else {
        user.setNotification(value);
             }
        

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
