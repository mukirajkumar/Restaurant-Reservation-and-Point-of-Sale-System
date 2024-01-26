package rrpssFINAL;

public class MenuItems {
	public enum ItemType {MAINCOURSE, DRINKS, DESSERT}; 
	private String itemName;
	private String itemDesp;
	private float itemPrice;
	private ItemType itemType;
	/**
	 * 
	 * MenuItems Constructor: need to key in item name, item description, item price and item type
	 */
	public MenuItems(String itemName, String itemDesp, float itemPrice, ItemType itemType) {
		this.itemDesp = itemDesp;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
	}
	/**
	 * 
	 * @return item name
	 */
	public String getItemName() {
		return this.itemName;
	}
	/**
	 * 
	 * @return item description
	 */
	public String getItemDesp() {
		return this.itemDesp;
	}
	/**
	 * 
	 * @return item price
	 */
	public float getItemPrice() {
		return this.itemPrice;
	}
	/**
	 * 
	 *@return item type
	 */
	public ItemType getItemType() {
		return this.itemType;
	}

	/**
	 * 
	 * @param itemName name of ala-carte item that you want to create
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 
	 * @param itemDesp Description of ala-carte item that you want to create
	 */
	public void setItemDesp(String itemDesp) {
		this.itemDesp = itemDesp;
	}

	/**
	 * 
	 * @param itemPrice Price of ala-carte item that you want to create
	 */
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * 
	 * @param type Type of Ala-carte Item can be categorised into MAINCOURSE, DRINKS AND DESSERT
	 */
	public void setItemType(ItemType type) {
		this.itemType = type;
	}

}

