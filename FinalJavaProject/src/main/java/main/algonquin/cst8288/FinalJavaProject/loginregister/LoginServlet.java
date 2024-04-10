package main.algonquin.cst8288.FinalJavaProject.loginregister;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		LoginUser loginUser = new LoginUser();
		loginUser.setEmail(email);
		loginUser.setPassword(password);

		LoginDao loginDao = new LoginDao();

		try {
			User user = loginDao.authenticateUser(loginUser); 
			if (user != null) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);

				session.setAttribute("email", user.getEmail()); 
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userType", user.getUserType());
				session.setAttribute("notification", user.isNotification());
				

				switch (user.getUserType()) {
				case "RETAILER":
					System.out.println("Retailer role entered");
					response.sendRedirect(request.getContextPath() + "/retailor.jsp");


				 break;
				case "CONSUMER":
					System.out.println("Consumer role entered");
				
					response.sendRedirect(request.getContextPath() + "/consumer.jsp");

					 break;
				case "CHARITY":
					System.out.println("Charity role entered");
					response.sendRedirect(request.getContextPath() + "/charity.jsp");

					 break;
				default:
					throw new IllegalStateException("Unexpected value: " + user.getUserType());
				}
			} else {
				System.out.println("Error message");
				request.setAttribute("errMessage", "Invalid user credentials");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}

		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}