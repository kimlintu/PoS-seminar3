package model.dto;

import integration.ItemDescription;
import model.Amount;

/**
 * This is a data container containing information about an item being
 * processed in the sale.
 * @author kim
 *
 */
public class ItemInformation {
	private final ItemDescription description;
	private final Amount price;
	private final int quantity;
	
	public ItemInformation(ItemDescription description, Amount price, int quantity) {
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public ItemDescription getItemDescription() {
		return description;
	}
	
	public Amount getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
