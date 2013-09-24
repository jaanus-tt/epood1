package epood1.model.data;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="product")
public class Product implements Serializable {

	private static final long serialVersionUID = -7325281561911437634L;
	
	@Id
	@Column(name = "product")
	private int productId;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated")
	private Date updated;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "producer")
	private String producer;
	
	@Column(name = "catalog")
	private String product_catalog;
	
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * @param product_catalog the product_catalog to set
	 */
	public void setProduct_catalog(String product_catalog) {
		this.product_catalog = product_catalog;
	}

	/**
	 * @return the product_catalog
	 */
	public String getProduct_catalog() {
		return product_catalog;
	}

	
}
