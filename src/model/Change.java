package model;

public class Change {
	private final Amount amountPaid;
	private final Amount totalPrice;
	
	public Change(Amount amountPaid, Amount totalPrice) {
		this.amountPaid = amountPaid;
		this.totalPrice = totalPrice;
	}
	
	public Amount calculateChange() {
		return totalPrice.subtract(amountPaid);
	}
}
