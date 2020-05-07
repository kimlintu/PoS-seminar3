package model.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an amount of any kind. For example an item price or a VAT rate.
 * @author kim
 *
 */
public class Amount {
	private BigDecimal amount;
	
	/**
	 * Constructs a new <code>Amount</code> and initializes its value to the provided
	 * <code>amount</code>.
	 * 
	 * @param amount The amount that the instance will represent.
	 */
	public Amount(double amount) {
		this.amount = new BigDecimal(String.valueOf(amount)); 
	}
	
	/**
	 * Constructs a new <code>Amount</code> that represents the same value
	 * as <code>otherAmount</code>.
	 * @param otherAmount The amount to copy.
	 */
	public Amount(Amount otherAmount) {
		this.amount = otherAmount.getValue();
	}
	
	public Amount(BigDecimal amount) {
		this.amount = new BigDecimal(amount.toPlainString());
	}
	
	/**
	 * Adds together two <code>Amount</code> values.
	 * @param amountToAdd The other amount to add, should be greater than 0.
	 * @return A new <code>Amount</code> with the resulting sum as its value.
	 */
	public Amount add(Amount amountToAdd) {
		return new Amount(amount.add(amountToAdd.getValue()));
	}
	
	/**
	 * Subtracts one <code>Amount</code> from another <code>Amount</code>.
	 * @param amountToAdd The other amount to subtract, should be greater than 0.
	 * @return A new <code>Amount</code> with the resulting difference as its value.
	 */
	public Amount subtract(Amount amountToSubtract) {
		return new Amount(amount.subtract(amountToSubtract.getValue()));
	}
	
	/**
	 * Multiplies two <code>Amount</code> values.
	 * @param amountToAdd The multiplier.
	 * @return A new <code>Amount</code> with the resulting product as its value.
	 */
	public Amount multiply(Amount amountToMultiply) {
		return new Amount(amount.multiply(amountToMultiply.getValue()));
	}
	
	/**
	 * Multiplies an <code>Amount</code> value by an <code>int</code>.
	 * @param amountToAdd The multiplier.
	 * @return A new <code>Amount</code> with the resulting product as its value.
	 */
	public Amount multiply(int multiplicand) {
		return new Amount(amount.multiply(new BigDecimal(multiplicand)));
	}
	
	/**
	 * Returns the value that the <code>Amount</code> represents.
	 * @return The value as a <code>double</code>.
	 */
	public BigDecimal getValue() {
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
			return this.amount.doubleValue() == amt.getValue().doubleValue();
		}
		
		return false;
	}
	
	public String toString() {
		return String.format("%.2f", amount.doubleValue());
	}
}
