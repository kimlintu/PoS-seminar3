package startup;

import controller.Controller;
import integration.cashregister.CashRegister;
import integration.dbhandler.SystemCreator;
import view.View;

public class Main {
	public static void main(String[] args) {
		SystemCreator creator = new SystemCreator();
		CashRegister register = new CashRegister();
		
		Controller controller = new Controller(creator, register);
		View view = new View(controller);
		
		view.testRun();
	}
}
