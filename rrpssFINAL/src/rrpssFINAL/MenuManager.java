package rrpssFINAL;

import java.util.Scanner;

public class MenuManager {
	/**
	 * Constructor
	 */
	public MenuManager() {}
	/**
	 * main class to run methods in MenuItemManager and SetPackageManager
	 */
	public static void main(String[] args) {
		int choice, option;
		
		MenuDB menu = new MenuDB();
		SetPackageManager SPManager = new SetPackageManager();
		MenuItemManager MIManager = new MenuItemManager();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("=============================\n"
							  +"1: Manage Menu Items\n"
							  +"2: Manage Set Packages\n"
							  +"3: Back\n"
							  +"=============================");
			System.out.println("Enter option: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (choice) {
            case 1:
            	while (true) {
        			System.out.println("=============================\n"
        							  +"1: Create item\n"
        							  +"2: Remove item\n"
        							  +"3: Update item\n"
        							  +"4: Find item\n"
        							  +"5: Display all items\n"
        							  +"6: Back\n"
        							  +"=============================");
        			
        			System.out.println("Enter option: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    switch (option) {
                    case 1:
                    	MIManager.create();
                    	break;
                    case 2:
                    	MIManager.remove();
                    	break;
                    case 3:
                    	MIManager.update();
                    	break;
                    case 4:
                    	MIManager.find();
                    	break;
                    case 5:
                    	menu.viewItems();
                    	break;
                    case 6:
                    	break;
                    default:
                    	System.out.println("Invalid option. Please re-enter.");
                        break;
                    }
                    if (option == 6) { break; }
        		}
            	break;
            case 2:
            	while (true) {
        			System.out.println("=============================\n"
        							  +"1: Create set package\n"
        							  +"2: Remove set package\n"
        							  +"3: Update set package\n"
        							  +"4: Find set package\n"
        							  +"5: Display all set packages\n"
        							  +"6: Back\n"
        							  +"=============================");
        			
        			System.out.println("Enter option: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    switch (option) {
                    case 1:
                    	SPManager.create();
                    	break;
                    case 2:
                    	SPManager.remove();
                    	break;
                    case 3:
                    	SPManager.update();
                    	break;
                    case 4:
                    	SPManager.find();
                    	break;
                    case 5:
                    	menu.viewPackages();
                    	break;
                    case 6:
                    	break;
                    default:
                    	System.out.println("Invalid option. Please re-enter.");
                        break;
                    }
                    if (option == 6) { break; }
        		}
            	break;
            case 3:
            	break;
            default:
            	System.out.println("Invalid option. Please re-enter.");
                break;
            }
            if (choice == 3) { break; }
		}
	}

}
