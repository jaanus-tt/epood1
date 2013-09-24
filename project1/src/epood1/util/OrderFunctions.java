package epood1.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import epood1.model.DAO.OrderDAO;
import epood1.model.DAO.ProductDAO;
import epood1.model.data.Order;
import epood1.model.data.OrderItem;
import epood1.model.data.OrderType;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */
public class OrderFunctions {

	Logger log = Logger.getLogger(this.getClass());
	
	public String saveOrder(Order order, String view, HttpServletRequest request) {
		try {
	    	order = (Order) request.getSession(true).getAttribute("current_order");
	    	System.out.println("saveOrder käivitatud");
		    	int customerAddressIdParsed = Integer.valueOf(request.getParameter("customerAddressId"));
		    	int customer = (Integer) request.getSession(true).getAttribute("user");
		    	
		    	order.setCustomer(customer);
		    	order.setOrderType(OrderType.ORDER.getId());
		    	order.setOrderStatusType(OrderType.ORDER.getId());
		    	order.setShippingAddressId(customerAddressIdParsed);
		    	order.setCustomerConfirmed(new Date());
		    	order.setUpdated(new Date());
		    	order.setCreated(new Date());
		    	//order.setOrderNumber("56464");
		    	order.setNote(request.getParameter("note"));
		    	//order.setOrderNumber(new OrderDao().getMaxOrder());
		    	Long OrderNumber = System.currentTimeMillis() / 1000L; 
		    	order.setOrderNumber(String.valueOf(OrderNumber));
		    	
		    	new OrderDAO().saveOrder(order);
		    	order.getOrderItems().clear();
		    	view = "searchOrder";
	    } catch (Exception e) {
	      log.error("Cannot save order: ", e);
	    }
		
		return view;
	}
	
	public void removeCheckedItems(HttpServletRequest request) {
		String checkedItems = request.getParameter("checkedProducts");
		String[] checkedProductsArray = checkedItems.split(",");
		for (String productId : checkedProductsArray) {
			((Order) request.getSession(true).getAttribute("current_order")).removeItem(Integer.valueOf(productId));
		}
	}
	
	public void insertOrUpdateOrder(Order order, HttpServletRequest request) {
		try {
			int productIdParsed = Integer.parseInt(request.getParameter("productId"));
			int itemCountParsed = Integer.parseInt(request.getParameter("itemCount"));
			if (itemCountParsed > 0) {
				if (request.getSession(true).getAttribute("current_order")!=null){
					order = (Order) request.getSession(true).getAttribute("current_order");
				} else {
					order = new Order();
				}
				
				if (order.containsItem(productIdParsed)) {
			        order.getOrderItem(productIdParsed).setItemCount(itemCountParsed);
				} else {
					OrderItem orderitem = new OrderItem();
					orderitem.setItemCount(itemCountParsed);
					orderitem.setProductId(productIdParsed);
					orderitem.setProduct(new ProductDAO().getProduct(productIdParsed));
			        order.getOrderItems().add(orderitem);
				}
				if (request.getSession(true).getAttribute("current_order")==null)
					request.getSession(true).setAttribute("current_order",order); // if order created first time
			}
		} catch (NumberFormatException e) {
			log.error("Quantity must be in number format");
		} catch (Exception e) {
			log.error(e);
		}
	}
	
}
