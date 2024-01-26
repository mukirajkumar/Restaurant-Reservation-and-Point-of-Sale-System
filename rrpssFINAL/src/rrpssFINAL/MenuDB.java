package rrpssFINAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MenuDB  
{  
	//attributes
	 /**
     * File location containing the Packages database (csv) stored in string type
     */
	private String packageFile = "C:\\Restaurant Info\\packages.csv";
	/**
     * File location containing the Ala carte items database (csv) stored in string type
     */
	private String itemFile = "C:\\Restaurant Info\\items.csv";
	/**
     * Array List of set package objects
     */
	private ArrayList<SetPackage> setPackageArrayList = new ArrayList<>();
	/**
     * Array List of MenuItems objects
     */
	private ArrayList<MenuItems> menuItemArrayList = new ArrayList<>();
	
	//constructors
	/**
     * Constructor: within constructor read and convert the databases and pass it into the arraylists
     */
	public MenuDB() {
		setPackageArrayList = convertSetData(readData(packageFile));
		menuItemArrayList = convertItemData(readData(itemFile));
	}
	/**
     * @return setPackage array list 
     */
	//get methods
	public ArrayList<SetPackage> getSetPackageList() {
        return setPackageArrayList;
    }
	/**
     * @return menuItems array list 
     */
	public ArrayList<MenuItems> getMenuItemsList() {
        return menuItemArrayList;
    }
	/**
     * Update any changes in the Set Package ArrayList so that it is reflected in the Set Package database 
     * For e.g A new set package was created and thus needs to be added to the database
     * @param setPackageList changed Set package List that needs to be updated in the database 
     */
	//update database
	public void updateSetPackage(ArrayList<SetPackage> setPackageList) {
		try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(packageFile));
	        
			for (int i=0; i<setPackageList.size(); i++) { 
				bw.write(setPackageList.get(i).getSetName());
				bw.write(",");
	        	String price = Float.toString(setPackageList.get(i).getSetPrice());
	        	bw.write(price);
	        	for (int j=0; j<setPackageList.get(i).getSetItems().size(); j++) {
	        		bw.write(",");
	        		bw.write(getSetPackageList().get(i).getSetItems().get(j));
	        	}
	        	bw.write("\n");
	        }
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}
	/**
     * Update any changes in the Menu Items ArrayList so that it is reflected in the Menu Items database 
     * For e.g A menu item was created and thus needs to be added to the database
     * @param menuItemList changed Menu Items List that needs to be updated in the database 
     */
	public void updateItemPackage(ArrayList<MenuItems> menuItemList) {
		try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(itemFile));
	        
	        for (int i=0; i<menuItemList.size(); i++) {
	        	bw.write(menuItemList.get(i).getItemName());
	        	bw.write(",");
	        	String price = Float.toString(menuItemList.get(i).getItemPrice());
	        	bw.write(price);
	        	bw.write(",");
	        	bw.write(menuItemList.get(i).getItemType().name());
	        	bw.write(",");
	        	bw.write(menuItemList.get(i).getItemDesp());
	        	bw.write("\n");
	        }
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}
	/**
     * @return list of lists of strings containing the data the file read
     * @param file file location of file that needs to be read
     */
	//read and convert data
	public List<List<String>> readData(String file) { 
	    List<List<String>> templist = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(file)))  {
	    	String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        templist.add(Arrays.asList(values));
		    }
	    } catch (IOException e) { e.printStackTrace(); }
	    return templist;
	}
	/**
     * @param Packages list of lists of strings containing the Packages data to be converted
     * @return array list of set packages
     */
	public ArrayList<SetPackage> convertSetData(List<List<String>> Packages) {
		ArrayList<SetPackage> setPackageArrayList = new ArrayList<>();
		for (int i=0; i<Packages.size(); i++) {
			String setName = Packages.get(i).get(0);
			float setPrice = Float.parseFloat(Packages.get(i).get(1));
			ArrayList<String> setItems = new ArrayList<>();
			for (int j=2; j<Packages.get(i).size(); j++) {
				setItems.add(Packages.get(i).get(j));
			}
			SetPackage S = new SetPackage(setName, setPrice, setItems);
			setPackageArrayList.add(S); 
		}
		return setPackageArrayList;
	}	
	/**
     * @param Items list of lists of strings containing the Menu Items data to be converted
     * @return array list of set packages
     */
	public ArrayList<MenuItems> convertItemData(List<List<String>> Items) {
		ArrayList<MenuItems> menuItemsArrayList = new ArrayList<>();
		for (int i=0; i<Items.size(); i++) {
			String itemName = Items.get(i).get(0);
			float itemPrice = Float.parseFloat(Items.get(i).get(1));
			String itemType = Items.get(i).get(2);
			String itemDesp = Items.get(i).get(3);

			MenuItems M = new MenuItems(itemName, itemDesp, itemPrice, MenuItems.ItemType.valueOf(itemType));
			menuItemsArrayList.add(M); 
		}
		return menuItemsArrayList;
	}
	
	
	//checking if items exist

    /**
     * check if menu item exists
     * @param itemName name of menuItem
     * @param menuItemList ArrayList of MenuItems
     * @return true if menuItems exist in menuItemList, returns false if menuItems return false
     */
    public boolean menuItemExists(String itemName, ArrayList<MenuItems> menuItemList) {	
    	for(int i=0; i<menuItemList.size(); i++){
			if(Objects.equals(itemName, menuItemList.get(i).getItemName())) { return true; }
		}
		return false;
	}
    
    /**
     * Check if set package exists
     * @param setName name of setPackage
     * @param setPackageList ArrayList of setPackage
     * @return true if setPackage exist in setPackageList, returns false if setPackage return false
     */
    public boolean setPackageExists(String setName, ArrayList<SetPackage> setPackageList){
        for(int i=0; i<setPackageList.size(); i++){
        	if(Objects.equals(setName, setPackageList.get(i).getSetName())) { return true; }
        }
        return false;
    }

    
    //viewing
    
    /**
     * View all items
     */
    public void viewItems() {
		menuItemArrayList = convertItemData(readData(itemFile));
		ArrayList<MenuItems> menuItemList = getMenuItemsList();
		int i=1;
		System.out.println("---Current Menu Items---");
		System.out.printf("   %-25s%-18s%-11s%s%n", "Name:", "Type:", "Price:", "Description:");
		for (MenuItems item: menuItemList)
		{
			System.out.printf("%d) %-25s%-18s%-11.2f", i, item.getItemName(), item.getItemType(), item.getItemPrice());
			System.out.println(item.getItemDesp());
			i++;
		}
		System.out.println();
	}
    
    /**
     * View all packages
     */
    public void viewPackages() {
    	setPackageArrayList = convertSetData(readData(packageFile));
    	ArrayList<SetPackage> setPackageList = getSetPackageList();
    	
    	System.out.println("---Current Set Packages---");
    	for (int i=0; i<setPackageList.size(); i++) {
    		System.out.println((i+1)+") Name: "+setPackageList.get(i).getSetName());
    		System.out.println("Price: "+setPackageList.get(i).getSetPrice());
    		System.out.println("Items: ");
    		setPackageList.get(i).viewSetItems();
    	System.out.println();
    	}
    	
    }
   
}

