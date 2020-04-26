package model.dto;

import java.time.LocalTime;

import model.util.Amount;

public class Receipt {
	private final SaleInformation saleInfo;
	private final Amount amountPaid;
	private final Amount changeAmount;
	private final Store store;
	private final LocalTime timeOfSale;
	
	/**
	 * Constructs an instance of <code>Receipt</code> that contains information about the sale, 
	 * payment amount, change amount, time of sale and store information. 
	 * @param saleInfo The available information about the completed sale.
	 * @param amountPaid The amount paid by the customer in the sale.
	 * @param changeAmount The amount of change the customer received after payment.
	 * @param timeOfSale The time which this receipt will be created and thus mark the 
	 * time of the completed sale.
	 */
	public Receipt(SaleInformation saleInfo, Amount amountPaid, Amount changeAmount, LocalTime timeOfSale) {
		this.saleInfo = saleInfo;
		this.amountPaid = amountPaid;
		this.changeAmount = changeAmount;
		this.timeOfSale = timeOfSale;
		
		this.store = new Store("STORE NAME", "STORE ADDRESS");
	}
	
	/**
	 * Returns information about the sale stored in {@link SaleInformation}.
	 * @return An <code>SaleInformation</code> object.
	 */
	public SaleInformation getSaleInformation() {
		return saleInfo;
	}
	
	/**
	 * Returns the amount paid by the customer. 
	 * @return The amount paid as an <code>Amount</code>.
	 */
	public Amount getAmountPaid() {
		return amountPaid;
	}
	
	/**
	 * Returns the amount of change that is owed to the customer.
	 * @return The amount of change as an <code>Amount</code>.
	 */
	public Amount getAmountOfChange() {
		return changeAmount;
	}
	
	/**
	 * Returns the time that the sale was started.
	 * 
	 * @return Time of the sale as a <code>LocalTime</code> object.
	 */
	public LocalTime getTimeOfSale() {
		return timeOfSale;
	}
	
	/**
	 * Returns an instance of a {@link Store} object that contains
	 * information about the store's address and name.
	 * @return A <code>Store</code> object.
	 */
	public Store getStore() {
		return store;
	}
}
