package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Amount;

class AmountTest {
	Amount wholeAmount;
	Amount amountWithDecimal;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		wholeAmount = new Amount(10);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdd() {
		Amount amtToAdd = new Amount(3.2);
		double expectedSum = 10 + 3.2;
		Amount actualSum = wholeAmount.add(amtToAdd);

		assertEquals(expectedSum, actualSum.getValue(), "Incorrect sum from addition of two Amount: "
				+ actualSum.getValue() + " but expected " + expectedSum + ".");
	}

	@Test
	void testMultiplyAmount() {
		Amount multiplier = new Amount(0.5);
		double expectedProduct = 10 * 0.5;
		Amount actualProduct = wholeAmount.multiply(multiplier);

		assertEquals(expectedProduct, actualProduct.getValue(),
				"Incorrect product from multiplication between two Amount: " + actualProduct.getValue()
						+ " but expected " + expectedProduct + ".");
	}

	@Test
	void testMultiplyInt() {
		int multiplier = 5;
		double expectedProduct = 10 * 5;
		Amount actualProduct = wholeAmount.multiply(multiplier);

		assertEquals(expectedProduct, actualProduct.getValue(),
				"Incorrect product from multiplication between Amount and int: " + actualProduct.getValue()
						+ " but expected " + expectedProduct + ".");
	}

}
