package app.cart;

import app.product.Order;

/**
 * 
 * @author ganesh
 *
 */
public interface Cart {

	public void  addOrder(Order order);
	public float getTotalCostWithoutTax();
	public float getTotalCost();
	public float getSalesTax();
	public void printReceipt();
	public int getSize();
	public void reset();
}
