package app;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import app.cart.ShoppingCart;
import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductOrder;
import app.product.ProductType;
import app.util.CommonUtil;
import exception.InvalidOrderQuantityException;

/**
 * shopping cart test cases
 * @author ganesh
 *
 */
public class ShoppingCartTest {

	private final Currency currency = Currency.getInstance(new Locale("EN", "IN"));
	ShoppingCart store = new ShoppingCart(currency);
	Product product = new ProductBuilder("Bed",ProductType.FURNITURE).setPrice(100).setIsImported(false).build();
	
	/**
	 * Test add order in cart
	 */
	@Test
	public void testAddOrder(){
		ProductOrder order = new ProductOrder(product);
		store.addOrder(order);
		assertEquals(1, store.getSize());
	}
	
	/**
	 * Test Total cost calculation
	 */
	@Test
	public void testTotalCostCalculation(){
		ProductOrder order = new ProductOrder(product);
		store.addOrder(order);
		assertEquals(1, store.getSize());
		assertEquals(CommonUtil.roundOff(110f), CommonUtil.roundOff(store.getTotalCost()),0.0f);
	}
	
	/**
	 * Test Total sales tax calculation
	 */
	@Test
	public void testTotalTaxCalculation(){
		ProductOrder order = new ProductOrder(product);
		store.addOrder(order);
		assertEquals(1, store.getSize());
		assertEquals(CommonUtil.roundOff(10f), CommonUtil.roundOff(store.getSalesTax()),0.0f);
	}
	
	
	/**
	 * Test product order with zero quantity
	 */
	@Test(expected = InvalidOrderQuantityException.class)
	public void testProductOrderQuantity(){
		ProductOrder order = new ProductOrder(product);
		order.setQuantity(0);
	}
}
