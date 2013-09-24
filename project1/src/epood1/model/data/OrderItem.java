package epood1.model.data;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import epood1.model.data.Product;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */


@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 3325120894174470680L;
	private final static NumberFormat NF = new DecimalFormat("0.0###");
	
	@Id
	@Column(name = "order_item")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="s_order_item")
    @SequenceGenerator(name="s_order_item", sequenceName="s_order_item", allocationSize=1)
	private int orderItem;
	
	@Column(name = "order_")
	private int order;
	
	@Column(name = "product")
	private int productId;
	
	@Column(name = "item_count")
	private int itemCount;
	
	@Column(name = "item_price")
	private double itemPrice;
	
	@ManyToOne
	@JoinColumn(name = "product", insertable = false, updatable = false)
	private Product product;
	
	/**
	 * @return the orderItem
	 */
	public int getOrderItem() {
		return orderItem;
	}
	/**
	 * @param orderItem the orderItem to set
	 */
	public void setOrderItem(int orderItem) {
		this.orderItem = orderItem;
	}
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
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
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}
	/**
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	/**
	 * @return the itemPrice
	 */
	public double getItemPrice() {
	//	DecimalFormat df = new DecimalFormat("#.##");
	//	NumberFormat formatter = new DecimalFormat("##.###");
	//	double newPrice = Math.round(itemPrice*100.00)/100.00;
	//	return Double.valueOf(df.format(itemPrice).replaceAll(",", "."));
		return itemPrice;
	}
	/**
	 * @param itemPrice 
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	
}
