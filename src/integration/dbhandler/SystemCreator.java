package integration.dbhandler;

public class SystemCreator {
	private InventorySystem inventorySystem;
	private DiscountSystem discountSystem;
	private AccountingSystem accountingSystem;
	private SaleLog saleLog;
	
	public SystemCreator() {
		inventorySystem = new InventorySystem();
		discountSystem = new DiscountSystem();
		accountingSystem = new AccountingSystem();
		saleLog = new SaleLog();
	}
	
	public InventorySystem getInventorySystem() {
		return inventorySystem;
	}
	
	public DiscountSystem getDiscountSystem() {
		return discountSystem;
	}
	
	public AccountingSystem getAccountingSystem() {
		return accountingSystem;
	}
	
	public SaleLog getSaleLog() {
		return saleLog;
	}
}
