package rrpssFINAL;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderApplication {
	/**
	 * Constructor
	 */
	public OrderApplication() {}
	/**
	 * Main class to call methods in OrderManager Class
	 */
	public static void main(String[] args) {
		int choice, option, tableNo;
		
		OrderManager OManager = new OrderManager();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("=============================\n"
							  +"1: Create new order\n"
							  +"2: View order\n"
							  +"3: Add item to order\n"
							  +"4: Remove item from order\n"
							  +"5: Print invoice & close order\n"
							  +"6: Back\n"
							  +"=============================");
			System.out.println("Enter option: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (choice) {
            case 1:
            	OManager.newOrder();
            	break;
            case 2:
            	OManager.viewOrder();
            	break;
            case 3:
            	OManager.addOrderItem();
            	break;
            case 4:
            	OManager.removeOrderItem();
            	break;
            case 5:
            	OManager.closeOrder();
            	break;
            case 6:
            	break;
            default:
            	System.out.println("Invalid option. Please re-enter.");
                break;
            }
            if (choice == 6) { break; }
		}
	}
}