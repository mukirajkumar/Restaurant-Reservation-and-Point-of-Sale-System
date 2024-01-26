package rrpssFINAL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MenuItemManager {
	
	/**
	 * 
	 * Constructor
	 */
	public MenuItemManager() {}
	/**
	 * 
	 * create a new item and view the new item
	 */
	public void create() {
		MenuDB menu = new MenuDB();
		ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter item name: ");
		String name = sc.nextLine();
		//sc.nextLine();
		System.out.println();
		
		if (menu.menuItemExists(name, menuItemList)) {			//check for existing
			System.out.println("Item already exists!");
			return;
		}
		
		System.out.println("Enter item description: ");
		String description = sc.nextLine();
		System.out.println();
		System.out.println("Enter item price: ");
		float price = sc.nextFloat();
		System.out.println();
		System.out.println("Enter item type:  (MAINCOURSE/DESSERT/DRINKS)");
		String strtype = sc.next();
		System.out.println();

		while (!(strtype.equals("MAINCOURSE")||strtype.equals("DESSERT")||strtype.equals("DRINKS"))) {										//check for ENUM
			System.out.println("Invalid input. Please re-enter.");
			System.out.println("Enter item type:  (MAINCOURSE/DESSERT/DRINKS)");
			strtype = sc.next();
			System.out.println();
		}

		MenuItems newItem = new MenuItems(name, description, price, MenuItems.ItemType.valueOf(strtype));
		menuItemList.add(newItem);
		menu.updateItemPackage(menuItemList);	
		System.out.println("Item created!");
		System.out.println("Name: "+name);
		System.out.println("Type: "+strtype);
		System.out.println("Description: "+description);
		System.out.println("Price: "+price);

	}
	/**
	 * 
	 * remove an item from the Menu database
	 */
	public void remove() {
		MenuDB menu = new MenuDB();
		ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
		
		System.out.println("Enter name of item to remove: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println();
		
		if (menu.menuItemExists(name, menuItemList)) {			//check for existing
			int index = getItemIndex(name, menuItemList);
			menuItemList.remove(index);
			menu.updateItemPackage(menuItemList);	
            System.out.println("Item removed!");
		}
		else {
			System.out.println("Item does not exist.");
		}
	}

	/**
	 * 
	 * Change/Update the attributes of a menu item such as name, description, price and type.
	 */
	public void update() {
		MenuDB menu = new MenuDB();
    	ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
    	
    	Scanner sc = new Scanner(System.in);
        String name, newName, newDesp, newType;
        int index, option;
        float newPrice;

        System.out.println("Enter name of item to update: ");
        name = sc.nextLine();
        System.out.println();
        if(!menu.menuItemExists(name, menuItemList)) {
            System.out.println("Item does not exist.");
            return;
        }

        index = getItemIndex(name, menuItemList);

        while(true){
        	System.out.println("1: Update item name");
            System.out.println("2: Update item description");
            System.out.println("3: Update item price");
            System.out.println("4: Update item type");
            System.out.println("5: Complete item update");
            System.out.println("Enter option: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (option){
                case 1:
                    while(true){
                    	System.out.println("Current name: "+menuItemList.get(index).getItemName());	//
                    	System.out.println();
                    	System.out.println("Enter new name of item: ");
                        newName = sc.nextLine();
                        System.out.println();
                          
                        if(menu.menuItemExists(newName, menuItemList)){
                            System.out.println("Item name already exists. Please re-enter.");
                        }
                        else{
                        	break;
                        }
                    }
                    System.out.println("Name updated! "+menuItemList.get(index).getItemName()+" -> "+newName); //
                    System.out.println();
                    menuItemList.get(index).setItemName(newName);
                    break;
                case 2:
                	System.out.println("Current description: "+menuItemList.get(index).getItemDesp());	//
                	System.out.println();
                	System.out.println("Enter new description of item: ");
                    newDesp = sc.nextLine();
                    System.out.println();
                    System.out.println("Description updated! "+menuItemList.get(index).getItemDesp()+" -> "+newDesp); //
                    System.out.println();
                    menuItemList.get(index).setItemDesp(newDesp);
                    break;
                case 3:
                	System.out.println("Current price: "+menuItemList.get(index).getItemPrice());	//
                	System.out.println();
                    System.out.println("Enter new price of item: ");
                    newPrice = sc.nextFloat();
                    System.out.println();
                    System.out.println("Price updated! "+menuItemList.get(index).getItemPrice()+" -> "+newPrice); //
                    System.out.println();
                    menuItemList.get(index).setItemPrice(newPrice);
                    break;
                case 4:
                	System.out.println("Current type: "+menuItemList.get(index).getItemType());	//
                	System.out.println();
                	System.out.println("Enter new type of item:  (MAINCOURSE/DESSERT/DRINKS)");
                    newType = sc.next();
                    System.out.println();
                    while (!(newType.equals("MAINCOURSE")||newType.equals("DESSERT")||newType.equals("DRINKS"))) {										//check for ENUM
            			System.out.println("Invalid input. Please re-enter.");
            			System.out.println("Enter item type:  (MAINCOURSE/DESSERT/DRINKS)");
            			newType = sc.next();
            			System.out.println();
            		}
                    System.out.println("Type updated! "+menuItemList.get(index).getItemType()+" -> "+newType); //
                    System.out.println();
                    menuItemList.get(index).setItemType(MenuItems.ItemType.valueOf(newType));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please re-enter.");
                    break;
            }
            if(option == 5){ break; }
        }

        menu.updateItemPackage(menuItemList);	
        System.out.println("Item updated!");
    }
	
	////////////////////////////////
	/**
	 * 
	 * View a specified item's details
	 */
	public void find() {
		MenuDB menu = new MenuDB();
		ArrayList<MenuItems> menuItemList = menu.getMenuItemsList();
		

		System.out.println("Enter name of item to view: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println();
		
		if(!menu.menuItemExists(name, menuItemList)) {
            System.out.println("Item does not exist.");
            return;
        }
        else {
        	int index = getItemIndex(name, menuItemList);
        	System.out.println("Name: " + menuItemList.get(index).getItemName());
			System.out.println("Type: " + menuItemList.get(index).getItemType());
			System.out.println("Description: " + menuItemList.get(index).getItemDesp());
			System.out.println("Price: " + menuItemList.get(index).getItemPrice());
        	
        }
		
	}
	
    /**
	 * @param itemName Name of item 
	 * @param menuItemList Array List of Menu Items
	 * retrieves an item's index in the database
	 */
    //gets item index
    public int getItemIndex(String itemName, ArrayList<MenuItems> menuItemList){     
        for(int i=0; i<menuItemList.size(); i++){
			if(Objects.equals(itemName, menuItemList.get(i).getItemName())) { return i; }
		}
		return -1;
    }
}	


