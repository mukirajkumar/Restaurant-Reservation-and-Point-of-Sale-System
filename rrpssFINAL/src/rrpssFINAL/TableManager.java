package rrpssFINAL;

import java.util.ArrayList;
import java.util.List;

public class TableManager {
	/**
    Create a new constructor for TableManager.
    */
	public TableManager() {}
	
	TableDB table = new TableDB();
	/**
    Display the availability of the tables.
    */
	public void displayTableAvailability() {
		TableDB table = new TableDB();
		ArrayList<Table> TableList = table.getTableList();
		
		System.out.printf("%-10s%-10s%s%n", "Table:", "Seats:", "Available?");
		for (Table item: TableList) {
			System.out.printf("%-10d%-10d", item.getTableNum(), item.getNumSeats());
			if (item.getAvailability()==true) {System.out.println("YES");}
			else {System.out.println("NO");}
			
		}
	}
	/**
    Check the availability of tables
    @param tableNum This is the table number
    @return the availability of tables and false if table number is invalid
    */
	public boolean checkAvailability (int tableNum) {
		TableDB table = new TableDB();
		ArrayList<Table> TableList = table.getTableList();
		
		for (Table item: TableList) {
			if (item.getTableNum()==tableNum) {
				return item.getAvailability();
			}
		}
		//invalid table no.
		System.out.println("Invalid table number!");
		return false;
	}
	/**
    Check the number of seats for the table number
    @param tableNum This is the table number
    @return the number of seats and -1 if table number is invalid
    */
	public int checkNumSeats (int tableNum) {
		TableDB table = new TableDB();
		ArrayList<Table> TableList = table.getTableList();
		
		for (Table item: TableList) {
			if (item.getTableNum()==tableNum) {
				return item.getNumSeats();
			}
		}
		//invalid table no.
		System.out.println("Invalid table number!");
		return -1;
	}
	

}
