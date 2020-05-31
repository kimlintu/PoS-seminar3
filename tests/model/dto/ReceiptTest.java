package model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.dbhandler.SystemCreator;
import integration.dbhandler.data.ItemDescription;
import model.pos.Sale;
import model.util.Amount;
import model.util.IdentificationNumber;

class ReceiptTest {
	private SystemCreator creator;

	private Sale sale;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		creator = new SystemCreator();
		sale = new Sale();
	}

	@AfterEach
	void tearDown() throws Exception {
		sale = null;
		creator = null;
	}

	@Test
	void testToString() {
		ItemDescription itemApple = creator.getInventorySystem().getItemDescriptionFromDatabase(new IdentificationNumber(123));
		ItemDescription itemCoffee = creator.getInventorySystem().getItemDescriptionFromDatabase(new IdentificationNumber(666));

		sale.addItemToSale(itemApple, 1);
		sale.addItemToSale(itemCoffee, 1);

		PriceInformation priceInfo = sale.getPriceInformation();
		Amount totalPrice = priceInfo.getTotalPrice();
		Amount totalVat = priceInfo.getTotalVat();
		
		Amount paymentAmount = new Amount(100);
		Amount expectedChange = new Change(totalPrice, paymentAmount).getAmount();
		Receipt saleInfo = sale.processSale(paymentAmount, expectedChange);
		String receiptString = saleInfo.toString();
		
		for(PurchasedItemInformation item : saleInfo.getListOfSoldItems()) {
			assertTrue(receiptString.contains(item.toString()), "Receipt does not contain sold item information " + item.toString() + ".");
		}
		
		assertTrue(receiptString.contains(totalPrice.toString()), "Receipt does not contain total price amount.");
		assertTrue(receiptString.contains(totalVat.toString()), "Receipt does not contain total VAT tax amount.");
		
		assertTrue(receiptString.contains(expectedChange.toString()), "Receipt does not contain change amount.");
		assertTrue(receiptString.contains(paymentAmount.toString()), "Receipt does not contain payment amount.");
	}

}
