import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.algonquin.cst8288.FinalJavaProject.loginregister.RegisterDao;
import main.algonquin.cst8288.FinalJavaProject.loginregister.RegisterServlet;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.io.IOException;

class RegisterServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RegisterDao registerDao;

    private RegisterServlet servlet;


    @Test
    void testDoPostSuccessfulRegistration() throws ServletException, IOException {
        mockRequestParameters("Test User", "test@example.com", "password123", "password123", "CHARITY", "1");
        when(registerDao.registerUser(any())).thenReturn("SUCCESS");
        servlet.doPost(request, response);
        verify(request, never()).setAttribute(eq("errMessage"), anyString());
        verify(request).getRequestDispatcher("/login.jsp");
    }

    @Test
    void testDoPostWithInvalidEmail() throws ServletException, IOException {
        mockRequestParameters("Test User", "invalidEmail", "password123", "password123", "RETAILER", "1");
        servlet.doPost(request, response);
        verify(request).setAttribute("errMessage", "Invalid email format.");
        verify(request).getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Test
    void testDoPostWithNonMatchingPasswords() throws ServletException, IOException {
        mockRequestParameters("Test User", "test@example.com", "password123", "differentPassword", "RETAILER", "1");
        servlet.doPost(request, response);
        verify(request).setAttribute("errMessage", "Passwords do not match.");
        verify(request).getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Test
    void testDoPostRegistrationFails() throws ServletException, IOException {
        mockRequestParameters("Test User", "test@example.com", "password123", "password123", "CHARITY", "1");
        when(registerDao.registerUser(any())).thenReturn("Oops.. Something went wrong while REGISTER..");
        servlet.doPost(request, response);
        verify(request).setAttribute("errMessage", "Oops.. Something went wrong while REGISTER..");
        verify(request).getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Test
    void testRegistrationWithMissingEmail() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Test User");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("confirmPassword")).thenReturn("password123");
        when(request.getParameter("userType")).thenReturn("CHARITY");
        when(request.getParameter("notification")).thenReturn("1");

        servlet.doPost(request, response);

        verify(request).setAttribute("errMessage", "Email is required.");
        verify(request).getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private void mockRequestParameters(String name, String email, String password, String confirmPassword, String userType, String notification) {
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(confirmPassword);
        when(request.getParameter("userType")).thenReturn(userType);
        when(request.getParameter("notification")).thenReturn(notification);
    }
}
