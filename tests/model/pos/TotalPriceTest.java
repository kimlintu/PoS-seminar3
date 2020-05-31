package model.pos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.dbhandler.InventorySystem;
import integration.dbhandler.data.ItemDescription;
import model.util.Amount;
import model.util.IdentificationNumber;

class TotalPriceTest {
	private TotalPrice totalPrice;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		totalPrice = new TotalPrice();
	}

	@AfterEach
	void tearDown() throws Exception {
		totalPrice = null;
	}

	@Test
	void testCorrectPriceInformation() {
		Amount itemPrice = new Amount(5.50);
		Amount vatRate = new Amount(0.16);
		ItemDescription validDescription = new ItemDescription("milk", itemPrice, vatRate, new IdentificationNumber(78932));
		
		int boughtQuantity = 4;
		Item newItem = new Item(validDescription, boughtQuantity);
		
		totalPrice.addToTotalPrice(newItem);
		Amount expectedTotalVat = new Amount(newItem.getUnitVatTax().multiply(boughtQuantity));
		Amount actualTotalVat = totalPrice.getPriceInfo().getTotalVat(); 
		assertEquals(expectedTotalVat, actualTotalVat, "Incorrect total VAT tax amount.");
		
		Amount expectedTotalPrice = new Amount(itemPrice.multiply(boughtQuantity).add(expectedTotalVat));
		Amount actualTotalPrice = totalPrice.getPriceInfo().getTotalPrice();
		assertEquals(expectedTotalPrice, actualTotalPrice, "Incorrect total price amount.");
	}
}
