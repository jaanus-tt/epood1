package epood1.model.DAO;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;

import epood1.model.data.Order;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 16, 2013
 */
public class OrderDAO {

	private static Logger log = Logger.getLogger(OrderDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersForCustomer(int customerId) {
		HibernateUtil.getSession().beginTransaction();
	    return HibernateUtil.getSession().createQuery(
	        "FROM Order WHERE customer = " + customerId).list();
	}
	
	public Order getOrder(int orderId) {
		HibernateUtil.getSession().beginTransaction();
	    return (Order) HibernateUtil.getSession().createQuery(
	        "FROM Order WHERE order_ = " + orderId).uniqueResult();
	}
	
	
	public String getMaxOrder() {
		  HibernateUtil.getSession().beginTransaction();
		  Criteria criteria = HibernateUtil.getSession().createCriteria(Order.class);
		  criteria = criteria.createAlias("orderNumber", "order_number"); 
		  criteria.setProjection(Projections.max("order_number"));
		  System.out.println("getMaxOrder:" + criteria.uniqueResult());
	    return (String) criteria.uniqueResult();
	    
	   
			
			
	/*		DetachedCriteria avgWeight = DetachedCriteria.forClass(Cat.class)
    		.setProjection( Property.forName("weight").avg() );
				session.createCriteria(Cat.class)
    			.add( Property.forName("weight").gt(avgWeight) )
    			.list();
		*/	    
	}
	
	
	public void saveOrder(Order o) throws Exception {
		 HibernateUtil.getSession().beginTransaction();
		 try {
		    HibernateUtil.getSession().save(o);
		 } catch (RuntimeException e) {
		    log.error("Error saving order: " + e);
		    HibernateUtil.getSession().getTransaction().rollback();
		    throw e;
		 }
		 HibernateUtil.getSession().getTransaction().commit();
	}
	
	/* */
	public void finalize() {
		try {
			HibernateUtil.getSession().close();
		} catch (HibernateException e) {
			HibernateUtil.getSession().getTransaction().rollback();
			System.out.println("Cannot close the session");
		}
	}
	
}
