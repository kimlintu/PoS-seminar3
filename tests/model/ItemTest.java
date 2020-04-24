package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ItemDescription;
import model.Amount;
import model.IdentificationNumber;
import model.Item;
import model.dto.ItemPrice;

class ItemTest {
	private Item item;
	private ItemDescription description;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		description = new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.1)), new IdentificationNumber(333));
		item = new Item(description, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCorrectCalculatedPrice() {
		double calculatedPrice = 5 + (0.1 * 5);
		Amount finalPrice = new Amount(calculatedPrice);

		assertTrue(item.getPrice().equals(finalPrice),
				"Calculated price " + item.getPrice() + " is not correct. Should be " + calculatedPrice);

	}

	@Test
	void testEqual() {
		Item identicalItem = new Item(description, 1);

		assertTrue(item.equals(identicalItem), "Two identical items " + item.toString() + ", " + identicalItem.toString() + " do not equal.");
	}

	@Test
	void testNotEqual() {
		ItemDescription differentDescription = new ItemDescription("orange", new ItemPrice(new Amount(10), new Amount(0.1)),
				new IdentificationNumber(1111));
		Item differentItem = new Item(differentDescription, 1);

		assertFalse(item.equals(differentItem), "Two different items " + item.toString() + ", " + differentItem.toString() + " are equal.");
	}

}
