package model;

import integration.ItemDescription;
import model.dto.ItemPrice;

/**
 * This class represents an item that's being processed in the sale.
 * @author kim
 *
 */

public class Item {
	private ItemDescription description;
	private int quantity;
	private Amount price;

	/**
	 * Constructs a new <code>Item</code> and initializes its description and
	 * quantity. It also calculates and initializes the item price from the price and VAT rate
	 * provided by the item description.
	 * 
	 * @param description The {@link ItemDescription} adjoined to the <code>Item</code>.
	 * @param quantity Amount of the corresponding item being processed.
	 */
	public Item(ItemDescription description, int quantity) {
		this.description = description;
		this.quantity = quantity;
		
		price = calculateItemPrice(description.getPriceInfo());
	}
	
	public Amount getPrice() {
		return price;
	}
	
	int getQuantity() {
		return quantity;
	}
	
	ItemDescription getItemDescription() {
		return description;
	}
	
	void addToQuantity(int quantityToAdd) {
		quantity += quantityToAdd;
	}
	
	void updatePrice(int quantity) {
		price = price.add(price.multiply(quantity));
	}
	
	private Amount calculateItemPrice(ItemPrice priceInfo) {
		Amount itemPriceAmount = priceInfo.getPriceAmount();
		Amount itemVatRate = priceInfo.getVatRate();
		
		return new Amount(itemPriceAmount.add(itemPriceAmount.multiply(itemVatRate)).getValue());
	}
	
	/**
	 * Compares the <code>Item</code> to the object. It will return <code>true</code> if and only if
	 * the object is an <code>Item</code> and the {@link ItemDescription} of each item
	 * is equal.
	 * 
	 * @param anObject The object to compare the <code>Item</code> against.
	 * @return <code>true</code> if the object represents an <code>Item</code> and has
	 * an equivalent {@link ItemDescription} to the other <code>Item</code>, <code>false</code> otherwise.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof Item) {
			Item item = (Item) anObject;
			return this.description.equals(item.getItemDescription());
		}
		
		return false;
	}
	
	public String toString() {
		return "[" + description.getName() + ", " + price + ", " + quantity + "]";
	}
}
