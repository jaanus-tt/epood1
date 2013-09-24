package epood1.model.data;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 17, 2013
 */
public enum OrderType {

	OFFER(1), ORDER(2);

	private int id;

	OrderType(final int id) {
	  this.id = id;
	}

	public int getId() {
	  return id;
	}
	
}
