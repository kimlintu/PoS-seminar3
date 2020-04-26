package model;

import model.dto.ItemInformation;
import model.dto.PriceInformation;

/**
 * Represents the total price of the ongoing sale. 
 * @author kim
 *
 */
public class TotalPrice {
	private Amount totalPriceAmount;
	private Amount totalVatAmount;
	
	/**
	 * Creates a new instance with an initial amount of 0.
	 */
	TotalPrice() {
		totalPriceAmount = new Amount(0);
		totalVatAmount = new Amount(0);
	}
	
	PriceInformation getPriceInfo() {
		return new PriceInformation(totalPriceAmount, totalVatAmount);
	}
	
	/**
	 * Increments the total price of the sale by the price of an item
	 * times the quantity of the item. Also adds the items VAT tax to
	 * the total.
	 * @param item Item whose price is getting added to the total. 
	 */
	void addToTotalPrice(Item item) {
		ItemInformation itemInfo = item.getItemInformation();
		Amount itemPrice = itemInfo.getPrice();
		
		totalPriceAmount = totalPriceAmount.add(itemPrice.multiply(itemInfo.getQuantity()));
		
		addToTotalVat(itemInfo.getVatTax().multiply(itemInfo.getQuantity()));
	}
	
	private void addToTotalVat(Amount itemVatAmount) {
		totalVatAmount = totalVatAmount.add(itemVatAmount);
	}
}
