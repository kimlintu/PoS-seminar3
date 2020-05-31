package integration.dbhandler.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.util.Amount;
import model.util.IdentificationNumber;

class ItemDataTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testChangeQuantity() {
		ItemDescription validDescription = new ItemDescription("yoghurt", new Amount(18.0), new Amount(0.16), new IdentificationNumber(52313));
		int storedQuantity = 54;
		ItemData data = new ItemData(validDescription, storedQuantity);
		
		int addedQuantity = 5;
		data.increaseQuantity(addedQuantity);
		int expectedQuantity = storedQuantity + addedQuantity;
		int actualQuantity = data.getAvailableQuantity();
		assertEquals(expectedQuantity, actualQuantity, "Quantity not increased. Amount in stock: " + actualQuantity + ", expected: " + expectedQuantity + ".");
		storedQuantity = expectedQuantity; 
		
		int removedQuantity = 3; 
		data.decreaseQuantity(removedQuantity);
		expectedQuantity = storedQuantity - removedQuantity;
		actualQuantity = data.getAvailableQuantity();
		assertEquals(expectedQuantity, actualQuantity, "Quantity not decreased. Amount in stock: " + actualQuantity + ", expected: " + expectedQuantity + ".");
	}

	@Test
	void testInStock() {
		ItemDescription validDescription = new ItemDescription("yoghurt", new Amount(18.0), new Amount(0.16), new IdentificationNumber(52313));
		int storedQuantity = 0;
		ItemData data = new ItemData(validDescription, storedQuantity);
		
		boolean inStock = data.inStock();
		boolean expectedInStock = false;
		assertEquals(expectedInStock, inStock, "Item in stock although quantity is 0.");
		
		data.increaseQuantity(5);
		inStock = data.inStock();
		expectedInStock = true;
		assertEquals(expectedInStock, inStock, "Item in not in stock although quantity is greater than 0.");
	}

}
