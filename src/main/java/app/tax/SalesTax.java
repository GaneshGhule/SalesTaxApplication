package app.tax;

import app.product.Product;
import app.util.CommonUtil;

/**
 * 
 * @author ganesh
 *
 */
public class SalesTax implements Tax {

	/**
	 * Sales tax percentage for non exempted products.
	 */
	private static final float TAX_PERCENTAGE = 10.0f / 100;

	/**
	 * Additional tax for imported products.
	 */
	private static final float IMPORT_TAX = 5.0f / 100;
	
	/**
	 * Tax exempted 
	 */
	private static final float NO_TAX = 0.0f;
	
	/**
	 * Calculate sales tax for given product
	 */
	public float calculateTax(Product product) {
		 float taxPercentage = getTaxPercentage(product);
		 float salesTax  = product.getPrice() * taxPercentage;
		return CommonUtil.roundOff(salesTax);
	}

	/**
	 * Calculate tax percentage for product including additional import tax.
	 * 
	 * @param product
	 * @return
	 */
	
	public  float getTaxPercentage(Product product) {
		
		float taxPercentage;

		if(product.isExempted()) {
			taxPercentage = NO_TAX;
		}else {
			taxPercentage = TAX_PERCENTAGE;
		}
		
		if(product.isImported()) {
			taxPercentage = taxPercentage+IMPORT_TAX;
		}
		
		return taxPercentage;
	}
}
