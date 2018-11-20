package app.bill;

import app.product.Order;

/**
 * 
 * @author ganesh
 *
 */
public interface BillReceipt {

	void  printHeader();
	void printFooter();
	void printTotal(float total,float totalTax);
	void  printOrder(Order order);
}
