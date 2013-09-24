package epood1.control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import epood1.model.DAO.HibernateUtil;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
public class HibernateListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSession(); // Just call the static initializer of that class    
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSession().close(); // Free all resources
	}

	
	
}
