package app.product;

import app.exception.InvalidOrderQuantityException;

/**
 * 
 * @author Ganesh
 *
 */
public class ProductOrder implements Order {

	Product product;
	int quantity = 1;

	/**
	 * ProductOrder constructor
	 * 
	 * @param product
	 * @param quantity
	 */
	public ProductOrder(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set product order quantity. Quantity must be non-negative value. Throws
	 * InvalidOrderQuantityException for negative value.
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			throw new InvalidOrderQuantityException();
		}
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product Order:" + product.getDescription() + " Quantity:" + quantity;
	}
}
