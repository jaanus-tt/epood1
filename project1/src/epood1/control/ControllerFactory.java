	package epood1.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */
public class ControllerFactory {

	public Controller create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
	    Logger log = Logger.getLogger(this.getClass());
		Controller Controller = null;
		   
	    try {
	    	if (request.getParameter("mode") != null) {
	    		if (request.getParameter("username") != null || request.getParameter("mode").equals("logout")) {
					Controller = new AuthenticationController();
				} else if (request.getParameter("mode").equals("product")) {
			    	Controller = new SearchProductController();
				} else if (request.getParameter("mode").equals("cart")) {
			    	Controller = new CartController();
				} else if (request.getParameter("mode").equals("ordersearch")) {
			    	Controller = new SearchOrderController();
				}
			} else {
				Controller = new SearchProductController();
			}
	    	} catch (Exception ex) {
	    		log.error("ControllerFactory.service():",ex);
	        }
	     return Controller;
	}
	
}
