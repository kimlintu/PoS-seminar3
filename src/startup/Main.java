package startup;

import controller.Controller;
import integration.dbhandler.SystemCreator;
import model.IdentificationNumber;
import view.View;

public class Main {
	public static void main(String[] args) {
		SystemCreator creator = new SystemCreator();
		
		Controller controller = new Controller(creator);
		View view = new View(controller);
		
		view.startSale();
	}
}
