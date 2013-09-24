package epood1.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import epood1.model.data.Product;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */

@Entity
@Table(name = "product_catalog")
public class ProductCatalog implements Serializable {

	private static final long serialVersionUID = -4689558509929038452L;
	
	@Id
	@Column(name = "product_catalog")
	private int productCatalog;
	
	@Column(name = "struct_unit")
	private int structUnit;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany
	@OrderBy("price")
	@JoinTable(name = "product_product_catalog", joinColumns = { @JoinColumn(name = "product_catalog") }, inverseJoinColumns = { @JoinColumn(name = "product") })
	private List<Product> products = new ArrayList<Product>();
	
	/**
	 * @return the productCatalog
	 */
	public int getProductCatalog() {
		return productCatalog;
	}
	/**
	 * @param productCatalog the productCatalog to set
	 */
	public void setProductCatalog(int productCatalog) {
		this.productCatalog = productCatalog;
	}
	/**
	 * @return the structUnit
	 */
	public int getStructUnit() {
		return structUnit;
	}
	/**
	 * @param structUnit the structUnit to set
	 */
	public void setStructUnit(int structUnit) {
		this.structUnit = structUnit;
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
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
