package integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ItemDescription;
import model.Amount;
import model.IdentificationNumber;
import model.dto.ItemPrice;

class ItemDescriptionTest {
	private ItemDescription description;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		description = new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.1)),
				new IdentificationNumber(123));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEqual() {
		ItemDescription identicalDescription = new ItemDescription("apple",
				new ItemPrice(new Amount(5), new Amount(0.1)), new IdentificationNumber(123));

		assertTrue(description.equals(identicalDescription),
				"Item descriptions with identical IDs " + "does not equal.");
	}

	@Test
	void testNotEqual() {
		ItemDescription differentDescription = new ItemDescription("orange",
				new ItemPrice(new Amount(2), new Amount(0.1)), new IdentificationNumber(321));

		assertFalse(description.equals(differentDescription), "Item descriptions with different IDs " + "are equal.");
	}

	@Test
	void testToString() {
		String desiredText = "[name: apple, price: 5.0, VAT: 0.1, ID: 123]";
		assertTrue(description.toString().equals(desiredText), "String does not match desired text. \n" + "string: "
				+ description.toString() + "\n" + "desired text: " + desiredText);
	}

}
