package app.bill;

import java.util.Currency;

import app.product.Order;
import app.product.Product;
import app.util.CommonUtil;

/**
 * 
 * @author ganesh
 *
 */
public class BillReceiptPrinter implements BillReceipt {

	private Currency currency;

	public BillReceiptPrinter(Currency currency) {
		this.currency = currency;
	}

	@Override
	public void printHeader() {
		System.out.println();
		System.out.println();
		System.out.println(
				"-------------------------------------------------------Bill Receipt---------------------------------------------------------------------------------");
		System.out.println(String.format("%30s %30s %30s %30s", "Item name", "Item Quantity", "Sales Tax",
				"Item price(Including tax)"));
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void printFooter() {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Thank you");
	}

	@Override
	public void printTotal(float total, float totalTax) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Sales tax  :" + currency.getSymbol() + " " + CommonUtil.roundOff(totalTax));
		System.out
				.println("Total Bill amount :  " + currency.getSymbol() + " " + CommonUtil.roundOff(total - totalTax));
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Total Bill amount : " + currency.getSymbol() + " "
				+ CommonUtil.roundPrice(CommonUtil.roundOff(total)) + " (Including tax)");
	}

	@Override
	public void printOrder(Order order) {
		Product p = order.getProduct();
		float tax = CommonUtil.roundOff(p.getSaleTax() * order.getQuantity());
		float priceWithTax = CommonUtil.roundOff(p.getPriceWithTax() * order.getQuantity());
		System.out.println(
				String.format("%30s %30s %30s %30s", p.getDescription(), order.getQuantity(), tax, priceWithTax));
	}

}
