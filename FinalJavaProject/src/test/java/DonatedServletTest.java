import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.algonquin.cst8288.FinalJavaProject.bonusActivity.ItemDonatedServlet;

class DonatedServletTest {

	@Test
	void loadUserItemsSuccessfully() throws ServletException, IOException {
	    HttpServletRequest request = mock(HttpServletRequest.class);
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    HttpSession session = mock(HttpSession.class);
	    when(request.getParameter("action")).thenReturn("loadUserItems");
	    when(request.getSession(false)).thenReturn(session);
	    when(session.getAttribute("userId")).thenReturn(1); 
	    ItemDonatedServlet servlet = new ItemDonatedServlet();

	    servlet.doGet(request, response);

	    verify(request).setAttribute(eq("userItems"), anyList());
	    verify(request).getRequestDispatcher("/manageMyItems.jsp").forward(request, response);
	}


}
