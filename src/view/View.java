package view;

import controller.Controller;
import model.dto.PriceInformation;
import model.dto.SaleInformation;
import model.util.Amount;
import model.util.IdentificationNumber;

public class View {
	private Controller controller; 
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	public void startSale() {
		controller.startSale();
	}
	
	public PriceInformation endSale() {
		return controller.endSale();
	}
	
	public SaleInformation enterItemIdentifier(IdentificationNumber itemID, int quantity, boolean multipleItems) {
		if(!multipleItems) {
			return controller.processItem(itemID, 1);
		}

		return controller.processItem(itemID, quantity);
	}
	
	public void testRun() {
		startSale();
		
		enterItemIdentifier(new IdentificationNumber(123), 4, true);
		enterItemIdentifier(new IdentificationNumber(666), 10, true);
		enterItemIdentifier(new IdentificationNumber(123), 1, false);
		enterItemIdentifier(new IdentificationNumber(492), 1, false);


		PriceInformation priceInfo = endSale();
		endSale();
		processSale();
		
	}
	
	private void processSale() {
		controller.enterAmountPaid(new Amount(5000));
		
	}
	
}
