package model.dto;

import java.util.ArrayList;
import java.util.List;

import model.pos.Item;

/**
 * A data container for all the available information about the sale, such as time of sale,
 * price information and the items processed so far.
 */
public class SaleInformation {
	private final PriceInformation priceInfo;
	private final List<PurchasedItemInformation> itemList;
	
	/**
	 * Constructs an object that contains the information about the current state of
	 * the sale.
	 * 
	 * @param time       A <code>LocalTime</code> object that represents the
	 *                   starting time of the sale.
	 * @param totalPrice An <code>Amount</code>
	 * @param itemList
	 */
	public SaleInformation(PriceInformation priceInfo, List<Item> itemList) {
		this.priceInfo = priceInfo;
		
		this.itemList = new ArrayList<>(); 
		createImmutableItemList(itemList);
	}

	/**
	 * Returns an object that contains the total price and the total VAT tax of the sale.
	 * @return An {@link PriceInformation} object that can be called to retrieve the total price 
	 * and total VAT tax.
	 */
	public PriceInformation getPriceInfo() {
		return priceInfo;
	}

	/**
	 * Returns a <code>List</code> of every item that was sold.
	 * 
	 * @return A <code>List</code> of {@link Item} objects.
	 */
	public List<PurchasedItemInformation> getListOfSoldItems() {
		return itemList;
	}
	
	/**
	 * Creates a list with immutable item objects from the items
	 * that has been sold.
	 * @param itemList The list of items that was sold.
	 * @return a new list with immutable data containers with information about
	 * each sold item.
	 */
	private List<PurchasedItemInformation> createImmutableItemList(List<Item> itemList) {
		for(Item i : itemList) {
			this.itemList.add(i.getItemInformation());
		}
		
		return this.itemList;
	}
}
