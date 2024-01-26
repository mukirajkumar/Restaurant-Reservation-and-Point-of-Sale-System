package rrpssFINAL;

public class Table {
	/**
	 * if table is available for reservation
	 */
	private boolean availability;
	/**
	 * Maximum number of seats per table 
	 */
	private int numSeats;
	/**
	 * table number
	 */
	private int tableNum;
	//private boolean membershipStatus;
	
	/**
	 * 
	 * Table constructor
	 * @param numSeats number of seats
	 * @param tableNum table number 
	 * @param availability whether table is available
	 */
	public Table(int numSeats, int tableNum, boolean availability) {
		this.availability = availability;
        this.tableNum = tableNum;
        this.numSeats = numSeats;
	}

	//get and set methods
	/**
	 * @return if table is available for reservation
	 */
	public boolean getAvailability() {
		return this.availability;
	}
	/**
	 * set availability as false
	 */
	public void occupied(){
		availability = false;
	}
	/**
	 * set availability as true
	 */
	public void vacant(){
		availability = true;
	}
	/**
	 * @return Maximum number of seats per table 
	 */
	public int getNumSeats() {
		return this.numSeats;
	}
	/**
	 * @param numSeats Maximum number of seats per table 
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	/**
	 * @return table number (integer)
	 */
	 public int getTableNum() {
		return this.tableNum;
	}

	/**
	 * 
	 * @param tableNum table number (integer)
	 */
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	

}
