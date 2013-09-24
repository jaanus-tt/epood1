package epood1.model.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import epood1.model.data.CustomerAddress;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */

@Entity
@Table(name = "customer")
@SecondaryTable(name = "cst_user")
public class Customer implements Serializable{

	private static final long serialVersionUID = -852691408404083454L;

	@Id
	@Column(name = "customer")
	private int customer;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username", table = "cst_user")
	private String username;
	
	@Column(name = "passw", table = "cst_user")
	private String password;

	 @OneToMany
	 @JoinColumn(name = "customer")
	 private List<CustomerAddress> customerAddresses;
	
	/**
	 * @return the customer
	 */
	public int getCustomerId() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomerId(int customer) {
		this.customer = customer;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
	    return firstName + " " + lastName;
	  }

	@Override
	public String toString() {
	   return "Customer[" + firstName + " " + lastName + "]";
	}

	public List<CustomerAddress> getCustomerAddresses() {
	   return customerAddresses;
	}

	public void setCustomerAddresses(final List<CustomerAddress> customerAddresses) {
	   this.customerAddresses = customerAddresses;
	}

}
