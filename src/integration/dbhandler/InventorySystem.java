package integration.dbhandler;

import java.util.ArrayList;
import java.util.List;

import integration.ItemDescription;
import model.Amount;
import model.IdentificationNumber;
import model.dto.ItemPrice;

public class InventorySystem {
	private List<ItemDescription> itemDB; 

	InventorySystem() {
		itemDB = new ArrayList<>();
		createDatabaseEntry(new ItemDescription("apple", new ItemPrice(new Amount(5), new Amount(0.1)), new IdentificationNumber(123)));
	}
	
	/**
	 * Searches the database(internal ArrayList) for an {@link ItemDescription}
	 * with the specified <code>itemID</code>. 
	 * @param itemID <code>IdentificationNumber</code> that will be used in the search.
	 * @return The {@link ItemDescription} if its id matches the <code>itemID</code>,
	 * otherwise <code>null</code>.
	 */
	public ItemDescription findItem(IdentificationNumber itemID) {
		for (ItemDescription description : itemDB) {
			if (description.getID().equals(itemID)) return description;
		}
		
		return null;
	}
	
	private void createDatabaseEntry(ItemDescription description) {
		itemDB.add(description);
	}
}
