package rrpssFINAL;

import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {
	/**
	 * 
	 * main class to call main classes in the Application Classes
	 */
	public static void main(String[] args) {
		int choice, option;
		
		MenuDB database = new MenuDB();
		MenuManager Menu = new MenuManager();
		//TableManager Table = new TableManager();
		//Table.displayTableAvailability();
		OrderApplication Order = new OrderApplication();
		ReservationApplication Reservation = new ReservationApplication();
		SaleRevenueApplication Revenue = new SaleRevenueApplication();
		
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("=============================\n"
							  +"1: Manage Menu \n"
							  +"2: Manage Orders \n"
							  +"3: Manage Reservation \n"
							  +"4: Generate Sales Report \n"
							  +"5: Exit\n"
							  +"=============================");
			System.out.println("Enter option: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (choice) {
            case 1:
            	Menu.main(args);
            	break;
            case 2:
            	Order.main(args);
            	break;
            case 3:
            	Reservation.main(args);
            	break;
            case 4:
            	Revenue.main(args);
            	break;
            case 5:
                System.out.println("Shutting down system...");
            	break;
            default:
            	System.out.println("Invalid option. Please re-enter.");
                break;
            }
            if (choice == 5) { break; }
		}
	}
}

