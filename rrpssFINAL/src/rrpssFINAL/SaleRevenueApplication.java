package rrpssFINAL;

import java.util.ArrayList;
import java.util.Scanner;

public class SaleRevenueApplication {
	/**
	 * 
	 * main class to call all the methods in SaleRevenue
	 */
	public static void main(String[] args) {
		SaleRevenue SR = new SaleRevenue();
		int choice, option;
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("=============================\n"
							  +"1: Calculate daily revenue \n"
							  +"2: Calculate monthly revenue \n"
							  +"3: Back\n"
							  +"=============================");
			System.out.println("Enter option: ");
			choice = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			switch (choice) {
			case 1:
				SR.calculateDailyRevenue();
				break;
			case 2:
				SR.calculateMonthlyRevenue();
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

