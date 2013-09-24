package epood1.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
public class DBconnection {

	Logger log = Logger.getLogger(this.getClass());
	
	String pwd="";
	String usr="";
	String url="";
	Connection db_connection ;
	
	
	public DBconnection() {

		try{
			ResourceBundle bundle = ResourceBundle.getBundle("DBConnection");
		    Class.forName(bundle.getString("Driver"));
		    this.url = bundle.getString("url");
		    this.usr = bundle.getString("usr");
		    this.pwd = bundle.getString("pwd");
		    //this.db_connection = DriverManager.getConnection(this.url, this.usr, this.pwd);
		    this.db_connection = DriverManager.getConnection(this.url, this.usr, this.pwd);
		} catch(Exception e) {
			log.error("dbconnection.dbconnection():" + e.getMessage());
		}
	}
	
	public Connection getConnection(){
		return this.db_connection;
	}

	public void close(){
		try{
			this.db_connection.close();
		} catch(Exception e) {
			log.error("dbconnection.close():", e);
		}
	}

	public void finalize(){
		try{
			//System.out.println("finalize():");
		} catch(Exception e) {
			log.error("dbconnection.finalize():", e);
		}
	}
}
