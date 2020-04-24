package view;

import controller.Controller;
import model.IdentificationNumber;

public class View {
	private Controller controller; 
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	public void startSale() {
		controller.startSale();
	}
	
	public void endSale() {
		controller.endSale();
	}
	
	public void enterItemIdentifier(IdentificationNumber itemID, int quantity, boolean multipleItems) {
		if(!multipleItems) {
			controller.processItem(itemID, 1);
		}

		controller.processItem(itemID, quantity);
	}
}
