package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import integration.ItemDescription;
import integration.dbhandler.InventorySystem;
import model.dto.SaleInformation;

/**
 * This class represents the ongoing sale. 
 * @author kim
 *
 */
public class Sale {
	private List<Item> itemList;
	private TotalPrice totalPrice;
	private LocalTime timeOfSale;
	
	/**
	 * Creates a new instance. The instance will have a list that contains the items that's
	 * being purchased and the total price of the sale
	 */
	public Sale() {
		itemList = new ArrayList<>();
		totalPrice = new TotalPrice();
		timeOfSale = LocalTime.now();
	}
	
	/**
	 * Creates a new {@link Item} object containing the <code>itemDescription</code> and the <code>quantity</code> 
	 * and adds it to the internal item list. If an identical item already exists in the list, the program
	 * will instead update the quantity and price of that item. 
	 * 
	 * @param itemDescription description of the item that's stored in the {@link InventorySystem}.
	 * @param purchasedQuantity Amount of the corresponding item being processed.
	 */
	public void addItemToSale(ItemDescription itemDescription, int purchasedQuantity) {
		Item purchasedItem = new Item(itemDescription, purchasedQuantity);
		
		if(itemList.contains(purchasedItem)) {
			updateQuantityOfItemInItemList(purchasedItem, purchasedQuantity);
			updatePriceOfItemInItemList(purchasedItem, purchasedQuantity);
		} else {
			addItemToItemList(purchasedItem);
		}
		
		totalPrice.addToTotalPrice(purchasedItem);
	}
	
	/**
	 * Returns a new {@link SaleInformation} object created by this instance.
	 * @return A <code>SaleInformation</code> object containing data from this <code>Sale</code>.
	 */
	public SaleInformation getSaleInformation() {
		return new SaleInformation(timeOfSale, totalPrice.getPriceInfo(), itemList);
	}
	
	private void updateQuantityOfItemInItemList(Item item, int quantity) {
		getItemFromItemList(item).addToQuantity(quantity);
	}
	
	private void updatePriceOfItemInItemList(Item item, int quantity) {
		getItemFromItemList(item).increasePrice(quantity);
	}
	
	private Item getItemFromItemList(Item item) {
		return itemList.get(itemList.indexOf(item));
	}
	
	private void addItemToItemList(Item item) {
		itemList.add(item);
	}
}
