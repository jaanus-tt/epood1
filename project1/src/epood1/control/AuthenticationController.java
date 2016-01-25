package epood1.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import epood1.model.DAO.CustomerDAO;
import epood1.model.data.Customer;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */

//Krister tegi ka midagi siin! ;)
	// Krister teeb Heleni palvel muudatuse

public class AuthenticationController implements Controller {

	private String username = "";
	private String passw = "";
	private Customer customer = null ;
	private String view = null;
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("mode").equals("logout")) {
			HttpSession destroyable_session = request.getSession(false);
			if (destroyable_session != null) 
				destroyable_session.invalidate();
		}
		
		HttpSession session = request.getSession(true);
		
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("password") != null) {
			passw = request.getParameter("password");
		}
		
		if ((!(username.equals(""))) && (!(passw.equals("")))) {
			customer = new CustomerDAO().getAuthCustomer(username, passw);
			if (customer != null ) {
				session.setAttribute("user", customer.getCustomerId());
				session.setAttribute("username", customer.getFullName());
				view = "catalog";
			}  else
				view = "catalog";
		} else
			view = "catalog";
		
		return view;
	}

	
	
}
