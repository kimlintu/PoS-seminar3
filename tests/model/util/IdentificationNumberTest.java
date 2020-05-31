package model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IdentificationNumberTest {

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
	void testEqualsSameObject() {
		IdentificationNumber id = new IdentificationNumber(123);
		assertTrue(id.equals(id), "ID is not equal when compared with itself.");
	}

	@Test
	void testEqualsObjectWithSameID() {
		long id = 12412412412L;
		IdentificationNumber oneID = new IdentificationNumber(id);
		IdentificationNumber anotherID = new IdentificationNumber(id);
		assertTrue(oneID.equals(anotherID), "ID is not equal when compared with object that has identical ID.");
	}

	@Test
	void testNotEquals() {
		long id = 12412412412L;
		long otherID = 9075434L;

		IdentificationNumber oneID = new IdentificationNumber(id);
		IdentificationNumber anotherID = new IdentificationNumber(otherID);
		assertFalse(oneID.equals(anotherID), "ID is equal when compared with object that has different ID.");
		assertFalse(oneID.equals(null), "ID is equal when compared with null.");
		assertFalse(oneID.equals(new Object()),
				"ID is equal when compared with an object that is not an IdentificationNumber object.");
	}

}
