package model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.ItemDescription;
import model.Amount;
import model.IdentificationNumber;
import model.Sale;
import model.dto.ItemPrice;
import model.dto.SaleInformation;

class SaleInformationTest {
	private Sale sale;
	private SaleInformation saleInfo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sale = new Sale();
	}

	@AfterEach
	void tearDown() throws Exception {
		sale = null;
	}

}
