package app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import app.product.Order;
import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductOrder;
import app.product.ProductType;

/**
 * 
 * @author ganesh
 *
 */
public class CommonUtil {

	private static final float ROUNDOFF = 0.05f;

	/**
	 * Returns the value rounded up to the nearest 0.05.
	 * 
	 * @param value
	 * @return
	 */
	public static float roundOff(float value) {
		return (float) Math.ceil(value / ROUNDOFF) * ROUNDOFF;
	}

	/**
	 * Round up value
	 * 
	 * @param value
	 * @return
	 */
	public static double roundPrice(float value) {

		return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();

	}

	/**
	 * Read single order item.
	 * 
	 * @param productTypStr
	 * @param name
	 * @param quantityStr
	 * @param priceStr
	 * @param imported
	 * @return
	 */
	public static Order parseItem(String productTypStr, String name, String quantityStr, String priceStr,
			String imported) {
		ProductType productType = ProductType.valueOf(productTypStr.toUpperCase());
		float price = Float.parseFloat(priceStr);
		int quantity = Integer.parseInt(quantityStr);
		boolean isImported = false;
		if (imported.equalsIgnoreCase("yes")) {
			isImported = true;
		}

		Product product = new ProductBuilder(name, productType).setPrice(price).setIsImported(isImported).build();
		Order order = new ProductOrder(product);
		order.setQuantity(quantity);
		return order;
	}

	/**
	 * Read items input file and return order list.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Order> parseItems(String fileName) throws IOException {
		ArrayList<Order> orders = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/app/" + fileName))) {
			String str;
			int count = 0;
			while ((str = br.readLine()) != null) {
				count++;
				// TODO detect key value pair based on column names.
				if (count == 1) {
					continue;
				}
				String[] values = str.split(",");
				orders.add(parseItem(values[0], values[1], values[2], values[3], values[4]));
			}
		} catch (IOException e) {
			throw e;
		}
		return orders;
	}
}
