package model.dto;

import integration.dbhandler.data.ItemDescription;
import model.pos.Item;
import model.util.Amount;

/**
 * This is a data container containing information about an item that has been
 * processed in the sale.
 * 
 * @author kim
 *
 */
public class PurchasedItemInformation {
	private final ItemDescription description;
	private final Amount accumulatedPrice;
	private final Amount accumulatedVatTax;
	private final Amount unitPrice;
	private final Amount unitVatTax;
	private final int quantity;

	/**
	 * Constructs an <code>ItemInformation</code> and stores description, price and
	 * quantity of a processed item.
	 * 
	 * @param description      The {@link ItemDescription} of the item.
	 * @param accumulatedPrice The price (including VAT tax) of the item.
	 * @param quantity         The current quantity of the item being processed.
	 */
	public PurchasedItemInformation(Item item) {
		this.description = item.getItemDescription();
		this.unitPrice = item.getUnitPrice();
		this.unitVatTax = item.getUnitVatTax();
		this.accumulatedPrice = item.getAccumulatedPrice();
		this.accumulatedVatTax = item.getAccumulatedVatTax();
		this.quantity = item.getQuantity();
	}

	/**
	 * Returns the item description contained in this object.
	 * 
	 * @return An {@link ItemDescription} object.
	 */
	public ItemDescription getItemDescription() {
		return description;
	}

	/**
	 * Returns the total price of the unique item, including its VAT tax.
	 * 
	 * @return The price as an <code>Amount</code> object.
	 */
	public Amount getAccumulatedPrice() {
		return accumulatedPrice;
	}

	/**
	 * Returns the total VAT tax of the item.
	 * 
	 * @return The tax as an <code>Amount</code> object.
	 */
	public Amount getAccumulatedVatTax() {
		return accumulatedVatTax;
	}

	/**
	 * Returns the price per unit for this item type.
	 * @return The unit price as an <code>Amount</code>.
	 */
	public Amount getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Returns the VAT tax per unit for this item type.
	 * @return The VAT tax as an <code>Amount</code>.
	 */
	public Amount getUnitVatTax() {
		return unitVatTax;
	}

	/**
	 * Returns the total quantity of the processed item.
	 * 
	 * @return The quantity as an <code>int</code>
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Compares an <code>Object</code> and an <code>ItemInformation</code> object.
	 * 
	 * @return <code>true</code> if the object is an instance of
	 *         <code>ItemInformation</code> and has an identical ID contained in its
	 *         {@link ItemDescription}.
	 */
	@Override
	public boolean equals(Object anObject) {
		if (anObject instanceof PurchasedItemInformation) {
			PurchasedItemInformation info = (PurchasedItemInformation) anObject;

			return (this.description.getID().equals(info.getItemDescription().getID()));
		}

		return false;
	}

	/**
	 * Gives a string representation of this object containing its name,
	 * total quantity, unit price and accumulated price.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%-15s %-15s %s", description.getName(), quantity + "*" + unitPrice, accumulatedPrice));
		return sb.toString();
	}
}
