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
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		LoginUser loginUser = new LoginUser();

		loginUser.setUserName(userName);
		loginUser.setPassword(password);

		LoginDao loginDao = new LoginDao();

		try {
			String userValidate = loginDao.authenticateUser(loginUser);

			HttpSession session = request.getSession(); // Creating a session
			session.setMaxInactiveInterval(10 * 60); // Set session timeout
			session.setAttribute("User", userName); // Setting session attribute
			request.setAttribute("username", userName);
			
			switch (userValidate) {
			case "RETAILER":
				System.out.println("Retailer role entered");
				// En LoginServlet, después de la autenticación exitosa
				response.sendRedirect(request.getContextPath() + "/ItemDonatedServlet?action=loadLocations");

				/*
				 * request.getRequestDispatcher("/bonusActivity.jsp").forward(request,
				 * response);
				 */				break;
			case "CONSUMER":
				System.out.println("Consumer role entered");
				// En LoginServlet, después de la autenticación exitosa carga la lista de ubicaciones y redirige a funcionbonus
				response.sendRedirect(request.getContextPath() + "/ItemDonatedServlet?action=loadLocations");

				/*
				 * request.getRequestDispatcher("/bonusActivity.jsp").forward(request,
				 * response);
				 */				break;
			case "CHARITY":
				System.out.println("Charity role entered");
				// En LoginServlet, después de la autenticación exitosa
				response.sendRedirect(request.getContextPath() + "/ItemDonatedServlet?action=loadLocations");

				/*
				 * request.getRequestDispatcher("/bonusActivity.jsp").forward(request,
				 * response);
				 */				break;
			default:
				System.out.println("Error message = " + userValidate);
				request.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			}
		} catch (

		Exception e1) {
			e1.printStackTrace();
		}
	}
}