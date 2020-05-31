package model.dto;

import model.util.Amount;

/**
 * Represents the change generated in the sale. 
 */
public class Change {
	private final Amount amount;
	
	/**
	 * Constructor. Calculates the change from the specified parameters.
	 * @param totalPrice The total price of the sale. 
	 * @param amountPaid The amount paid by the customer.
	 */
	public Change(Amount totalPrice, Amount amountPaid) {
		amount = amountPaid.subtract(totalPrice);
	}
	
	/**
	 * @return the amount of change. 
	 */
	public Amount getAmount() {
		return amount;
	}
}
