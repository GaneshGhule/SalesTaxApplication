package app.product;

import java.util.Objects;

import app.exception.InvalidProductPriceException;
import app.tax.SalesTax;
import app.tax.Tax;

/**
 * 
 * @author ganesh
 *
 */
public class ProductDef implements Product {

	private static final Tax taxCalculator = new SalesTax();

	/**
	 * Product displayName
	 */
	private String displayName;

	/**
	 * Product price. This should be non-negative
	 */
	private float price;

	/**
	 * Product type to check exemption.
	 */
	private ProductType productType;

	/**
	 * Flag to check product is imported or not.
	 */
	private boolean isImported;

	/**
	 * Check product is exempted or not
	 */
	private boolean isExempted = false;

	public ProductDef(String displayName, ProductType productType, float price, boolean isImported) {
		this.displayName = displayName;
		this.price = price;
		this.productType = productType;
		this.isImported = isImported;
		this.isExempted = productType.isExempted();
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	@Override
	public boolean isImported() {
		return isImported;
	}

	@Override
	public boolean isExempted() {
		return isExempted;
	}

	@Override
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public String getDescription() {
		return productType + "-" + displayName + (isImported ? "(I)" : "");
	}

	@Override
	public float getPrice() {
		return price;
	}

	@Override
	public void setPrice(float price) {
		if (price < 0) {
			throw new InvalidProductPriceException();
		}
		this.price = price;
	}

	@Override
	public float getPriceWithTax() {
		return price + taxCalculator.calculateTax(this);
	}

	@Override
	public float getSaleTax() {
		return taxCalculator.calculateTax(this);
	}

	@Override
	public int hashCode() {
		int hasCode = 17;
		hasCode = 31 * hasCode + displayName.hashCode();
		hasCode = 31 * hasCode + productType.hashCode();
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj)) {
			return false;
		}
		if (obj instanceof Product) {
			ProductDef p = (ProductDef) obj;
			if (p.getDisplayName().equalsIgnoreCase(displayName) && productType == p.getProductType() && p.isImported == isImported) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return productType + "-" + "-" + displayName + "-" + (isImported ? "Imported" : "Not Imported");
	}
}
