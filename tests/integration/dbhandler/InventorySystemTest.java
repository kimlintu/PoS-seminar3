package integration.dbhandler;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ItemDescription;
import integration.dbhandler.InventorySystem;
import model.IdentificationNumber;

class InventorySystemTest {
	private InventorySystem inventory;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		inventory = new InventorySystem();
	}

	@AfterEach
	void tearDown() throws Exception {
		inventory = null;
	}

	@Test
	void testFindExistingItem() {
		IdentificationNumber existingID = new IdentificationNumber(123);
		ItemDescription description = inventory.findItem(existingID); 
		
		assertTrue(description.getID().equals(existingID), "ID " + existingID + " "
				+ "does not match entry description retrieved from database with ID " +  description.getID() + ".");
	}
	
	@Test
	void testFindItemUsingInvalidID() {
		IdentificationNumber invalidID = new IdentificationNumber(981237);
		ItemDescription itemDescription = inventory.findItem(invalidID); 
		
		if(itemDescription != null) {
			fail("Item " + itemDescription + " was retrieved using nonexisting ID " + invalidID + ", expected null. " );
		}
	}

}
