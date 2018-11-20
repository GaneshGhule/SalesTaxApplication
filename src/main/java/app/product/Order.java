package app.product;

/**
 * 
 * @author ganesh
 *
 */
public interface Order {
	public Product getProduct();

	public int getQuantity();

	public void setQuantity(int quantity);
}
