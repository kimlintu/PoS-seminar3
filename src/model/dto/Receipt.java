package model.dto;

import model.util.Amount;

public class Receipt {
	private final SaleInformation saleInfo;
	private final Amount amountPaid;
	private final Amount changeAmount;
	private final Store store;
	
	public Receipt(SaleInformation saleInfo, Amount amountPaid, Amount changeAmount) {
		this.saleInfo = saleInfo;
		this.amountPaid = amountPaid;
		this.changeAmount = changeAmount;
		this.store = new Store("STORE NAME", "STORE ADDRESS");
	}
	
	public SaleInformation getSaleInformation() {
		return saleInfo;
	}
	
	public Amount getAmountPaid() {
		return amountPaid;
	}
	
	public Amount getAmountOfChange() {
		return changeAmount;
	}
}
