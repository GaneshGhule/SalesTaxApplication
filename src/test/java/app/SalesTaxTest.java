package app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductType;
import app.tax.SalesTax;
import app.util.CommonUtil;

/**
 * Sales tax calculation test cases
 * @author ganesh
 *
 */
public class SalesTaxTest {

	Product product = new ProductBuilder("Bed",ProductType.FURNITURE).setPrice(12f).setIsImported(false).build();
	Product product1 = new ProductBuilder("chocolate",ProductType.FOOD).setPrice(15.0f).setIsImported(true).build();
	Product product2 = new ProductBuilder("Shoes",ProductType.FOOTWEAR).setPrice(2000.0f).setIsImported(true).build();
	Product product3 = new ProductBuilder("Almond",ProductType.FOOD).setPrice(1500.0f).setIsImported(false).build();
	
	/**
	 * Test tax calculation for non-exempted product.
	 */
	@Test
	public void testSalesTaxCalculation(){
		assertEquals(1.20f, product.getSaleTax(),0.000f);
	}
	
	/**
	 * Test tax calculation for non-exempted and  imported product.
	 */
	@Test
	public void testSalesTaxCalculationWithAdditionalTax(){
		assertEquals(CommonUtil.roundOff(300f), CommonUtil.roundOff(product2.getSaleTax()),0.000f);
	}
	
	/**
	 * Test tax calculation for exempted product.
	 */
	@Test
	public void testSalesTaxCalculationWithExcepted(){
		assertEquals(0.0f, product3.getSaleTax(),0.000f);
	}
	

	/**
	 * Test tax calculation for exempted and imported product.
	 */
	@Test
	public void testSalesTaxCalculationWithImported(){
		assertEquals(0.75f, product1.getSaleTax(),0.000f);
	}
	
	/**
	 * Test tax calculation for zero price product.
	 */
	@Test
	public void testSalesTaxCalculationNegative(){
		Product product = new ProductBuilder("Bed",ProductType.FURNITURE)
				.setPrice(0)
				.setIsImported(false)
				.build();
		assertEquals(0.0f, product.getSaleTax(),0.000f);
	}
	
	/**
	 * Test Tax percentage calculation
	 */
	@Test
	public void testTaxPercentage() {
		SalesTax taxCal = new SalesTax();
		assertEquals(0.10f, CommonUtil.roundOff(taxCal.getTaxPercentage(product)),0.0f);
		assertEquals(0.05f, CommonUtil.roundOff(taxCal.getTaxPercentage(product1)),0.0f);
		assertEquals(0.15f, CommonUtil.roundOff(taxCal.getTaxPercentage(product2)),0.0f);
		assertEquals(0.0f, CommonUtil.roundOff(taxCal.getTaxPercentage(product3)),0.0f);
	}
}
