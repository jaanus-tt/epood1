package epood1.model.DAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import epood1.model.data.Product;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public class ProductDAO {

	Logger log = Logger.getLogger(this.getClass());
	
    @SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		HibernateUtil.getSession().beginTransaction();
	    return HibernateUtil.getSession().createQuery("FROM Product ORDER BY name").list();
    }
    
    /**
     * getProduct 
     * @param id
     * @return Product
     */
    public Product getProduct(int id) {
    	HibernateUtil.getSession().beginTransaction();
    	return (Product) HibernateUtil.getSession().createQuery("FROM Product WHERE product=" + id).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Product> getProductsByCriteria(HttpServletRequest req) throws Exception {	
    	HibernateUtil.getSession().beginTransaction();
        String query = "FROM Product";

        String criteria 		 = req.getParameter("criteria");
        String description 		 = req.getParameter("description");
        String productCategoryId = req.getParameter("cId");
        String createdStart 	 = req.getParameter("createdStart");
        String createdEnd		 = req.getParameter("createdEnd");
        String updatedStart		 = req.getParameter("updatedStart");
        String updatedEnd 		 = req.getParameter("updatedEnd");
        String priceStart 		 = req.getParameter("priceStart");
        String priceEnd 		 = req.getParameter("priceEnd");

        boolean trueNeeded = false;
        
          if (!"".equals(criteria) || !"".equals(productCategoryId) || !"".equals(description)
                || !"".equals(createdStart) || !"".equals(createdEnd)
                || !"".equals(updatedStart) || !"".equals(updatedEnd)
                || !"".equals(priceStart)   || !"".equals(priceEnd))
            {
              query += " WHERE";
              trueNeeded = true;
            }
        if (!"".equals(criteria) && criteria != null) {
            query += " (Lower(name) LIKE '%" + criteria.toLowerCase() + "%') AND ";
        }
        if (!"".equals(description) && description != null) {
        	query += " Lower(description) LIKE '%" + description.toLowerCase() + "%') AND ";
        }
        if (!"".equals(productCategoryId) && productCategoryId != null && !"0".equals(productCategoryId)) {
            query += " catalog=" + productCategoryId + " AND ";
        }
        if (!"".equals(createdStart) && createdStart != null) {
            query += " created>'" + createdStart + "' AND ";
        }
        if (!"".equals(createdEnd) && createdEnd != null) {
            query += " created<'" + createdEnd + "' AND ";
        }
        if (!"".equals(updatedStart) && updatedStart != null) {
            query += " updated>'" + updatedStart + "' AND ";
        }
        if (!"".equals(updatedEnd) && updatedEnd != null) {
            query += " updated<'" + updatedEnd + "' AND ";
        }
        if (!"".equals(priceStart) && priceStart != null) {
            query += " price > " + priceStart + " AND ";
        }
        if (!"".equals(priceEnd) && priceEnd != null) {
            query += " price < " + priceEnd + " AND ";
        }
        if (trueNeeded) {
            query += " 1=1";
        }
        log.info(query);

        query += " ORDER BY name";
        try {
            return HibernateUtil.getSession().createQuery(query).list();
        } catch (final Exception e) {
        	  HibernateUtil.getSession().getTransaction().rollback();
          throw e;
        }
    }
    
    public void finalize() {
		try {
			HibernateUtil.getSession().close();
		} catch (HibernateException e) {
			HibernateUtil.getSession().getTransaction().rollback();
			log.error("ProductDao.finalize(): ",e);
		}
	}
    
}
