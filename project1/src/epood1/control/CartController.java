package epood1.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import epood1.model.data.Order;
import epood1.util.OrderFunctions;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */
public class CartController implements Controller {

	static Logger log = Logger.getLogger(CartController.class);
	private String view = null;
	private Order order;
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		view = "cart";
		
		if (request.getParameter("clearAll")!=null) {
			if (request.getSession(true).getAttribute("current_order")!=null){
				((Order) request.getSession(true).getAttribute("current_order")).getOrderItems().clear();
			}
		} else if (request.getParameter("checkedProducts")!=null) {
			new OrderFunctions().removeCheckedItems(request);
		} else if (request.getParameter("submit")!=null && request.getParameter("customerAddressId")!=null) {
			new OrderFunctions().saveOrder(order, view, request);
		} else if (request.getParameter("productId")!=null && request.getParameter("itemCount")!=null) {
			new OrderFunctions().insertOrUpdateOrder(order, request);
		}
		
		return view;
	}
	

}
