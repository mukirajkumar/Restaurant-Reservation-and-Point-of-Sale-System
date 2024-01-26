package rrpssFINAL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OrderManager {

	Order newOr;
	Order tempOrder = new Order();
	MenuDB menu = new MenuDB();
	/**
	 * Array List of Menu Items that exists in the Menu Items Database
	 */
	ArrayList<MenuItems> mArrayList = menu.getMenuItemsList();
	/**
	 * Array List of Set Packages that exists in the Set Package Database
	 */
	ArrayList<SetPackage> sArrayList = menu.getSetPackageList();
	/**
	 * Array List of Orders
	 */
	ArrayList<Order> oArrayList = new ArrayList<>();
	TableDB tables = new TableDB();
	/**
	 * Array List of Tables
	 */
	ArrayList<Table> tableArrayList = tables.getTableList();
	RevenueDB revenue = new RevenueDB();
	

	Scanner sc = new Scanner(System.in);
	/**
	 * Constructor
	 */
	public OrderManager(){
	}
	/**
	 * @return Array List of orders
	 */
	public ArrayList<Order> getOrderArray() {
		return oArrayList;
	}
	/**
	 * create a new order
	 */
	public void newOrder() {
		ReservationDB Rdb = new ReservationDB();
		ArrayList<Reservation> RArray =  Rdb.getReservationList();
		
		System.out.println("Enter Staff ID number: ");
		int staffID = sc.nextInt();
		System.out.println();
		while (true){
			System.out.println("Enter table no:");
			int tableNum = sc.nextInt();
			System.out.println();
			
			//table input check
			int index = getTableIndex(tableNum);
			if (index==-1) {
				System.out.println("Invalid table number!");
				System.out.println();
			}
			else if (tables.tableArrayList.get(index).getAvailability() == true) {
				System.out.println("Table is vacant!");
				System.out.println();
			}
			else {
				newOr = new Order(staffID, tableNum);
				oArrayList.add(newOr);
				System.out.println("Order created.");
				System.out.println();
				break;
			}
		}
	}  
	/**
	 * view an order
	 */

	public int viewOrder() {
		System.out.println("Enter table no:");
		int tableNum = sc.nextInt();
		System.out.println();
		
		int index = getTableIndex(tableNum);
		int odex = getOrderIndex(tableNum);
		
		if (index==-1) {
			System.out.println("Invalid table number!");
			System.out.println();
		}
		else if (tables.tableArrayList.get(index).getAvailability() == true) {
			System.out.println("Table is vacant!");
			System.out.println();
		}
		else if (odex==-1){
			System.out.println("No orders for this table!");
			System.out.println();
		}
		else {
			System.out.println("Ala Carte Items: ");
			for(int a=0; a<oArrayList.get(odex).iArrayList.size(); a++){
				System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).iArrayList.get(a).getItemName(), oArrayList.get(odex).iArrayList.get(a).getItemPrice());
			}
			System.out.println();
			System.out.println("Set Package Items: ");
			for(int b=0; b<oArrayList.get(odex).sArrayList.size(); b++){
				System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).sArrayList.get(b).getSetName(), oArrayList.get(odex).sArrayList.get(b).getSetPrice());
			}
		}
		return tableNum;
	}
	/**
	 * add an order item
	 */
	public void addOrderItem() {
		int sel, index, tableNum, ind;
		System.out.println("Enter table no: ");
		tableNum = sc.nextInt();
		
		index = getTableIndex(tableNum);
		int odex = getOrderIndex(tableNum);
		
		if (index==-1) {
			System.out.println("Invalid table number!");
			System.out.println();
		}
		else if (tables.tableArrayList.get(index).getAvailability() == true) {
			System.out.println("Table is vacant!");
			System.out.println();
		}
		else if (odex==-1){
			System.out.println("No orders for this table!");
			System.out.println();
		}
		else {
			while (true){
				System.out.println("\nWhat do you want to order? ");
				System.out.println("1: AlaCarte");
				System.out.println("2: Set Package");
				System.out.println("3: Return");
				sel = sc.nextInt();
				System.out.println();
				switch(sel){
					case 1: 
						menu.viewItems();
						System.out.println("\nEnter item to be added: ");
						ind = sc.nextInt();
						if(ind-1 >= mArrayList.size()||ind-1<0){
							System.out.println("Invalid input!");
							continue;
						}
						else{
							oArrayList.get(odex).addItems(this.mArrayList.get(ind-1));
							System.out.println(mArrayList.get(ind-1).getItemName()+" added.");
						}
						break;
					case 2: 
						menu.viewPackages();
						System.out.println("\nEnter item to be added: ");
						ind = sc.nextInt();
						if(ind-1 >= sArrayList.size()||ind-1<0) {
							System.out.println("Invalid input!");
							continue;
						}
						else{
							oArrayList.get(odex).addSetItems(this.sArrayList.get(ind-1));
							System.out.println(sArrayList.get(ind-1).getSetName()+" added.");
						}
						break;
					case 3:
						System.out.println("Orders updated.");
						break;
					default: 
						System.out.println("Invaild selection, please enter again");
						break;
				}
				if (sel==3) {break;}
			}
		}
	}
	/**
	 * remove an order item
	 */
	public void removeOrderItem() {
		int sel, index, tableNum, ind;
		System.out.println("Enter table no: ");
		tableNum = sc.nextInt();
		
		index = getTableIndex(tableNum);
		int odex = getOrderIndex(tableNum);
		
		if (index==-1) {
			System.out.println("Invalid table number!");
			System.out.println();
		}
		else if (tables.tableArrayList.get(index).getAvailability() == true) {
			System.out.println("Table is vacant!");
			System.out.println();
		}
		else if (odex==-1){
			System.out.println("No orders for this table!");
			System.out.println();
		}
		else {
			while (true){
				System.out.println("\nWhat do you want to remove? ");
				System.out.println("1: AlaCarte item");
				System.out.println("2: Set Package item");
				System.out.println("3: Exit");
				sel = sc.nextInt();
				System.out.println();
				switch(sel){
					case 1: 
						System.out.println("Current items in order: ");
						for(int a=0; a<oArrayList.get(odex).iArrayList.size(); a++){
							System.out.printf("%d. %s\n", (a+1), oArrayList.get(odex).iArrayList.get(a).getItemName());
						}
						
						System.out.println("\nSelect item to remove: ");
						ind = sc.nextInt();
						if(ind-1 >= oArrayList.get(odex).iArrayList.size()||ind-1<0){
							System.out.println("Invalid input!");
							continue;
						}
						else{
							System.out.println(oArrayList.get(odex).iArrayList.get(ind-1).getItemName()+" removed.");
							oArrayList.get(odex).removeItems(oArrayList.get(odex).iArrayList.get(ind-1));
							
						}
						break;
					case 2: 
						System.out.println("Set Package Items: ");
						for(int b=0; b<oArrayList.get(odex).sArrayList.size(); b++){
							System.out.printf("%d. %s\n", (b+1), oArrayList.get(odex).sArrayList.get(b).getSetName());
						}
						
						System.out.println("\nEnter package to remove: ");
						ind = sc.nextInt();
						if(ind-1 >= oArrayList.get(odex).sArrayList.size()||ind-1<0){
							System.out.println("Invalid input!");
							continue;
						}
						else{
							System.out.println(oArrayList.get(odex).sArrayList.get(ind-1).getSetName()+" removed.");
							oArrayList.get(odex).removeSetItems(oArrayList.get(odex).sArrayList.get(ind-1));
						}
						break;
					case 3:
						System.out.println("Orders updated.");
						break;
					default: 
						System.out.println("Invaild selection, please enter again");
						break;
				}
				if (sel==3) {break;}
			}
		}
	}
	/**
	 * close the order and print order invoice
	 * update the reservation database to remove the corresponding reservation
	 * reset table availability of table whose order is closed 
	 * remove order from Array List of Orders
	 */
	public void closeOrder() {ReservationDB Rdb = new ReservationDB();
		ArrayList<Reservation> RArray =  Rdb.getReservationList();
		
		ArrayList<Order> saveOrderArrayList = new ArrayList<>();
		
		int sel, tableNum;
		float alaP=0, setPP=0, totalPrice=0;
		
		System.out.println("Enter table no:");
		tableNum = sc.nextInt();
		System.out.println();
		
		int index = getTableIndex(tableNum);
		int odex = getOrderIndex(tableNum);
		
		if (index==-1) {
			System.out.println("Invalid table number!");
			System.out.println();
			return;
		}
		else if (tables.tableArrayList.get(index).getAvailability() == true) {
			System.out.println("Table is vacant!");
			System.out.println();
			return;
		}
		else if (odex==-1){
			System.out.println("No orders for this table!");
			System.out.println();
			return;
		}
		else {
			System.out.println("Ala Carte Items: ");
			for(int a=0; a<oArrayList.get(odex).iArrayList.size(); a++){
				System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).iArrayList.get(a).getItemName(), oArrayList.get(odex).iArrayList.get(a).getItemPrice());
			}
			System.out.println();
			System.out.println("Set Package Items: ");
			for(int b=0; b<oArrayList.get(odex).sArrayList.size(); b++){
				System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).sArrayList.get(b).getSetName(), oArrayList.get(odex).sArrayList.get(b).getSetPrice());
			}
		}
		System.out.println("\nPlease ensure all orders are correct. ");
		System.out.println();
		System.out.println("1: Bill table");
		System.out.println("2: Return");
		sel = sc.nextInt();
		switch(sel){
			case 1: 
			
				//calculations
				for(int a=0; a<oArrayList.get(odex).iArrayList.size(); a++){
					alaP = alaP + oArrayList.get(odex).iArrayList.get(a).getItemPrice();
				}
				for(int b=0; b<oArrayList.get(odex).sArrayList.size(); b++){
					setPP = setPP + oArrayList.get(odex).sArrayList.get(b).getSetPrice();
				}
				////////////////////////////////////////////////////////////////////////////////////////////
				//PRINT RECEIPT
				
				System.out.println("Printing receipt...");
				
				System.out.println("\n===========RECEIPT===========");
				
				boolean MS = false;
				for (Reservation res : RArray) {
					if (res.getTableNum() == tableNum) {
						boolean memStatus = res.getMembershipStatus();
						LocalDate dateOfOrder = res.getDate();
						LocalTime timeOfOrder = res.getTime();
						
						System.out.println("Date: "+dateOfOrder);
						System.out.println("Time: "+timeOfOrder);
						System.out.println("Table number: "+tableNum);

						MS=memStatus;
						break;
					}
				}
				System.out.println();
				
				for(int a=0; a<oArrayList.get(odex).iArrayList.size(); a++){
					System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).iArrayList.get(a).getItemName(), oArrayList.get(odex).iArrayList.get(a).getItemPrice());
				}
				for(int b=0; b<oArrayList.get(odex).sArrayList.size(); b++){
					System.out.printf("%-20s $%.2f\n", oArrayList.get(odex).sArrayList.get(b).getSetName(), oArrayList.get(odex).sArrayList.get(b).getSetPrice());
				}
				
				float subtotal = alaP + setPP;
				oArrayList.get(odex).setAlaCarteTotalPrice(alaP);
				oArrayList.get(odex).setPackageTotalPrice(setPP);
				oArrayList.get(odex).setTotalPrice(totalPrice);
				
				
				/* System.out.println(oArrayList.get(odex).getAlaCarteTotalPrice());
				System.out.println(oArrayList.get(odex).getPackageTotalPrice());
				System.out.println(oArrayList.get(odex).getTotalPrice()); */
				
				System.out.println();
				
				float taxesamt = (float)0.07 * subtotal;
				if (MS == true) {
					float discountAmt = (float) 0.10 * subtotal;
					totalPrice = subtotal + taxesamt - discountAmt;
					System.out.printf("%-20s $%.2f\n", "Subtotal:", subtotal);
					System.out.printf("%-20s $%.2f\n", "GST(7%):", taxesamt);
					System.out.printf("%-20s $%.2f\n", "Membership(10%):", discountAmt);
					System.out.printf("%-20s $%.2f\n", "Total:", totalPrice);
					
					/*System.out.println("SubTotal: $" + subtotal);
					System.out.println("GST(7%): $" + taxesamt);
					System.out.println("Membership Discount: -$" + discountAmt);
					System.out.println("Total: $" + totalPrice);*/

				} else {
					totalPrice = subtotal + taxesamt;
					System.out.printf("%-20s $%.2f\n", "Subtotal:", subtotal);
					System.out.printf("%-20s $%.2f\n", "GST(7%):", taxesamt);
					System.out.printf("%-20s $%.2f\n", "Total:", totalPrice);
					/*System.out.println("SubTotal: " + subtotal);
					System.out.println("GST(7%): " + taxesamt);
					System.out.println("Total: " + totalPrice);*/

				}
				
				
				System.out.println("\n=============================");
				
				//reset availability
				for(Table tableCheck: oArrayList.get(odex).tM.table.tableArrayList){
					if(tableCheck.getTableNum() == (oArrayList.get(odex).tM.table.tableArrayList.get(tableNum-1).getTableNum())){
						tableCheck.vacant();
					}
				}
				oArrayList.get(odex).tM.table.updateTableList(oArrayList.get(odex).tM.table.tableArrayList);
				
				Order saveOrder = new Order(oArrayList.get(odex).getDineTime(), oArrayList.get(odex).getDineDate(), 
						oArrayList.get(odex).getTableNum(), alaP, setPP, subtotal);
				
				saveOrderArrayList.add(saveOrder);
				revenue.appendRevenueList(saveOrderArrayList);

				//remove from array list
				oArrayList.remove(odex);
				
				//remove reservation made
				int i=0;
				for (Reservation res : RArray) {
					if (res.getTableNum() == tableNum) {
						RArray.remove(RArray.get(i));
		                Rdb.replaceReservationList(RArray);
						break;
					}
					i++;
				} 
				
				System.out.println("\nOrder closed.");
				break;
			case 2: 
				break;
		}
	}
	/**
	 * @param tableNum table number 
	 * @return table Index in the table database
	 */
	//gets table index
    public int getTableIndex(int tableNum){     
    	//Order tempOrder = new Order();
    	for(int i=0; i<tables.tableArrayList.size(); i++) {
    		if(tableNum == tables.tableArrayList.get(i).getTableNum()) { return i; }
		}
		return -1;
    }
    /**
	 * @param tableNum table number 
	 * @return Order Index in the order Array List that corresponds with the table number inputted
	 */
    //gets order index
    public int getOrderIndex(int tableNum){   
    	for(int i=0; i<oArrayList.size(); i++){
			if(oArrayList.get(i).getTableNum() == tableNum){
				return i;
			}
		}
		return -1;
    }

}
