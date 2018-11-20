package app;

import java.util.Objects;
import java.util.Scanner;

import app.product.Order;
import app.product.Product;
import app.product.ProductBuilder;
import app.product.ProductOrder;
import app.product.ProductType;

public class ReadInput {

	private Scanner keyboard;

	public ReadInput(Scanner keyboard) {
		this.keyboard = keyboard;
	}

	/**
	 * Read item category name input.
	 * 
	 * @param itemCount
	 * @return
	 */
	private ProductType readProductType(int itemCount) {
		System.out.print("Item " + itemCount + " Type: ");
		ProductType category = ProductType.valueOf(keyboard.nextLine().toUpperCase());
		if (Objects.isNull(category)) {
			throw new IllegalArgumentException("Invalid item category passed");
		}
		return category;
	}

	/**
	 * Read item name input.
	 * 
	 * @param itemCount
	 * @return
	 */
	private String readName(int itemCount) {
		System.out.print("Item " + itemCount + " Name: ");
		String name = keyboard.nextLine();
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Invalid item name passed");
		}
		return name;
	}

	/**
	 * Read item import input.
	 * 
	 * @param itemCount
	 * @return
	 */
	private boolean readImported(int itemCount) {
		System.out.print("Is Item " + itemCount + " Imported[y/n]: ");
		String input = keyboard.nextLine();
		return input.equalsIgnoreCase("y");
	}

	/**
	 * Read item price input
	 * 
	 * @param itemCount
	 * @return
	 */
	private float readPrice(int itemCount) {
		System.out.print("Item " + itemCount + " Price: ");
		return Float.parseFloat(keyboard.nextLine());
	}

	/**
	 * Read item quantity input
	 * 
	 * @param itemCount
	 * @return
	 */
	private int readQuantity(int itemCount) {
		System.out.print("Item " + itemCount + " Quantity: ");
		return Integer.parseInt(keyboard.nextLine());
	}

	/**
	 * Read new item from cli.
	 * 
	 * @param itemCount
	 * @return
	 */
	public Order readItem(int itemCount) {
		ProductType category;
		String name;
		int quantity;
		float price;
		boolean isImported;

		try {
			category = readProductType(itemCount);
			name = readName(itemCount);
			quantity = readQuantity(itemCount);
			price = readPrice(itemCount);
			isImported = readImported(itemCount);

			Product product = new ProductBuilder(name, category).setPrice(price).setIsImported(isImported).build();

			ProductOrder order = new ProductOrder(product);
			order.setQuantity(quantity);
			return order;
		} catch (Exception e) {
			System.out.println("Failed to read item :" + itemCount + " " + e.getMessage());
		}
		return null;
	}
}
