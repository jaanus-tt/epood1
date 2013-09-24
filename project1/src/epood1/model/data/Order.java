package epood1.model.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import epood1.model.data.CustomerAddress;
import epood1.model.data.OrderItem;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */

@Entity
@Table(name = "order_")
public class Order implements Serializable {

	private static final long serialVersionUID = 2740185063L;

	@Id
	@Column(name = "order_")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="s_order")
    @SequenceGenerator(name="s_order", sequenceName="s_order", allocationSize=1)
    
	private int order;
	
	@Column(name = "order_status_type")
	private int orderStatusType;
	
	@Column(name = "customer")
	private int customer;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated")
	private Date updated;
	
	@Column(name = "customer_confirmed")
	private Date customerConfirmed;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "order_type")
	private int orderType;
	
	@Column(name = "shipping_address")
	private int shippingAddressId;
	
	@ManyToOne
	@JoinColumn(name = "shipping_address", referencedColumnName = "cst_address", insertable = false, updatable = false)
	private CustomerAddress shippingAddress;
	
	@OneToMany
	@JoinColumn(name = "order_")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
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
	 * @return the orderStatusType
	 */
	public int getOrderStatusType() {
		return orderStatusType;
	}
	/**
	 * @param orderStatusType the orderStatusType to set
	 */
	public void setOrderStatusType(int orderStatusType) {
		this.orderStatusType = orderStatusType;
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
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param umber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created date
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
	 * @return the customerConfirmed
	 */
	public String getCustomerConfirmed() {
		return new SimpleDateFormat("dd-MM-yyyy").format(customerConfirmed);
	}

	public Date getCustomerConfirmedDate() {
		return customerConfirmed;
	}
	/**
	 * @param customerConfirmed the customerConfirmed to set
	 */
	public void setCustomerConfirmed(Date customerConfirmed) {
		this.customerConfirmed = customerConfirmed;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the orderType
	 */
	public int getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the shippingAddressId
	 */
	public int getShippingAddressId() {
		return shippingAddressId;
	}
	/**
	 * @param shippingAddressId the shippingAddressId to set
	 */
	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}
	
	public List<OrderItem> getOrderItems() {
	    return orderItems;
	}
	
	public OrderItem getOrderItem(int productId) {
	    for (OrderItem orderItem : orderItems) {
	      if (orderItem.getProductId() == productId) {
	        return orderItem;
	      }
	    }
	    return null;
	}
	
	public boolean containsItem(int productId) {
	    for (final OrderItem orderItem : orderItems) {
	      if (orderItem.getProductId() == productId) {
	        return true;
	      }
	    }
	    return false;
	  }

	  public void removeItem(int productId) {
	    // Evading ConcurrentModificationException
	    List<OrderItem> itemsToRemove = new ArrayList<OrderItem>();
	    for (OrderItem orderItem : orderItems) {
	      if (orderItem.getProductId() == productId) {
	        itemsToRemove.add(orderItem);
	      }
	    }
	    orderItems.removeAll(itemsToRemove);
	  }

	  public double getOrderTotalPrice() {
	    double totalPrice = 0;
	    for (final OrderItem oi : orderItems) {
	   //   totalPrice += oi.getProduct().getPrice() * oi.getItemCount();  //hind toote originaalhinnaga
	    	totalPrice += oi.getItemCount() * oi.getItemPrice();
	    }
	    return totalPrice;
	  }
	  
	  
	  public double getRowTotalPrice(int productId) {
		    double totalPrice = 0;
		    for (final OrderItem oi : orderItems) {
		    	if (oi.getProductId() == productId) {
		    		totalPrice += oi.getItemCount() * oi.getItemPrice();
		    	}	
		    }
		    return totalPrice;
		  }
	
	  public int getAle(int productId) {
		  int ale = 0;
		  for (final OrderItem orderItem : orderItems) {
			  if (orderItem.getProductId() == productId) {	
				  ale = (int) ((1-(orderItem.getItemPrice() / orderItem.getProduct().getPrice() )) * 100);
			  }	  
		  }
		  return ale;
	  }
	  
		  
	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(CustomerAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	/**
	 * @return the shippingAddress
	 */
	public CustomerAddress getShippingAddress() {
		return shippingAddress;
	}
	
	@Override
	  public String toString() {
	    return "Order[" + orderItems + "]";
	}
	
}
