package epood1.control;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import epood1.model.DAO.ProductCatalogDAO;
import epood1.model.DAO.ProductDAO;
import epood1.model.data.Product;
import epood1.model.data.ProductCatalog;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public class SearchProductController implements Controller {

	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = null;
		if (request.getParameter("catalogId") != null) {
			ProductCatalog catalog = new ProductCatalogDAO().getCatalogById(Integer.valueOf(request.getParameter("catalogId")));
			request.setAttribute("catalog", catalog);
			view = "catalog";
		} else if (request.getParameter("productId") != null) {
			Product product = new ProductDAO().getProduct(Integer.valueOf(request.getParameter("productId")));
			request.setAttribute("product", product);
			view = "product";
		} else if (request.getParameter("form") != null) {
			view = "searchProductsForm";
		} else if (request.getParameter("search") != null) {
			try {
				List<Product> productList = new ProductDAO().getProductsByCriteria(request);
				ProductCatalog specialCatalog = new ProductCatalog();
				specialCatalog.setProducts(productList);
				request.setAttribute("specialCatalog", specialCatalog);
			} catch (Exception e) {
				log.error("SearchProductcontroller.service(): ",e);
			}
			view = "searchProductsForm";
		}
		
		return view;
	}

}
