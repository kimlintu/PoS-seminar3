package model;

/**
 * Represents an amount of any kind. For example an item price or a VAT rate.
 * @author kim
 *
 */
public class Amount {
	double amount; 
	
	/**
	 * Constructs a new <code>Amount</code> and initializes its value to the provided
	 * <code>amount</code>.
	 * 
	 * @param amount The amount that the instance will represent.
	 */
	public Amount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Adds together two <code>Amount</code> values.
	 * @param amountToAdd The other amount to add.
	 * @return A new <code>Amount</code> with the resulting sum as its value.
	 */
	public Amount add(Amount amountToAdd) {
		return new Amount(this.getValue() + amountToAdd.getValue());
	}
	
	/**
	 * Adds together two <code>Amount</code> values.
	 * @param amountToAdd The other amount to add.
	 * @return A new <code>Amount</code> with the resulting sum as its value.
	 */
	public Amount subtract(Amount amountToSubtract) {
		return new Amount(this.getValue() - amountToSubtract.getValue());
	}
	
	/**
	 * Multiplies two <code>Amount</code> values.
	 * @param amountToAdd The multiplier.
	 * @return A new <code>Amount</code> with the resulting product as its value.
	 */
	public Amount multiply(Amount amountToMultiply) {
		return new Amount(this.getValue() * amountToMultiply.getValue());
	}
	
	/**
	 * Multiplies an <code>Amount</code> value by an <code>int</code>.
	 * @param amountToAdd The multiplier.
	 * @return A new <code>Amount</code> with the resulting product as its value.
	 */
	public Amount multiply(int amountToMultiply) {
		return new Amount(this.getValue() * amountToMultiply);
	}
	
	/**
	 * Returns the value that the <code>Amount</code> represents.
	 * @return The value as a <code>double</code>.
	 */
	public double getValue() {
		return amount;
	}
	
	/**
	 * Compares an object and an <code>Amount</code>. 
	 * @return <code>true</code> if the object is an instance of <code>Amount</code> and has the
	 * same value as the other <code>Amount</code>, <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Amount) {
			Amount amt = (Amount) o;
			return this.amount == amt.getValue();
		}
		
		return false;
	}
	
	public String toString() {
		return "" + amount;
	}
}
