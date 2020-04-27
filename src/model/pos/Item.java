package model.pos;

import integration.dbhandler.data.ItemDescription;
import model.dto.PurchasedItemInformation;
import model.dto.ItemPrice;
import model.util.Amount;

/**
 * This class represents an unique item that's being processed in the sale. This
 * means that its price and quantity is mutable.
 * 
 * @author kim
 *
 */

public class Item {
	private ItemDescription description;
	private int quantity;
	private final Amount unitPrice;
	private final Amount unitVatTax;
	private Amount accumulatedPrice;
	private Amount accumulatedVatTax;

	/**
	 * Constructs a new <code>Item</code> and initializes its description and
	 * quantity. It also calculates and initializes the item price from the price
	 * and VAT rate provided by the item description.
	 * 
	 * @param description The {@link ItemDescription} adjoined to the
	 *                    <code>Item</code>.
	 * @param quantity    Amount of the corresponding item being processed.
	 */
	public Item(ItemDescription description, int quantity) {
		this.description = description;
		this.quantity = quantity;

		unitPrice = calculateItemPrice(description.getPriceInfo());
		unitVatTax = calculateVatTax(description.getPriceInfo());
		accumulatedPrice = new Amount(unitPrice.multiply(quantity));
		accumulatedVatTax = new Amount(unitVatTax.multiply(quantity));
	}

	/**
	 * Returns a container object with information about the specific items
	 * accumulated price, purchased quantity and the item description for the item.
	 * 
	 * @return An {@link PurchasedItemInformation} object.
	 */
	public PurchasedItemInformation getItemInformation() {
		return new PurchasedItemInformation(this);
	}

	public int getQuantity() {
		return quantity;
	}

	public ItemDescription getItemDescription() {
		return description;
	}

	public Amount getUnitPrice() {
		return unitPrice;
	}

	public Amount getUnitVatTax() {
		return unitVatTax;
	}

	public Amount getAccumulatedPrice() {
		return accumulatedPrice;
	}

	public Amount getAccumulatedVatTax() {
		return accumulatedVatTax;
	}

	/**
	 * Increase the quantity of this item that's being purchased.
	 * 
	 * @param quantityToAdd How much to increase the quantity.
	 */
	void addToQuantity(int quantityToAdd) {
		quantity += quantityToAdd;
	}

	/**
	 * Increase the total cost of the item(s).
	 * 
	 * @param quantity How many more of this item that has been purchased.
	 */
	void increaseAccumulatedPrice(int quantity) {
		accumulatedPrice = accumulatedPrice.add(unitPrice.multiply(quantity));

		increaseAccumulatedVatTax(quantity);
	}

	private void increaseAccumulatedVatTax(int quantity) {
		accumulatedVatTax = accumulatedVatTax.add(unitVatTax.multiply(quantity));
	}

	private Amount calculateItemPrice(ItemPrice priceInfo) {
		Amount itemPriceWithoutVat = priceInfo.getPriceAmount();
		Amount itemVatTax = calculateVatTax(priceInfo);

		return new Amount(itemPriceWithoutVat.add(itemVatTax).getValue());
	}

	private Amount calculateVatTax(ItemPrice priceInfo) {
		Amount itemPriceWithoutVat = priceInfo.getPriceAmount();
		Amount itemVatRate = priceInfo.getVatRate();

		return itemPriceWithoutVat.multiply(itemVatRate);
	}

	/**
	 * Compares the <code>Item</code> to the object. It will return
	 * <code>true</code> if and only if the object is an <code>Item</code> and the
	 * {@link ItemDescription} of each item is equal.
	 * 
	 * @param anObject The object to compare the <code>Item</code> against.
	 * @return <code>true</code> if the object represents an <code>Item</code> and
	 *         has an equivalent {@link ItemDescription} to the other
	 *         <code>Item</code>, <code>false</code> otherwise.
	 */
	public boolean equals(Object anObject) {
		if (anObject instanceof Item) {
			Item item = (Item) anObject;
			return this.description.equals(item.getItemInformation().getItemDescription());
		}

		return false;
	}
}
