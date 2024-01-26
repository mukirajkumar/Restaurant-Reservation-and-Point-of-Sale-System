package rrpssFINAL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SetPackageManager {
    
	/**
     * Constructor
     */
    public SetPackageManager() {}

    /**
     * Create new set package
     */
    public void create() {
    	MenuDB menu = new MenuDB();
    	ArrayList<String> setPackageMenuItems = new ArrayList<String>();
        ArrayList<SetPackage> setPackageList = menu.getSetPackageList();
        ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
    	
    	Scanner sc = new Scanner(System.in);
        String setName, itemName;
        float setPrice;
        int option;
        
        while(true){
            System.out.println("Enter set package name: ");
            setName = sc.nextLine();        
            System.out.println();
            
            if(menu.setPackageExists(setName, setPackageList)){
            	System.out.println("Set package name already exists. Please choose another name.");
            }
            else{
            	break;
            }
        }

        menu.viewItems();
        while(true){
        	System.out.println("1: Add menu item\n"+"2: Complete set package creation");
            System.out.println("Enter option: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch(option){
                case 1:	
                	System.out.println("Enter menu item name: ");				// need to check if its a menu item!
                    itemName = sc.nextLine();
                    System.out.println();
                    
                    if (!menu.menuItemExists(itemName, menuItemList)) {
                    	System.out.println("Item doesn't exist.\n");
                    }
                    else {
                    	setPackageMenuItems.add(itemName);
                    	System.out.println(itemName+" added.");
                    	System.out.println();
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid option. Please re-enter.");
                    break;
            }
            if(option == 2){ break; }
        }
        
        System.out.println("Enter set package price: ");
        setPrice = sc.nextFloat();
        System.out.println();

        SetPackage newSetPackage = new SetPackage(setName, setPrice, setPackageMenuItems);
        setPackageList.add(newSetPackage);
        menu.updateSetPackage(setPackageList);			//updates ARRAY LIST
        System.out.println("Set package created!");
        int index = getSetPackageIndex(setName, setPackageList);
        System.out.println("Name: " + setName);
        System.out.println("Price: " + setPrice);
        System.out.println("Items: "); setPackageList.get(index).viewSetItems();
    }

    /**
     *  Remove a set package
     */
    public void remove() {
    	MenuDB menu = new MenuDB();
    	ArrayList<SetPackage> setPackageList = menu.getSetPackageList();
    	
    	Scanner sc = new Scanner(System.in);
        String setName;
        int index;

            System.out.println("Enter name of set package to remove: ");
            setName = sc.nextLine();
            System.out.println();
            
            if(!menu.setPackageExists(setName, setPackageList)){
                System.out.println("Set package does not exist.");
            }
            else{
                index = getSetPackageIndex(setName, setPackageList);
                setPackageList.remove(index);
                menu.updateSetPackage(setPackageList);	
                System.out.println("Set package removed!");
            }    
    }

    /**
     * Update set package
     */
    public void update() {
    	MenuDB menu = new MenuDB();
    	ArrayList<SetPackage> setPackageList = menu.getSetPackageList();
    	ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
    	
    	Scanner sc = new Scanner(System.in);
        String setName, newSetName, menuItemName;
        int index, option;
        float newSetPrice;

        System.out.println("Enter name of set package to update: ");
        setName = sc.nextLine();
        System.out.println();
        if(!menu.setPackageExists(setName, setPackageList)) {
            System.out.println("Set package does not exist.");
            return;
        }

        index = getSetPackageIndex(setName, setPackageList);

        while(true){
        	System.out.println("1: Update set package name");
            System.out.println("2: Update set package price");
            System.out.println("3: Add item to set package");
            System.out.println("4: Remove item from set package");
            System.out.println("5: Complete set package update");
            System.out.println("Enter option: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (option){
                case 1:
                    while(true){
                    	System.out.println("Current name: "+setPackageList.get(index).getSetName());	//
                    	System.out.println();
                    	System.out.println("Enter new name of set package: ");
                        newSetName = sc.nextLine();
                        System.out.println();
                          
                        if(menu.setPackageExists(newSetName, setPackageList)){
                            System.out.println("Set package name already exists. Please re-enter.");
                        }
                        else{
                        	break;
                        }
                    }
                    System.out.println("Name updated! "+setPackageList.get(index).getSetName()+" -> "+newSetName); //
                    System.out.println();
                    setPackageList.get(index).setSetName(newSetName);
                    break;
                
                case 2:
                	System.out.println("Current price: "+setPackageList.get(index).getSetPrice());	//
                	System.out.println();
                    System.out.println("Enter new price of set package: ");
                    newSetPrice = sc.nextFloat();
                    System.out.println();
                    System.out.println("Price updated! "+setPackageList.get(index).getSetPrice()+" -> "+newSetPrice); //
                    System.out.println();
                    setPackageList.get(index).setSetPrice(newSetPrice);
                    break;
                case 3: 
                	menu.viewItems();
                	System.out.println("Enter menu item to add: ");
                	menuItemName = sc.nextLine();
                    
                    if (!menu.menuItemExists(menuItemName, menuItemList)) {
                    	System.out.println("Item doesn't exist!\n");
                    }
                    else if (setPackageList.get(index).itemInSet(menuItemName)) {
                    	System.out.println("Item is already in set!\n");;
                    }
                    else {
                    	setPackageList.get(index).addSetItem(menuItemName); 
                    	System.out.println("Item added.\n");
                    }        
                    break;
                case 4:
                	System.out.println("Current items in set: ");
                	setPackageList.get(index).viewSetItems();
                	System.out.println();
                	System.out.println("Enter menu item to remove: ");
                    menuItemName = sc.nextLine();
                    
                	if (setPackageList.get(index).itemInSet(menuItemName)) {
                		setPackageList.get(index).removeSetItem(menuItemName);
                		System.out.println("Item removed.\n");
                    }
                    else {
                    	System.out.println("Item not in set!\n");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please re-enter.");
                    break;
            }
            if(option == 5){ break; }
        }

        menu.updateSetPackage(setPackageList);
        System.out.println("Set package updated!");
    }
    
    /**
     * View a set package
     */
    public void find() {
    	MenuDB menu = new MenuDB();
    	ArrayList<SetPackage> setPackageList = menu.getSetPackageList();
    	
    	Scanner sc = new Scanner(System.in);
        String setName;
        int index;

        System.out.println("Enter name of set package to view: ");
        setName = sc.nextLine();
        System.out.println();
        
        if(!menu.setPackageExists(setName, setPackageList)){
            System.out.println("Set package does not exist.");
        }
        else {
            index = getSetPackageIndex(setName, setPackageList);
            System.out.println("Name: " + setPackageList.get(index).getSetName());
            System.out.println("Price: " + setPackageList.get(index).getSetPrice());
            System.out.println("Items: "); setPackageList.get(index).viewSetItems();
        } 
    }
    
    
    /**
     * Gets set package index
     * @param setName name of setPackage
     * @param setPackageList ArrayList of setPackage
     * @return index of setPackage, returns -1 if out setPackageList range
     */
    public int getSetPackageIndex(String setName, ArrayList<SetPackage> setPackageList){
        for(int i=0; i<setPackageList.size(); i++){
        	if(Objects.equals(setName, setPackageList.get(i).getSetName())) { return i; }
        }
        return -1;
    }
    
     
}
