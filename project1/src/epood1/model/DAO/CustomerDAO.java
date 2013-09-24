package epood1.model.DAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import epood1.model.data.Customer;
import epood1.model.data.CustomerAddress;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
public class CustomerDAO {
	

	Logger log = Logger.getLogger(this.getClass());
	
	
	
	public Customer getAuthCustomer(String username, String passw) {
		HibernateUtil.getSession().beginTransaction();
		return (Customer) HibernateUtil.getSession().createQuery(" FROM Customer WHERE username='" + username + "' AND passw=md5('" + passw + "')").uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	public List<CustomerAddress> getCustomerAddresses(int customerId) {
		HibernateUtil.getSession().beginTransaction();
	    return HibernateUtil.getSession().createQuery("FROM CustomerAddress WHERE customer=" + customerId).list();
	  }
	
	public void finalize() {
		try {
			HibernateUtil.getSession().close();
		} catch (HibernateException e) {
			HibernateUtil.getSession().getTransaction().rollback();
			log.error("Cannot close the hibernate session");
		}
	}
	
}
