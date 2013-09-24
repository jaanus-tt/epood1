package epood1.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epood1.model.DAO.OrderDAO;
import epood1.model.data.Order;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public class SearchOrderController implements Controller {
	
	String view = null;
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		view = "searchOrder";
		if (request.getParameter("orderId")!= null) {
			Order order = new OrderDAO().getOrder(Integer.valueOf(request.getParameter("orderId")));
			request.setAttribute("order", order);
			view = "orderItems";
		}
		return view;
	}

}
