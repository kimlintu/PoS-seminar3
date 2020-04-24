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
	
	public void enterItemIdentifier(IdentificationNumber itemID, int quantity) {
		controller.processItem(itemID, quantity);
	}
}
