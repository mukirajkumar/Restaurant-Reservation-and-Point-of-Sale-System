package rrpssFINAL;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Order {

	Staff staff = new Staff();
	TableManager tM = new TableManager();
	/**
	 * order ID 
	 */
	private int orderID;
	/**
	 * Staff ID 
	 */
	private int staffID;
	/**
	 * Order's table number
	 */
	private int tableNum;
	/**
	 * Invoice number
	 */
	private int invoiceNum;
	/**
	 * Total Price of all items and packages ordered 
	 */
	private float totalPrice;
	/**
	 * Total Price of all ala carte items ordered 
	 */
	private float totalAlaCartePrice;
	/**
	 * Total Price of all packages ordered 
	 */
	private float totalPackagePrice;
	/**
	 * Price of ala carte item
	 */
	private float alaCartePrice;
	/**
	 * price of set package
	 */
	private float setPackPrice;
	/**
	 * Date that customers dined (i.e reservation date)
	 */
	private String dineDate;
	/**
	 * Time that customers dined
	 */
	private String dineTime;
	
	MenuDB menu = new MenuDB();
	/**
	 * Array List of Set Packages that are added to the Order
	 */
	ArrayList<SetPackage> sArrayList = new ArrayList<>();
	/**
	 * Array List of Ala Carte items that are added to the Order
	 */
	ArrayList<MenuItems> iArrayList = new ArrayList<>();
	/**
	 * 
	 * Constructor Order
	 */
	public Order() {
	}
	/**
	 * 
	 * Constructor Order
	 * @param staffID staff ID number of staff that took the order
	 * @param tableNum table number
	 */
	//to create order
	public Order(int staffID, int tableNum){
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
		this.dineTime = LocalTime.now().format(formatterTime);
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.dineDate = LocalDate.now().format(formatterDate);
		invoiceNum = orderID;
		this.staffID = staffID;
		this.tableNum = tableNum;
		orderID++;
	}
	/**
	 * 
	 * Constructor Order so that we can append the orders and the prices to revenue database
	 * @param dineTime Time that customers dined
	 * @param dineDate Date that customers dined (i.e reservation date)
	 * @param tableNum table number
	 * @param totalAlaCartePrice Total Price of all ala carte items ordered 
	 * @param totalPackagePrice Total Price of all packages ordered 
	 * @param totalPrice Total Price of all items and packages ordered 
	 */
	//to append to revenue csv
	public Order(String dineTime, String dineDate, int tableNum, float totalAlaCarePrice, float totalPackagePrice, float totalPrice) {
		this.dineDate = dineDate;
		this.dineTime = dineTime;
		this.tableNum = tableNum;
		setAlaCarteTotalPrice(totalAlaCarePrice);
		setPackageTotalPrice(totalPackagePrice);
		setTotalPrice(totalPrice);
	}
	/**
	 * 
	 * @return dineDate
	 */
	public String getDineDate(){
		return this.dineDate;
	}
	/**
	 * 
	 * @param totalPrice Total Price of all items and packages ordered
	 */
	public void setTotalPrice(float totalPrice){
		this.totalPrice = totalPrice;
	}
	/**
	 * 
	 * @return Total Price of all items and packages ordered
	 */
	public float getTotalPrice(){
		return this.totalPrice;
	}
	/**
	 * 
	 * @param totalAlaCartePrice Total Price of all ala carte items ordered
	 */
	public void setAlaCarteTotalPrice(float totalAlaCarePrice){
		this.totalAlaCartePrice = totalAlaCarePrice;
	}
	/**
	 * 
	 * @return Total Price of all ala carte items ordered
	 */
	public float getAlaCarteTotalPrice(){
		return this.totalAlaCartePrice;
	}
	/**
	 * 
	 * @param totalPackagePrice Total Price of all packages ordered
	 */
	public void setPackageTotalPrice(float totalPackagePrice){
		this.totalPackagePrice = totalPackagePrice;
	}
	/**
	 * 
	 * @return Total Price of all packages ordered
	 */
	public float getPackageTotalPrice(){
		return this.totalPackagePrice;
	}
	/**
	 * 
	 * @return Time that customers dined
	 */
	public String getDineTime(){
		return this.dineTime;
	}
	/**
	 * 
	 * @return  Price of ala carte item
	 */
	public float getAlaCartePrice(){
		return this.alaCartePrice;	
	}
	/**
	 * 
	 * @return  Price of set package
	 */
	public float getSetPackagePrice(){
		return this.setPackPrice;
	}
	/**
	 * 
	 * Add Menu Item to the Array List of Menu items
	 * @param items menu item to be added into the order
	 */
	public void addItems(MenuItems items){
		iArrayList.add(items);
	}
	/**
	 * 
	 * Add Set Package to the Array List of Set Packages
	 * @param sPackage Set Package to be added into the order
	 */
	public void addSetItems(SetPackage sPackage){
		sArrayList.add(sPackage);
	}
	/**
	 * 
	 * remove alacarte items from array list of alacarte items
	 * @param items ala carte item to be removed
	 */
	public void removeItems(MenuItems items){
		iArrayList.remove(items);
	}
	/**
	 * 
	 * remove set package from array list of set packages
	 * @param sPackage package to be removed
	 */
	public void removeSetItems(SetPackage sPackage){
		sArrayList.remove(sPackage);
	}
	/**
	 * 
	 * @return Staff ID
	 */
	//
	public int getStaffID(){
		return this.staffID;
	}
	/**
	 * 
	 * @param staff Staff member whose ID needs to be set
	 */
	public void setStaffID(Staff staff) {
		staffID = staff.getStaffId();
	}
	/**
	 * 
	 * @return orderID
	 */
	public int getOrderID() {
		return this.orderID;
	}
	/**
	 * 
	 * @param orderID Order ID of order that is newly created and thus the ID needs to be set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
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
}