package controller;

import integration.ItemDescription;
import integration.dbhandler.AccountingSystem;
import integration.dbhandler.DiscountSystem;
import integration.dbhandler.InventorySystem;
import integration.dbhandler.SaleLog;
import integration.dbhandler.SystemCreator;
import integration.printer.Printer;
import model.IdentificationNumber;
import model.Sale;
import model.dto.PriceInfo;
import model.dto.SaleInformation;

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
	
	private Sale currentSale;
	private SaleInformation saleInfo;
	
	/**
	 * Creates a new instance and initializes references to the external systems
	 * {@link InventorySystem}, {@link DiscountSystem}, {@link AccountingSystem}, {@link SaleLog}
	 * and {@link Printer}
	 * 
	 * @param creator A {@link SystemCreator} object.
	 */
	public Controller(SystemCreator creator) {
		inventorySystem = creator.getInventorySystem();
		discountSystem = creator.getDiscountSystem();
		accountingSystem = creator.getAccountingSystem();
		saleLog = creator.getSaleLog();
		
		printer = new Printer();
	}
	
	/**
	 * Gives a reference to a new Sale object to <code>currentSale</code> in this {@link Controller}
	 */
	public void startSale() {
		currentSale = new Sale();
	}
	
	public PriceInfo endSale() {
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
	public ItemDescription processItem(IdentificationNumber itemID, int quantity) {
		ItemDescription itemDescription = inventorySystem.getItemDescriptionFromDatabase(itemID);
		currentSale.addItemToSale(itemDescription, quantity);
		
		return itemDescription;
	}
}
