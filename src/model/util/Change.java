package model.util;

public class Change {
	public Amount calculateChange(Amount totalPrice, Amount amountPaid) {
		return totalPrice.subtract(amountPaid);
	}
}
