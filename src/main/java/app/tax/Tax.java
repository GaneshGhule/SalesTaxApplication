package app.tax;

import app.product.Product;

/**
 * 
 * @author ganesh
 *
 */
public interface Tax {
	float calculateTax(Product product);
}
