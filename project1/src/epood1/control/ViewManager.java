package epood1.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epood1.log.MyLogger;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public class ViewManager {

	public static void navigate(String view, HttpServletRequest request, HttpServletResponse response,ServletContext context) 
			throws ServletException, IOException  {

		try {

			if (view.equals("catalog")) {
				context.getRequestDispatcher("/jsp/products.jsp").forward(request, response);
			} else if (view.equals("product")){
				context.getRequestDispatcher("/jsp/product.jsp").forward(request, response);
			} else if (view.equals("cart")){
				context.getRequestDispatcher("/jsp/cartItems.jsp").forward(request, response);
			} else if (view.equals("searchOrder")){
				context.getRequestDispatcher("/jsp/orderHistory.jsp").forward(request, response);
			} else if (view.equals("orderItems")){
				context.getRequestDispatcher("/jsp/orderItems.jsp").forward(request, response);
			} else if (view.equals("searchProductsForm")){
				context.getRequestDispatcher("/jsp/productSearchForm.jsp").forward(request, response);
			}

		} catch (Exception ex) {
			MyLogger log = new MyLogger();
			log.Log("ViewManager.navigate():",ex.getMessage());
		}

	}

}
