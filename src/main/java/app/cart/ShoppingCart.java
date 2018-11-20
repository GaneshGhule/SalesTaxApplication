package app.cart;

import java.util.Objects;
import java.util.Currency;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import app.bill.BillReceipt;
import app.bill.BillReceiptPrinter;
import app.exception.InvalidOrderException;
import app.product.Order;
import app.product.Product;

/**
 * 
 * @author ganesh
 *
 */
public class ShoppingCart implements Cart {

	/**
	 * Not required in this class
	 */
	// private Currency currency;

	private BillReceipt receipt;

	private final ConcurrentHashMap<Product, Order> orderStore = new ConcurrentHashMap<>();

	Predicate<Object> isNullObj = Objects::isNull;

	public ShoppingCart(Currency currency) {

		// this.currency = currency;

		this.receipt = new BillReceiptPrinter(currency);
	}

	/**
	 * Add product order in cart. If order already exist in cart then increment
	 * quantity of order.
	 */
	@Override
	public void addOrder(Order order) {
		Predicate<Object> isNullObj = Objects::isNull;
		if (isNullObj.test(order) || isNullObj.test(order.getProduct())) {
			throw new InvalidOrderException();
		}

		orderStore.merge(order.getProduct(), order, (oldOrder, newOrder) -> {
			oldOrder.setQuantity(oldOrder.getQuantity() + newOrder.getQuantity());
			return oldOrder;
		});
	}

	/**
	 * Return the size of store.
	 * 
	 * @return
	 */
	public int getSize() {
		return orderStore.size();
	}

	/**
	 * Calculate Total bill amount and sales tax
	 * 
	 * @param onlyTax
	 * @param totalWithoutTax
	 * @return
	 */
	public float calculateTotal(boolean onlyTax, boolean totalWithoutTax) {
		float totalCost = 0.0f;
		float totalSalesTax = 0.0f;

		for (Entry<Product, Order> entry : orderStore.entrySet()) {
			Product product = entry.getKey();
			Order order = entry.getValue();

			totalCost += product.getPriceWithTax() * order.getQuantity();
			totalSalesTax += product.getSaleTax() * order.getQuantity();
		}

		if (onlyTax) {
			return totalSalesTax;
		}
		if (totalWithoutTax) {
			return totalCost - totalSalesTax;
		}

		return totalCost;
	}

	/**
	 * Shopping cart total bill amount with sales tax.
	 */
	@Override
	public float getTotalCost() {
		return calculateTotal(false, false);
	}

	/**
	 * Shopping cart total sales tax.
	 */
	@Override
	public float getSalesTax() {
		return calculateTotal(true, false);
	}

	/**
	 * Shopping cart total bill amount without sales tax.
	 */
	@Override
	public float getTotalCostWithoutTax() {
		return calculateTotal(false, true);
	}

	/**
	 * Print bill receipt for shopping cart items.
	 */
	@Override
	public void printReceipt() {
		receipt.printHeader();

		float totalCost = 0.0f;
		float totalSalesTax = 0.0f;

		for (Entry<Product, Order> entry : orderStore.entrySet()) {
			Product product = entry.getKey();
			Order order = entry.getValue();
			receipt.printOrder(order);
			totalCost += product.getPriceWithTax() * order.getQuantity();
			totalSalesTax += product.getSaleTax() * order.getQuantity();
		}

		receipt.printTotal(totalCost, totalSalesTax);
		receipt.printFooter();
	}

	@Override
	public void reset() {
		orderStore.clear();
	}
}
