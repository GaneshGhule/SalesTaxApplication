package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import app.product.Order;
import app.product.ProductType;
import app.util.CommonUtil;
import exception.InvalidOrderQuantityException;
import exception.InvalidProductPriceException;

/**
 * Utility class test cases
 * @author ganesh
 *
 */
public class CommonUtilTest {

	/**
	 * Test round off value
	 */
	@Test
	public void testRoundOff() {
		assertEquals(CommonUtil.roundOff(1.80f),CommonUtil.roundOff(1.79000034f),0.000f);
	}
	
	/**
	 * Test valid order parsing
	 */
	@Test
	public void testParseItem() {
		Order order = CommonUtil.parseItem(ProductType.BEAUTY.toString(), "Perfume", "1", "110.50", "yes");
		assertNotNull(order);
		assertNotNull(order.getProduct());
		assertEquals(1, order.getQuantity());
		assertEquals(true, order.getProduct().isImported());
		assertEquals(CommonUtil.roundOff(110.50f), order.getProduct().getPrice(),0.0f);
	}
	
	/**
	 * Test invalid order quantity parsing
	 */
	@Test(expected=InvalidOrderQuantityException.class)
	public void testParseItemInvalidQuantity() {
		Order order = CommonUtil.parseItem(ProductType.BEAUTY.toString(), "Perfume", "-1", "110.50", "yes");
		assertNotNull(order);
	}
	
	/**
	 * Test invalid price parsing
	 */
	@Test(expected=InvalidProductPriceException.class)
	public void testParseItemInvalidPrice() {
		Order order = CommonUtil.parseItem(ProductType.BEAUTY.toString(), "Perfume", "1", "-110.50", "yes");
		assertNotNull(order);
		assertNotNull(order.getProduct());
	}

	/**
	 * Test input file parsing
	 * @throws IOException
	 */
	@Test
	public void testParseItems(){
		try {
			ArrayList<Order> list = CommonUtil.parseItems("input.csv");
			assertEquals(5, list.size());
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Test file not found for file parsing
	 * @throws IOException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testParseItemsFileNotFound() throws IOException{
		CommonUtil.parseItems("input1.csv");
	}
	
}
