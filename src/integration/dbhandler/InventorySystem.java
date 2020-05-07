package integration.dbhandler;

import java.util.ArrayList;
import java.util.List;

import integration.dbhandler.data.ItemData;
import integration.dbhandler.data.ItemDescription;
import model.dto.PurchasedItemInformation;
import model.dto.ItemPrice;
import model.dto.SaleInformation;
import model.util.Amount;
import model.util.IdentificationNumber;

/**
 * A class that handles the external inventory system. The database connected
 * to this is represented by an internal array list.
 *
 */
public class InventorySystem {
	private List<ItemData> itemDB;

	/**
	 * Constructs a new object and adds some data entries.
	 */
	InventorySystem() {
		itemDB = new ArrayList<>();

		createDatabaseEntry(new ItemData(new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.16)),
				new IdentificationNumber(123)), 54));
		createDatabaseEntry(new ItemData(new ItemDescription("coffee", new ItemPrice(new Amount(42), new Amount(0.16)),
				new IdentificationNumber(666)), 12));
		createDatabaseEntry(new ItemData(new ItemDescription("orange juice",
				new ItemPrice(new Amount(12), new Amount(0.16)), new IdentificationNumber(492)), 5));
		createDatabaseEntry(new ItemData(new ItemDescription("chocolate bar",
				new ItemPrice(new Amount(10), new Amount(0.16)), new IdentificationNumber(876)), 1)); 
	} 

	/**
	 * Searches the database(internal ArrayList) for an {@link ItemDescription} with
	 * the specified <code>itemID</code>.
	 * 
	 * @param itemID <code>IdentificationNumber</code> that will be used in the
	 *               search.
	 * @return The {@link ItemDescription} if its id matches the
	 *         <code>itemID</code>, otherwise <code>null</code>.
	 */
	public ItemDescription getItemDescriptionFromDatabase(IdentificationNumber itemID) {
		for (ItemData itemDataObject : itemDB) {
			if (itemDataObject.getItemDescription().getID().equals(itemID))
				return itemDataObject.getItemDescription();
		}

		return null;
	}

	/**
	 * Updates the quantity of the items in the database that was 
	 * processed in the sale.
	 * @param saleInfo The information about the sale. Contains the list 
	 * of sold items.
	 */
	public void updateQuantityOfItems(SaleInformation saleInfo) {
		List<PurchasedItemInformation> itemList = saleInfo.getListOfSoldItems();

		for (PurchasedItemInformation itemInfo : itemList) {
			for (ItemData itemData : itemDB) {
				IdentificationNumber searchedItemID = itemInfo.getItemDescription().getID();
				
				if (matches(searchedItemID, itemData)) {
					itemData.decreaseQuantity(itemInfo.getQuantity());
				}
			}
		}
	}
	
	/**
	 * Returns the available quantity of an item stored in the database.
	 * @param itemID The unique ID of the corresponding item.
	 * @return The available quantity as an <code>int</code>.
	 */
	public int getAvailableQuantityOfItem(IdentificationNumber itemID) {
		for(ItemData itemData : itemDB) {
			if(matches(itemID, itemData)) {
				return itemData.getAvailableQuantity();
			}
		}
		
		return 0;
	}

	private boolean matches(IdentificationNumber searchedItemID, ItemData itemData) {
		IdentificationNumber storedItemID = itemData.getItemDescription().getID();

		return searchedItemID.equals(storedItemID);
	}

	private void createDatabaseEntry(ItemData itemDataObject) {
		itemDB.add(itemDataObject);
	}
}
