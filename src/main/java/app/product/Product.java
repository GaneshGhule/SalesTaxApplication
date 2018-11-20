package app.product;

/**
 * 
 * @author ganesh
 *
 */
public interface Product {

	String getDescription();

	float getPrice();

	void setPrice(float price);

	boolean isImported();

	void setImported(boolean isImported);

	ProductType getProductType();

	boolean isExempted();

	float getPriceWithTax();

	float getSaleTax();
}
