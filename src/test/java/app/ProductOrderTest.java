package app;



import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import app.exception.InvalidOrderQuantityException;
import app.product.Order;
import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductOrder;
import app.product.ProductType;

/**
 * Product order test cases 
 * @author ganesh
 *
 */
public class ProductOrderTest {
	
	Product product = new ProductBuilder("Bed",ProductType.FURNITURE).setPrice(5.0f).setIsImported(false).build();
	
	/**
	 * Test order creation
	 */
	@Test
	public void testOrderCreation() {
		Order order = new ProductOrder(product);
		assertNotNull(order);
		assertNotNull(order.getProduct());
	}
	
	/**
	 * Test invalid order quantity
	 */
	@Test(expected= InvalidOrderQuantityException.class)
	public void testParseItemInvalidQuantity() {
		Order order = new ProductOrder(product);
		order.setQuantity(-1);
	}
}
