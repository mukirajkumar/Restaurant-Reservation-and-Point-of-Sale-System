package rrpssFINAL;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.time.format.DateTimeParseException;


public class ReservationManager{
	
	ReservationDB rDB = new ReservationDB();
    Reservation aReservation = new Reservation();
    /**
    * Array list of reservations that are from the Reservation Database
    */
    ArrayList <Reservation> reservationList = rDB.getReservationList();
    
    TableDB tableDB = new TableDB();
    TableManager tableMgr = new TableManager();
    /**
     * Array list of Tables that are from the Table Database
     */
    ArrayList<Table> tableList = tableDB.tableArrayList;
    
    Scanner sc = new Scanner(System.in);
    /**
     * Constructor
     */
    //constructor
    public ReservationManager() {

	}
    /**
    Creates a new reservation
    */
    public void createBooking() throws DateTimeParseException {

    		//check for full booking
    		int full =1;
			for (Table item: tableList) {
					if (item.getAvailability()) {
						full=0;
					}
			}
			if (full==1) {
				System.out.println("Fully reserved! Unable to create new reservations.");
				return;
			}
			
            System.out.println("\nEnter number of people: ");
            int reservePax = sc.nextInt();
            sc.nextLine();

            int check=0;
    		for (Table item: tableList) {
    			if (item.getNumSeats()>=reservePax) {
    				if (item.getAvailability()) {
    					if (check==0) {
    						System.out.println("--available tables--");
    						System.out.printf("%-10s%s%n", "Table", "Seats");
    					}
    					System.out.printf("%-10d%d%n", item.getTableNum(), item.getNumSeats());
    					check++;
    				}
    			}
    		}
    		System.out.println();
    		if (check==0) {
    			System.out.println("No available seats!");
    			return;
    		}

    		int reserveTableNum;
            while (true) {
            	System.out.println("Enter table number: ");
                reserveTableNum = sc.nextInt();

                if (!tableMgr.checkAvailability(reserveTableNum)) {
                	System.out.println("Table is already taken!\n");
                }
                else if (tableMgr.checkNumSeats(reserveTableNum)<reservePax) {
                	System.out.println("Table doesn't have enough seats!\n");
                }
                else {
                	break;
                	//System.out.println("Table "+reserveTableNum+" assigned.");
                }
            }

            sc.nextLine();
            System.out.println("\nEnter name: (in CAPs)");
            String reserveName = sc.nextLine();

            System.out.println("\nEnter phone number: ");
            int reserveContact = sc.nextInt();
            
            LocalDate reserveDate;
            LocalTime reserveTime;
            
            //exception handling
            while (true) {
            try {
            	System.out.println("\nEnter date (enter as yyyy-MM-dd): ");
                String Date = sc.next();
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                reserveDate = LocalDate.parse(Date, formatterDate);
                break;
            } catch (DateTimeParseException e) {
            	System.out.println("Invalid input format! Please re-enter.");
            	continue;}
            }

            while (true) {
                try {
                	System.out.println("\nEnter time (enter as HH:mm):");
                    String Time = sc.next();
                    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                    reserveTime = LocalTime.parse(Time, formatterTime);
                    break;
                } catch (DateTimeParseException e) {
                	System.out.println("Invalid input format! Please re-enter.");
                	continue;}
                }
            
            boolean reserveMembershipStatus;
            while (true) {
            	System.out.println("\nMembership status (true/false): ");
                String mem = sc.next();
                if (mem.equals("true")||mem.equals("false")) {
                	reserveMembershipStatus=Boolean.parseBoolean(mem);
                	break;
                }
                System.out.println("Invalid input format! Please re-enter.");
            }

            Reservation newRes1 = new Reservation(reserveName, reserveTableNum, reserveContact, reservePax, reserveDate, reserveTime, reserveMembershipStatus);
            reservationList.add(newRes1);
            rDB.appendReservationList(reservationList);
            sc.nextLine();

	        for(Table tableCheck: tableList){
	            if(tableCheck.getTableNum() == tableList.get(reserveTableNum-1).getTableNum()){
	                tableCheck.occupied();
	            }
	        }
	        tableDB.updateTableList(tableList);
	        
	        System.out.println("\nTable "+reserveTableNum+" reserved for "+reserveName+" at "+reserveTime+" on "+reserveDate+".");
    }
    /**
    Check for existing reservations
    */
    public void checkBooking() {
        
        int count = 0;
        System.out.println("Enter customer name to check booking: (in CAPs)");
        String checkName;
        checkName = sc.nextLine();
        System.out.println();
        if (reservationList.isEmpty())
        {
        	System.out.println("No reservation made by "+checkName+" found.");
        }
        for (Reservation R : reservationList)
        {
            count++;
            if (R.getName().equals(checkName))
            {
                System.out.println("Name : "+ R.getName());
		        System.out.println("Table assigned : "+ R.getTableNum());
		        System.out.println("Phone Number : "+ R.getContact());
		        System.out.println("No of people : "+ R.getPax());
                System.out.println("Date : "+ R.getDate());
		        System.out.println("Time : "+ R.getTime());
		        System.out.println("Membership Status : "+ R.getMembershipStatus());
                break;
            }
            if (count == reservationList.size())
            {
                System.out.println("No reservation made by "+checkName+" found.");
            }
        }
    }
    /**
    Automatically removes an existing reservation if customer late for 30 minutes
    */
    public void removeBooking() {
    	
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        for (int i=0; i<reservationList.size(); i++)
        {
            if (reservationList.get(i).getDate().equals(date))
            {
                // can change the -30 to minutes you want
                if (time.until(reservationList.get(i).getTime(), ChronoUnit.MINUTES) <= -30)
                {
                    System.out.printf("%s has been late for 30 minutes, reservation for table %d has been removed\n", reservationList.get(i).getName(), reservationList.get(i).getTableNum());
                    for(Table tableCheck: tableList){
                        if(tableCheck.getTableNum() == tableList.get(reservationList.get(i).getTableNum()-1).getTableNum()){
                            tableCheck.vacant();
                        }
                    tableDB.updateTableList(tableList);
                    }
                    reservationList.remove(reservationList.get(i));
                    rDB.replaceReservationList(reservationList);
                    break;
                }
            }
        }
    }
    /**
    Manually remove a reservation
    */
    public void manuallyRemoveBooking() {
        System.out.println("Enter customer name to remove booking: (in CAPs)");
        String removeName;
        removeName = sc.nextLine();
        
        if (reservationList.isEmpty()) {
        	System.out.println("No reservation made by "+removeName+" found.");
        }
            
        for(int i=0; i<reservationList.size(); i++){
            //check for name
            if(reservationList.get(i).getName().equals(removeName)){
                //change availability
            	for(Table tableCheck: tableList) {
                    if(tableCheck.getTableNum() == tableList.get(reservationList.get(i).getTableNum()-1).getTableNum()){
                        tableCheck.vacant();
                    }
                tableDB.updateTableList(tableList);
                }
                System.out.printf("%s's reservation for table %d has been removed.\n", reservationList.get(i).getName(), reservationList.get(i).getTableNum());
                reservationList.remove(reservationList.get(i));
                rDB.replaceReservationList(reservationList);
            }
        }
    }
}

