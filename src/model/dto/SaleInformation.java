package model.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Item;

/**
 * A data container for all the available information about the sale, such as time of sale,
 * price information and the items processed so far.
 * @author kim
 *
 */
public class SaleInformation {
	private final LocalTime time;
	private final PriceInfo priceInfo;
	private final List<ItemInformation> itemList;

	/**
	 * Constructs an object that contains the information about the current state of
	 * the sale.
	 * 
	 * @param time       A <code>LocalTime</code> object that represents the
	 *                   starting time of the sale.
	 * @param totalPrice An <code>Amount</code>
	 * @param itemList
	 */
	public SaleInformation(LocalTime time, PriceInfo priceInfo, List<Item> itemList) {
		this.time = time;
		this.priceInfo = priceInfo;
		
		this.itemList = new ArrayList<>(); 
		createImmutableItemList(itemList);
	}

	/**
	 * Returns an object that contains the total price and the total VAT tax of the sale.
	 * @return An {@link PriceInfo} object that can be called to retrieve the total price 
	 * and total VAT tax.
	 */
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}
	
	/**
	 * Returns the time that the sale was started.
	 * 
	 * @return Time of the sale as a <code>LocalTime</code> object.
	 */
	public LocalTime getTimeOfSale() {
		return time;
	}

	/**
	 * Returns a <code>List</code> of every item that was sold.
	 * 
	 * @return A <code>List</code> of {@link Item} objects.
	 */
	public List<ItemInformation> getListOfSoldItems() {
		return itemList;
	}
	
	private List<ItemInformation> createImmutableItemList(List<Item> itemList) {
		for(Item i : itemList) {
			this.itemList.add(i.getItemInformation());
		}
		
		return this.itemList;
	}

	/**
	 * The string representation of this instance. Shows the name, price and
	 * quantity of every sold item. Also displays the total price, total VAT tax and
	 * the time when the sale was started.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ItemInformation i : itemList) {
			sb.append(i.toString() + "\n");
		}

		sb.append("\n");
		sb.append(String.format("Total price: %-10.2fTotal Vat: %-10.2fTime: %s", priceInfo.getTotalPrice().getValue(),
				priceInfo.getTotalVat().getValue(), time.toString()));

		return sb.toString();
	}
}
