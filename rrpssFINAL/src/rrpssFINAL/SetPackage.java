package rrpssFINAL;

import java.util.ArrayList;

public class SetPackage{
	/**
	 The name of the package.
	*/
    private String setName;
    /**
    The price of the package.
   */
    private float setPrice;
    /**
    Array list for storing the set items.
   */
    private ArrayList<String> setItems;
    /**
     * Creates a set package with given name,
     * price and items.
     * @param setName This set package name.
     * @param setPrice This set package price.
     * @param setItems This set package items.
     */
    public SetPackage(String setName, float setPrice, ArrayList<String> setItems){
        this.setName = setName;
        this.setPrice = setPrice;
        this.setItems = setItems;
    };
    /**
     * Adds an item to the set package.
     * @param itemName Item's name.
     */
    public void addSetItem(String itemName){
        setItems.add(itemName);
        return;
    }
    /**
     * Removes an item from the set package.
     * @param itemName Item's name.
     */
    public void removeSetItem(String itemName){
        int index = setItems.indexOf(itemName);
        setItems.remove(index);
        return;
    }
    /**
     * Check if an item exists in the set package.
     * @param itemName Item's name.
     */
    public boolean itemInSet(String itemName){
        if(setItems.contains(itemName)){
            return true;
        }
        return false;
    }
    /**
     * View an item from the set package.
     */
    public void viewSetItems() {
        for(int i=0; i < setItems.size(); i++){
            System.out.println("-"+setItems.get(i));
        }
        return;
    }
    
    //get and set methods
    /**
     * Gets an item from the set package.
     * @return the set package item.
     */
    public ArrayList<String> getSetItems() {
    	return this.setItems;
    }
    /**
     * Gets the set package name.
     * @return the set package name.
     */
    public String getSetName() {
        return this.setName;
    }
    /**
     * Gets the set package price.
     * @return the set package price.
     */
    public float getSetPrice() {
        return this.setPrice;
    }
    /**
     * Sets the set package name.
     * @param setName This set package name.
     */
    public void setSetName(String setName) {
        this.setName = setName;
    }
    /**
     * Sets the set package price.
     * @param setPrice This set package price.
     */
    public void setSetPrice(float setPrice) {
        this.setPrice = setPrice;
    }

}
