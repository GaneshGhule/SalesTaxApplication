package app.product;

import app.exception.InvalidProductPriceException;

/**
 * 
 * @author Ganesh
 *
 */
public class ProductBuilder {
	
	/**
	 * Product displayName
	 */
	private String displayName;

	/**
	 * Product price
	 */
	private float price = 0.0f;

	/**
	 * Product category to check product is exempted or not
	 */
	private ProductType productType;

	/**
	 * Flag to check product is imported or not
	 */
	private boolean isImported = false;

	/**
	 * Constructor of ProductBuilder
	 * 
	 * @param displayName
	 * @param category
	 */
	public ProductBuilder(String displayName, ProductType productType) {
		this.displayName = displayName;
		this.productType = productType;
	}

	/**
	 * Construct product object.
	 * 
	 * @return Product
	 */
	public Product build() {
		return new ProductDef(displayName, productType, price, isImported);
	}

	/**
	 * Set product price.
	 */
	public ProductBuilder setPrice(float price) {
		if (price < 0) {
			throw new InvalidProductPriceException();
		}
		this.price = price;
		return this;
	}

	public ProductBuilder setIsImported(boolean isImported) {
		this.isImported = isImported;
		return this;

	}
}
