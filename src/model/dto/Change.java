package model.dto;

import model.util.Amount;

public class Change {
	private final Amount amount;
	
	public Change(Amount totalPrice, Amount amountPaid) {
		amount = amountPaid.subtract(totalPrice);
	}
	
	public Amount getAmount() {
		return amount;
	}
}
