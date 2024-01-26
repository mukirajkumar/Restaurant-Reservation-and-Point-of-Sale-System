package rrpssFINAL;

import java.time.LocalDate;    
import java.time.LocalTime;  


public class Reservation {
	/**
	 * table number
	 */
    private int tableNum;
    /**
	 * date of reservation
	 */
	private LocalDate date;
	/**
	 * date of reservation
	 */
	private LocalTime time;
	/**
	 * No. of people under the reservation
	 */
	private int pax;
	/**
	 * Name of customer who created the reservation
	 */
	private String name;
	/**
	 * Contact no. of customer who created the reservation
	 */
	private int contact;
	TableDB table = new TableDB();
	/**
	 * whether customer who made the reservation is a member or not
	 */
	private boolean membershipStatus;

	/**
	 * 
	 * @param tableNum table number
	 * @param date date of reservation
	 * @param time reservation time
	 * @param pax No. of people under the reservation
	 * @param name Name of customer who created the reservation
	 * @param contact Contact no. of customer who created the reservation
	 * @param membershipStatus whether customer who made the reservation is a member or not
	 */
	
	//constructors
	public Reservation(String name, int tableNum, int contact, int pax, LocalDate date, LocalTime time, boolean membershipStatus) {
		//TableList();
        this.tableNum = tableNum;
		this.date = date;
		this.time = time;
		this.pax = pax;
		this.name = name;
		this.contact = contact;
        this.membershipStatus = membershipStatus;
	}
	
	public Reservation() {
	}

	//changing availability
	/**
	 * 
	 * @param tableNum table number of table that is occupied 
	 * sets empty table as occupied
	 */
	public void occupiedTable(int tableNum){
		for(Table t : table.tableArrayList){
			if(t.getTableNum() == tableNum){
				t.occupied();
			}
		}
	}
	/**
	 * 
	 * @param tableNum table number of table that is empty 
	 * sets empty table as vacant
	 */
	public void vacantTable(int tableNum){
		for(Table t : table.tableArrayList){
			if(t.getTableNum() == tableNum){
				t.vacant();
			}
		}
	}
	
	//get and set methods
	/**
	 * 
	 * @return whether customer who made the reservation is a member or not 
	 */
	public boolean getMembershipStatus() {
		return this.membershipStatus;
	}
	/**
	 * 
	 * @param membershipStatus whether customer who made the reservation is a member or not 
	 */
	public void setMembershipStatus(boolean membershipStatus) {
		this.membershipStatus = membershipStatus;
	}
	/**
	 * 
	 * @return table number
	 */
    public int getTableNum() {
		return this.tableNum;
	}
    /**
	 * 
	 * @param tableNum table number
	 */
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	/**
	 * 
	 * @return date of reservation(yyyy-MM-dd)
	 */
	public LocalDate getDate() {
		return this.date;
	}
	/**
	 * 
	 * @param date date of reservation(yyyy-MM-dd)
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * 
	 * @return time of reservation
	 */
	public LocalTime getTime() {
		return this.time;
	}
	/**
	 * 
	 * @param time time of reservation to be set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
	/**
	 * 
	 * @return No. of people under the reservation
	 */
	public int getPax() {
		return this.pax;
	}
	/**
	 * 
	 * @param pax No. of people under the reservation
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}
	/**
	 * 
	 * @return Name of customer who made the reservation
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @param name Name of customer who made the reservation
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return Contact of customer that made the reservation
	 */
	public int getContact() {
		return this.contact;
	}
	/**
	 * 
	 * @param contact Contact of customer that made the reservation
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

}