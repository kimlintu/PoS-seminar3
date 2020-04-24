package integration.dbhandler;

import java.util.ArrayList;
import java.util.List;

import integration.ItemDescription;
import model.Amount;
import model.IdentificationNumber;
import model.Item;
import model.SaleInformation;
import model.dto.ItemPrice;

public class InventorySystem {
	private List<ItemData> itemDB;

	InventorySystem() {
		itemDB = new ArrayList<>();
		
		createDatabaseEntry(new ItemData(new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.1)),
				new IdentificationNumber(123)), 54));
		createDatabaseEntry(new ItemData(new ItemDescription("coffee", new ItemPrice(new Amount(42), new Amount(0.2)),
				new IdentificationNumber(666)), 12));
		createDatabaseEntry(new ItemData(new ItemDescription("orange juice", new ItemPrice(new Amount(12), new Amount(0.1)),
				new IdentificationNumber(492)), 5));
		createDatabaseEntry(new ItemData(new ItemDescription("chocolate bar", new ItemPrice(new Amount(10), new Amount(0.1)),
				new IdentificationNumber(876)), 1));
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
	public ItemDescription findItem(IdentificationNumber itemID) {
		for (ItemData itemDataObject : itemDB) {
			if (itemDataObject.getItemDescription().getID().equals(itemID))
				return itemDataObject.getItemDescription();
		}

		return null;
	}
	
	public void updateInventory(SaleInformation saleInfo) {
		List<Item> itemList = saleInfo.getListOfSoldItems();
		
		for(Item i : itemList) {
			
		}
	}

	private void createDatabaseEntry(ItemData itemDataObject) {
		itemDB.add(itemDataObject);
	}
}
