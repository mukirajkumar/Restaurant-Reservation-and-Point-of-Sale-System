package rrpssFINAL;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;  

public class ReservationApplication {
	/**
	 * 
	 * Constructor for other classes to call
	 */
	public ReservationApplication() {}
	/**
	 * 
	 * main class to call all the methods in ReservationManager and some methods in TableManager
	 */
	public static void main(String[] args) {
		int choice, option;
		
		ReservationManager reserve = new ReservationManager();
		TableManager table = new TableManager();
		
		Scanner sc = new Scanner(System.in);

		// This part need to be in the main(String[] args) for the auto remove to work
		Runnable R = new Runnable() {
			    public void run() {
			    boolean flag = true;
			    while (flag)
			    {
			    	reserve.removeBooking();
				    try {
				        Thread.sleep(1000);
				    } catch (InterruptedException e) {e.printStackTrace(); }
			    }
		    }
		};
		
		Thread t = new Thread(R);
	    // Lets run Thread in background..
	    // Sometimes you need to run thread in background for your Timer application..
	   	 t.start(); // starts thread in background..
	    // t.run(); // is going to execute the code in the thread's run method on the current thread..
         
       	 
		while (true) {
			System.out.println("=============================\n"
							  +"1: Place Reservations\n"
							  +"2: Remove Reservations\n"
							  +"3: Check Reservations\n"
							  +"4: View table availability\n"
							  +"5: Back\n"
							  +"=============================");
			System.out.println("Enter option: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (choice) {
            case 1:
            	reserve.createBooking();
            	break;
            case 2:
            	reserve.manuallyRemoveBooking();
            	break;
            case 3:
            	reserve.checkBooking();
            	break;
            case 4:
            	table.displayTableAvailability();
            	break;
            case 5:
            	break;
            default:
            	System.out.println("Invalid option. Please re-enter.");
                break;
            }
            if (choice == 5) { break; }
		}
	}
}

