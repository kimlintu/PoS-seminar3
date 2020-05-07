package model.dto;

import model.util.Amount;

/**
 * This class contains an items original price and its VAT rate.
 */
public class ItemPrice {
	private final Amount priceAmount;
	private final Amount vatRate;
	
	/**
	 * Create an instance that stores the item's price and VAT rate
	 * @param priceAmount The price of the item without added tax.
	 * @param vatRate The VAT rate that applies to the item.
	 */
	public ItemPrice(Amount priceAmount, Amount vatRate) {
		this.priceAmount = priceAmount;
		this.vatRate = vatRate;
	}
	
	/**
	 * Returns the original price without added tax, stored in this object.
	 * @return The price as an <code>Amount</code> object.
	 */
	public Amount getPriceAmount() {
		return priceAmount;
	}
	
	/**
	 * Returns the VAT rate stored in this object.
	 * @return The rate as an <code>Amount</code> object.
	 */
	public Amount getVatRate() {
		return vatRate;
	}

}
