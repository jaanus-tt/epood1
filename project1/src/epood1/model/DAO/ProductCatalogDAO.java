package epood1.model.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.persistence.OrderBy;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import epood1.model.DAO.DBconnection;
import epood1.model.data.Product;
import epood1.model.data.ProductCatalog;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
public class ProductCatalogDAO {

	Logger log = Logger.getLogger(this.getClass());
	
	/*
	@SuppressWarnings("unchecked")
	public List<ProductCatalog> getAllCatalogs() {	
		HibernateUtil.getSession().beginTransaction();
	    return HibernateUtil.getSession().createQuery(
	        "FROM ProductCatalog ORDER BY name").list();
	}
	*/
	
	// Lisame siia JDBC Ühenduse
	public List<ProductCatalog> getAllCatalogs() {
		List<ProductCatalog> catalogList = null;

		try {
			Connection connect = new DBconnection().getConnection();
			Statement  statement = connect.createStatement();
			String sql = "SELECT product_catalog, name " +
					     "FROM product_catalog ORDER BY name";
			ResultSet result = statement.executeQuery(sql);
			catalogList = new ArrayList<ProductCatalog>();
			while(result.next()) {
				ProductCatalog catalog = new ProductCatalog();
				catalog.setName(result.getString("name"));
				catalog.setProductCatalog(result.getInt("product_catalog"));
				catalogList.add(catalog);
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catalogList;
	}	
		
	
	
	
	@OrderBy("name")
	public List<Product> getProductsInCatalog(int catalogId) {
		HibernateUtil.getSession().beginTransaction();
	    return getCatalogById(catalogId).getProducts();
	  }
	
	public ProductCatalog getCatalogById(int catalogId) {
		HibernateUtil.getSession().beginTransaction();
	    return (ProductCatalog) HibernateUtil.getSession().createQuery(
	        "FROM ProductCatalog WHERE product_catalog = " + catalogId).uniqueResult();
	  }
	
	public void finalize() {
		try {
			HibernateUtil.getSession().close();
		} catch (HibernateException e) {
			HibernateUtil.getSession().getTransaction().rollback();
			System.out.println("Cannot close the session");
		}
	}
	
}


