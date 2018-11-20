package app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.exception.InvalidProductPriceException;
import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductType;
import app.util.CommonUtil;

/**
 * Product test cases 
 * @author ganesh
 *
 */
public class ProductBuilderTest {
	
	Product product = new ProductBuilder("Bed",ProductType.FURNITURE).setPrice(5.0f).setIsImported(false).build();
	Product product1 = new ProductBuilder("chocolate",ProductType.FOOD).setPrice(15.0f).setIsImported(true).build();
	Product product2 = new ProductBuilder("Shoes",ProductType.FOOTWEAR).setPrice(2000.0f).setIsImported(true).build();
	
	/**
	 * Test product creation with negative price.
	 */
	@Test(expected = InvalidProductPriceException.class)
	public void testProductWithNegativePrice(){
		product.setPrice(-5.0f);
	}
	
	/**
	 * Test product creation with negative price.
	 */
	@Test
	public void testProductTaxCalculation(){
		product.setPrice(10);
		assertEquals(CommonUtil.roundOff(1f), product.getSaleTax(),0.0f);
	}
	
	/**
	 * Test product creation with negative price.
	 */
	@Test
	public void testProductPriceWithTax(){
		product.setPrice(10);
		assertEquals(CommonUtil.roundOff(11f), product.getPriceWithTax(),0.0f);
	}
	
	/**
	 * Test imported product tax calculation
	 */
	@Test
	public void testExemptedAndImportedProductPriceWithTax(){
		assertEquals(CommonUtil.roundOff(0.75f), CommonUtil.roundOff(product1.getSaleTax()),0.0f);
	}
	
	/**
	 * Test imported product tax calculation
	 */
	@Test
	public void testImportedProductPriceWithTax(){
		assertEquals(CommonUtil.roundOff(300f), CommonUtil.roundOff(product2.getSaleTax()),0.0f);
	}
	
	@Test
	public void testProductExemption(){
		product.setPrice(10);
		assertEquals(false, product.isExempted());
	}
}
