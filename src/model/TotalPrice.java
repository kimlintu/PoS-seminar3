package model;

import model.dto.ItemInformation;
import model.dto.PriceInfo;

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
	
	PriceInfo getPriceInfo() {
		return new PriceInfo(totalPriceAmount, totalVatAmount);
	}
	
	/**
	 * Increments the total price of the sale by the price of an item
	 * times the quantity of the item. 
	 * @param item Item whose price is getting added to the total. 
	 */
	void addToTotalPrice(Item item) {
		ItemInformation itemInfo = item.getItemInformation();
		
		totalPriceAmount = totalPriceAmount.add(itemInfo.getPrice().multiply(itemInfo.getQuantity()));
		
		Amount itemPriceNoVat = itemInfo.getItemDescription().getPriceInfo().getPriceAmount();
		Amount itemVatRate = itemInfo.getItemDescription().getPriceInfo().getVatRate();
		
		addToTotalVat(new Amount(((itemPriceNoVat.multiply(itemVatRate)).multiply(itemInfo.getQuantity())).getValue()));
	}
	
	private void addToTotalVat(Amount itemVatAmount) {
		totalVatAmount = totalVatAmount.add(itemVatAmount);
	}
}
