package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ItemDescription;
import integration.dbhandler.SystemCreator;
import model.Amount;
import model.IdentificationNumber;
import model.dto.ItemPrice;
import model.dto.PriceInfo;

class ControllerTest {
	private Controller controller;
	private ItemDescription existingDescription;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		SystemCreator creator = new SystemCreator();
		controller = new Controller(creator);

		existingDescription = new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.1)),
				new IdentificationNumber(123));
	}

	@AfterEach
	void tearDown() throws Exception {
		controller = null;
		existingDescription = null;
	}

	@Test
	void testCorrectItemDescription() {
		controller.startSale();

		ItemDescription retrievedDescription = controller.processItem(existingDescription.getID(), 1);

		assertTrue(existingDescription.equals(retrievedDescription),
				"Incorrect item description recieved from inventory. Expected " + existingDescription.toString()
						+ " got " + retrievedDescription.toString());
	}

	@Test
	void testCorrectPriceInfo() {
		controller.startSale();
		controller.processItem(existingDescription.getID(), 3);

		Amount expectedTotalPrice = new Amount((5 + (5 * 0.1)) * 3);
		Amount expectedTotalVat = new Amount((5 * 0.1) * 3);

		PriceInfo priceInfo = controller.stopSale();
		Amount receivedTotalPrice = priceInfo.getTotalPrice();
		Amount receivedTotalVat = priceInfo.getTotalVat();

		assertTrue(receivedTotalPrice.equals(expectedTotalPrice), "Received total price "
				+ receivedTotalPrice.getValue() + " does not match, expected " + expectedTotalPrice.getValue());
		assertTrue(receivedTotalVat.equals(expectedTotalVat), "Received total VAT "
				+ receivedTotalVat.getValue() + " does not match, expected " + expectedTotalVat.getValue());
	}

}
