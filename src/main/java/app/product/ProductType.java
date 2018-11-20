package app.product;

/**
 * 
 * @author ganesh
 *
 */
public enum ProductType {
	BOOK(true), MEDICAL(true), FOOD(true), BEVERAGE(false), CLOTH(false), FOOTWEAR(false), ELECTRONIC(false),
	GROCERY(false), STATIONARY(false), MUSIC(false), SPORT(false), FURNITURE(false), TOY(false), BEAUTY(false);

	private boolean isExempted;

	private ProductType(boolean exepmted) {
		isExempted = exepmted;
	}

	public boolean isExempted() {
		return isExempted;
	}
}
