package epood1.model.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
@Entity
@Table(name = "cst_address")
public class CustomerAddress implements Serializable {

	private static final long serialVersionUID = -8410282669284630022L;

	@Id
	@Column(name = "cst_address")
	private int customerAddress;
	
	@Column(name = "customer")
	private int customer;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "house")
	private String house;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "town_county")
	private String townCounty;

	/**
	 * @return the customerAddress
	 */
	public int getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(int customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the customer
	 */
	public int getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(int customer) {
		this.customer = customer;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return townCounty+", "+address+", "+house;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the county
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param county the county to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the townCounty
	 */
	public String getTownCounty() {
		return townCounty;
	}

	/**
	 * @param townCounty the townCounty to set
	 */
	public void setTownCounty(String townCounty) {
		this.townCounty = townCounty;
	}
	
}
