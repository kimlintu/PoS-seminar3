package integration.dbhandler.data;

import integration.dbhandler.InventorySystem;
import model.dto.ItemPrice;
import model.util.IdentificationNumber;

/**
 * This class is a data container that contains information pertaining to an item, including its
 * name, price info contained in {@link PriceInformation} and its unique id.
 * @author kim
 *
 */
public class ItemDescription {
	private final String name;
	private final ItemPrice priceInfo;
	private final IdentificationNumber id;
	
	/**
	 * Constructs an item description that is used to describe an unique item that 
	 * exists in the {@link InventorySystem}. 
	 * @param name Name of the item.
	 * @param price Price of the item.
	 * @param vatRate The VAT rate that gets applied to the item price.
	 * @param id An unique id that identifies the item.
	 */
	public ItemDescription(String name, ItemPrice priceInfo, IdentificationNumber id) {
		this.name = name;
		this.priceInfo = priceInfo;
		this.id = id;
	}
	
	/**
	 * Returns the item id that's being stored in the description.
	 * 
	 * @return The <code>IdentificationNumber</code> representing the item id.
	 */
	public IdentificationNumber getID() {
		return id;
	}
	
	/**
	 * Returns the name of the item that the <code>ItemDescription</code>
	 * is describing.
	 * @return The item <code>name</code> as a <code>String</code>.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns an {@link ItemPrice} that contains information about the item price and vat rate.
	 * @return An {@link ItemPrice} object.
	 */
	public ItemPrice getPriceInfo() {
		return priceInfo;
	}
	
	/**
	 * Compares the item description to the object. 
	 * 
	 * @param anObject The object to compare the <code>ItemDescription</code> against.
	 * @return <code>true</code> if the object represents an <code>ItemDescription</code> 
	 * and has an {@link IdentificationNumber} equivalent to the other <code>ItemDescription</code>.
	 */
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof ItemDescription) {
			ItemDescription i = (ItemDescription) anObject;
			return this.id.equals(i.getID());
		}
		
		return false;
	}
}
