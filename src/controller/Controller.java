package controller;

import integration.cashregister.CashRegister;
import integration.dbhandler.AccountingSystem;
import integration.dbhandler.DiscountSystem;
import integration.dbhandler.InventorySystem;
import integration.dbhandler.SaleLog;
import integration.dbhandler.SystemCreator;
import integration.dbhandler.data.ItemDescription;
import integration.printer.Printer;
import model.dto.PriceInformation;
import model.dto.Receipt;
import model.dto.SaleInformation;
import model.pos.Sale;
import model.util.Amount;
import model.util.Change;
import model.util.IdentificationNumber;

/**
 * Handles all the system operations in the program. 
 * @author kim
 *
 */
public class Controller {
	private InventorySystem inventorySystem;
	private DiscountSystem discountSystem;
	private AccountingSystem accountingSystem;
	private SaleLog saleLog;
	
	private Printer printer;
	private CashRegister cashRegister;
	
	private Sale currentSale;
	private SaleInformation saleInfo;
	
	/**
	 * Creates a new instance and initializes references to the external systems
	 * {@link InventorySystem}, {@link DiscountSystem}, {@link AccountingSystem}, {@link SaleLog}
	 * and {@link Printer}
	 * 
	 * @param creator A {@link SystemCreator} object that has references to all the 
	 * external systems.
	 */
	public Controller(SystemCreator creator) {
		inventorySystem = creator.getInventorySystem();
		discountSystem = creator.getDiscountSystem();
		accountingSystem = creator.getAccountingSystem();
		saleLog = creator.getSaleLog();
		
		printer = new Printer();
		cashRegister = new CashRegister();
	}
	
	/**
	 * Gives a reference to a new Sale object to <code>currentSale</code> in this <code>Controller</code>
	 */
	public void startSale() {
		currentSale = new Sale();
	}
	
	/**
	 * Signals the program that all items have been scanned. 
	 * @return Returns information about the total price, including
	 * total VAT tax.
	 */
	public PriceInformation endSale() {
		saleInfo = currentSale.getSaleInformation();
		
		return saleInfo.getPriceInfo();
	}
	
	/**
	 * Processes the item by either adding the item to the sale,
	 * or updating its quantity if an identical item has already been processed. 
	 * 
	 * @param itemID The unique id for the item that's being processed.
	 * @param quantity Amount of items being processed.
	 * @return An {@link ItemDescription} that gets retrieved from the {@link InventorySystem} using the 
	 * specified <code>itemID</code>
	 */
	public SaleInformation processItem(IdentificationNumber itemID, int quantity) {
		ItemDescription itemDescription = inventorySystem.getItemDescriptionFromDatabase(itemID);
		currentSale.addItemToSale(itemDescription, quantity);
		
		return currentSale.getSaleInformation();
	}
	
	/**
	 * Processes the sale by updating the balance in the register, creating 
	 * a receipt and sending the sale information to the relevant external systems. 
	 * Finally, it prints the receipt and returns the amount of change to 
	 * give to the customer.
	 * @param amountPaid The amount paid by the customer.
	 * @return The amount of change to be received by the customer.
	 */
	public Amount enterAmountPaid(Amount amountPaid) {
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount amountOfChange = getChange(totalPrice, amountPaid); 
		
		updateBalanceInCashRegister(totalPrice);
		
		Receipt receipt = currentSale.processSale(saleInfo, amountPaid, amountOfChange);
		
		accountingSystem.updateAccounting(receipt);
		saleLog.logSale(receipt);
		inventorySystem.updateQuantityOfSoldItems(saleInfo);
		
		currentSale.printReceipt(printer, receipt);
		
		return amountOfChange;
	}
	
	private Amount getChange(Amount totalPrice, Amount amountPaid) {
		return new Change().calculateChange(totalPrice, amountPaid);
	}
	
	private Amount updateBalanceInCashRegister(Amount totalPrice) {
		cashRegister.depositAmountToRegister(totalPrice);
		
		return cashRegister.getBalance();
	}
}
