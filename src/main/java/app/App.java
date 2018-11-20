package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import app.cart.Cart;
import app.cart.ShoppingCart;
import app.product.Order;
import app.product.ProductType;
import app.util.CommonUtil;

/**
 * Sales Tax application
 * @author ganesh
 *
 */
public class App 
{
	/**
	 * Handle Internationalization
	 */
	private final Currency currency = Currency.getInstance(new Locale("EN", "IN"));
	
	/**
	 * Shopping cart for parse items or input items
	 */
	private Cart cart = new ShoppingCart(currency);
	
	/**
	 * Item Input counter
	 */
	private int itemCount = 0;
	
    public static void main( String[] args) 
    {
    	if(args.length == 2 && args[0].equals("fileName")) {
    		App.readInputFromFile(args);
    	}else {		
    		App.readInputFromCli();
    	}
    }
  
	/**
	 * Read input from cli 
	 */
    private static void readInputFromCli() {
    	App app = new App();
  
		// Print valid product categories.
		app.printValidCategories();
		
		System.out.println("Please enter you bill items :");
		
		Scanner keyboard = new Scanner(System.in);
		ReadInput ri = new ReadInput(keyboard);
		
		while (true) {
			// Read item
			Order order = app.readItem(ri);
			
			// Store item
			app.storeItem(order);
			
			//Check want to read new item
			if(!app.readNextItem(keyboard)) {
				break;
			}
		}
		
		//print bill of read items
		
		app.printBill();	
    }
    
    private static void readInputFromFile(String []args) {
    	App app = new App();
    	try {
			ArrayList<Order> list = CommonUtil.parseItems(args[1]);
    		list.forEach(order -> {
    			app.storeItem(order);
    		});
    		app.printBill();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Read item from cli
     * @param input
     * @return
     */
	private Order readItem(ReadInput input) {
		itemCount++;
		return input.readItem(itemCount);
	}
	
	/**
	 * Store read item in cart.
	 * @param order
	 */
	private void storeItem(Order order) {
		if(Objects.nonNull(order)) {
			cart.addOrder(order);	
		}
	}
	
	/**
	 * Print bill receipt of shopping cart items.
	 */
	private void printBill() {
		if(cart.getSize() <= 0) {
			return;
		}
		cart.printReceipt();
	}
	
	/**
	 * Check want to read new item or not.
	 * @param keyboard
	 * @return
	 */
	private boolean readNextItem(Scanner keyboard) {
		System.out.print("Do you have more Items [y/n]: ");
		String input = keyboard.nextLine();
		if (input.toLowerCase().equals("n")) {	
			return false;
		}
		return true;
	}
	
	/**
	 * Print valid product category names
	 */
	private void  printValidCategories() {
		System.out.println(" Valid product category are ");
		for (ProductType cat : ProductType.values()) {
			System.out.print(cat + ",");
		}
		System.out.println();
		System.out.println();
	}
}
