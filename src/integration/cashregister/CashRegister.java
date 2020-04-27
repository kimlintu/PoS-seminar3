package integration.cashregister;

import java.util.Random;

import model.util.Amount;

public class CashRegister {
	private Random random = new Random();
	private Amount balance = new Amount(random.nextDouble() * 500 + 500);
	
	public Amount depositAmountToRegister(Amount amountToAdd) {
		balance = balance.add(amountToAdd);
		
		return balance;
	}
	
	public Amount withdrawAmountFromRegister(Amount amountToSubtract) {
		balance = balance.subtract(amountToSubtract);
		
		return balance;
	}
}
