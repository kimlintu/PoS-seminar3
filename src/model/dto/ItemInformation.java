package model.dto;

import integration.ItemDescription;
import model.Amount;

/**
 * This is a data container containing information about an unique item
 * that has been processed in the sale.
 * @author kim
 *
 */
public class ItemInformation {
	private final ItemDescription description;
	private final Amount price;
	private final Amount vatTax;
	private final int quantity;
	
	/**
	 * Constructs an <code>ItemInformation</code> and stores description, price and quantity
	 * of a processed item.
	 * @param description The {@link ItemDescription} of the item.
	 * @param price The price (including VAT tax) of the item.
	 * @param quantity The current quantity of the item being processed.
	 */
	public ItemInformation(ItemDescription description, Amount price, Amount vatTax, int quantity) {
		this.description = description;
		this.price = price;
		this.vatTax = vatTax;
		this.quantity = quantity;
	}
	
	/**
	 * Returns the item description contained in this object.
	 * @return An {@link ItemDescription} object.
	 */
	public ItemDescription getItemDescription() {
		return description;
	}
	
	/**
	 * Returns the total price of the unique item, including its 
	 * VAT tax.
	 * @return The price as an <code>Amount</code> object.
	 */
	public Amount getPrice() {
		return price;
	}
	
	/**
	 * Returns the total VAT tax of the item.
	 * @return The tax as an <code>Amount</code> object.
	 */
	public Amount getVatTax() {
		return vatTax;
	}
	
	/**
	 * Returns the total quantity of the processed item.
	 * @return The quantity as an <code>int</code>
	 */
	public int getQuantity() {
		return quantity;
	}
	
	public String toString() {
		return "[" + description.getName() + ", " + price + ", " + quantity + "]";
	}
	
	/**
	 * Compares an <code>Object</code> and an <code>ItemInformation</code> object. 
	 * @return <code>true</code> if the object is an instance of <code>ItemInformation</code>
	 * and has an identical ID contained in its {@link ItemDescription}.
	 */
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof ItemInformation) {
			ItemInformation info = (ItemInformation) anObject;
			
			return (this.description.getID().equals(info.getItemDescription().getID()));
		}
		
		return false;
	}
}
