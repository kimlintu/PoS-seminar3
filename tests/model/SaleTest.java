package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.ItemDescription;
import integration.dbhandler.SystemCreator;
import model.dto.ItemInformation;
import model.dto.ItemPrice;
import model.dto.SaleInformation;

class SaleTest {
	SystemCreator creator;
	Controller controller;

	Sale sale;

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
	
	@Test
	void testAddNoItemsToSale() {
		SaleInformation saleInfo = sale.getSaleInformation();
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount totalVat = saleInfo.getPriceInfo().getTotalVat();
		
		Amount expectedPrice = new Amount(0);
		assertTrue(totalPrice.equals(expectedPrice), "Total price is greater than 0 when no item is added.");
		
		Amount expectedVat = new Amount(0);
		assertTrue(totalVat.equals(expectedVat), "Total VAT tax is greater than 0 when no item is added.");
	}

	@Test
	void testAddOneItemToSale() {
		Amount itemPrice = new Amount(5);
		Amount itemVatRate = new Amount(0.1);
		Amount vatTax = itemPrice.multiply(itemVatRate);
		int purchasedQuantity = 1;

		ItemDescription newItem = new ItemDescription("apple", new ItemPrice(itemPrice, itemVatRate),
				new IdentificationNumber(123));

		sale.addItemToSale(newItem, purchasedQuantity);

		SaleInformation saleInfo = sale.getSaleInformation();
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount totalVat = saleInfo.getPriceInfo().getTotalVat();
		List<ItemInformation> itemList = saleInfo.getListOfSoldItems();
		
		ItemInformation expectedItem = new Item(newItem, purchasedQuantity).getItemInformation();
		assertTrue(itemList.contains(expectedItem), "Purchased item list does not contain the added item.");
		
		Amount expectedPrice = new Amount(itemPrice.add(vatTax).getValue());
		assertTrue(totalPrice.equals(expectedPrice),
				"Incorrect total price " + totalPrice.getValue() + ", expected " + expectedPrice.getValue());
		
		Amount expectedVat = new Amount(vatTax.getValue());
		assertTrue(totalVat.equals(expectedVat),
				"Incorrect total vat " + totalVat.getValue() + ", expected " + expectedVat.getValue());

	}
	
	@Test 
	void testAddMultipleIdenticalItemsToSale() {
		Amount itemPrice = new Amount(5);
		Amount itemVatRate = new Amount(0.1);
		Amount vatTax = itemPrice.multiply(itemVatRate);

		ItemDescription newItem = new ItemDescription("apple", new ItemPrice(itemPrice, itemVatRate),
				new IdentificationNumber(444));

		sale.addItemToSale(newItem, 1);
		sale.addItemToSale(newItem, 1);
		int purchasedQuantity = 2;

		SaleInformation saleInfo = sale.getSaleInformation();
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount totalVat = saleInfo.getPriceInfo().getTotalVat();
		List<ItemInformation> itemList = saleInfo.getListOfSoldItems();
		
		ItemInformation expectedItem = new Item(newItem, purchasedQuantity).getItemInformation();
		assertTrue(itemList.contains(expectedItem), "Purchased item list does not contain the added item.");
		
		Amount expectedPrice = new Amount(itemPrice.add(vatTax).getValue()).multiply(2);
		assertTrue(totalPrice.equals(expectedPrice),
				"Incorrect total price " + totalPrice.getValue() + ", expected " + expectedPrice.getValue());
		
		Amount expectedVat = new Amount(vatTax.getValue()).multiply(2);
		assertTrue(totalVat.equals(expectedVat),
				"Incorrect total vat " + totalVat.getValue() + ", expected " + expectedVat.getValue());
	}
	
	@Test 
	void testAddMultipleDifferentItemsToSale() {
		Amount applePrice = new Amount(5);
		Amount orangePrice = new Amount(4);
		Amount appleVatRate = new Amount(0.1);
		Amount orangeVatRate = new Amount(0.1);
		Amount appleVatTax = applePrice.multiply(appleVatRate);
		Amount orangeVatTax = orangePrice.multiply(orangeVatRate);

		ItemDescription itemApple = new ItemDescription("apple", new ItemPrice(applePrice, appleVatRate),
				new IdentificationNumber(444));
		ItemDescription itemOrange = new ItemDescription("orange", new ItemPrice(orangePrice, orangeVatRate),
				new IdentificationNumber(555));

		sale.addItemToSale(itemApple, 1);
		sale.addItemToSale(itemOrange, 1);

		SaleInformation saleInfo = sale.getSaleInformation();
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount totalVat = saleInfo.getPriceInfo().getTotalVat();
		List<ItemInformation> itemList = saleInfo.getListOfSoldItems();
		

		ItemInformation expectedItemApple = new Item(itemApple, 1).getItemInformation();
		assertTrue(itemList.contains(expectedItemApple), "Purchased item list does not contain the added item.");
		
		ItemInformation expectedItemOrange = new Item(itemOrange, 1).getItemInformation();
		assertTrue(itemList.contains(expectedItemOrange), "Purchased item list does not contain the added item.");

		Amount expectedPrice = new Amount(((applePrice.add(appleVatTax)).add(orangePrice.add(orangeVatTax))).getValue());
		assertTrue(totalPrice.equals(expectedPrice),
				"Incorrect total price " + totalPrice.getValue() + ", expected " + expectedPrice.getValue());
		
		Amount expectedVat = new Amount(appleVatTax.add(orangeVatTax).getValue());
		assertTrue(totalVat.equals(expectedVat),
				"Incorrect total vat " + totalVat.getValue() + ", expected " + expectedVat.getValue());
	}
	
	@Test
	void testAddMultipleItemsOneOperation() {
		Amount itemPrice = new Amount(5);
		Amount itemVatRate = new Amount(0.1);
		Amount vatTax = itemPrice.multiply(itemVatRate);

		ItemDescription newItem = new ItemDescription("apple", new ItemPrice(itemPrice, itemVatRate),
				new IdentificationNumber(444));

		int purchasedQuantity = 4;
		sale.addItemToSale(newItem, purchasedQuantity);

		SaleInformation saleInfo = sale.getSaleInformation();
		Amount totalPrice = saleInfo.getPriceInfo().getTotalPrice();
		Amount totalVat = saleInfo.getPriceInfo().getTotalVat();
		List<ItemInformation> itemList = saleInfo.getListOfSoldItems();
		
		ItemInformation expectedItem = new Item(newItem, purchasedQuantity).getItemInformation();
		assertTrue(itemList.contains(expectedItem), "Purchased item list does not contain the added item.");
		
		Amount expectedPrice = new Amount(itemPrice.add(vatTax).getValue()).multiply(purchasedQuantity);
		assertTrue(totalPrice.equals(expectedPrice),
				"Incorrect total price " + totalPrice.getValue() + ", expected " + expectedPrice.getValue());
		
		Amount expectedVat = new Amount(vatTax.getValue()).multiply(purchasedQuantity);
		assertTrue(totalVat.equals(expectedVat),
				"Incorrect total vat " + totalVat.getValue() + ", expected " + expectedVat.getValue());
	}

}
