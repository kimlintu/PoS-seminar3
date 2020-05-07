package view;

import java.util.Random;

import controller.Controller;
import model.dto.PriceInformation;
import model.dto.CurrentSaleInformation;
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

	public CurrentSaleInformation enterItemIdentifier(IdentificationNumber itemID, int quantity, boolean multipleItems) {
		if (!multipleItems) {
			return controller.processItem(itemID, 1);
		}

		return controller.processItem(itemID, quantity);
	}

	public void testRun() {
		Random rand = new Random(); 
		
		startSale();

		IdentificationNumber validIDs[] = { new IdentificationNumber(123), new IdentificationNumber(666),
				new IdentificationNumber(492), new IdentificationNumber(876) };
		for(int i = 0; i < 4; i++) {
			int quantity = rand.nextInt(9) + 1;
			boolean multipleItems = (quantity > 1) ? true : false;
			
			System.out.println("Purchasing " + quantity + " item(s) with id " + validIDs[i].toString());
			CurrentSaleInformation recentSaleInformation = enterItemIdentifier(validIDs[i], quantity, multipleItems);
			System.out.println(recentSaleInformation);
		}

		PriceInformation totalPriceInfo = endSale();
		System.out.println("[" + totalPriceInfo + "]" + "\n");
		Amount amountPaid = totalPriceInfo.getTotalPrice().add(new Amount(rand.nextDouble() * 200 + 1));
		System.out.println("Customer pays " + amountPaid);
		
		System.out.println("Processing sale and printing receipt..\n");
		enterAmountPaid(amountPaid);
	}

	private void enterAmountPaid(Amount amountPaid) {
		controller.processSale(amountPaid);

	}

}
