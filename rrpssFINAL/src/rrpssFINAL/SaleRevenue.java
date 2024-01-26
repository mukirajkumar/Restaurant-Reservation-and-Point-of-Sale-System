package rrpssFINAL;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.format.DateTimeParseException;

public class SaleRevenue {
	OrderManager oApp = new OrderManager();
	Scanner sc = new Scanner(System.in);
	
	RevenueDB revenue = new RevenueDB();
	/**
	 * Array List of orders form the revenue database
	 */
	ArrayList<Order> orderRevenueArrayList = revenue.getrevenueList();
	/**
	 * daily revenue from ala-carte items
	 */
	private float dailyAlaRevenue;
	/**
	 * daily revenue from set packages
	 */
	private float dailySetRevenue;
	/**
	 * daily revenue from all orders
	 */
	private float dailyTotalRevenue;
	/**
	 * monthly revenue from ala-carte items
	 */
	private float monthlyAlaRevenue;
	/**
	 * monthly revenue from set packages
	 */
	private float monthlySetRevenue;
	/**
	 * daily revenue from all orders
	 */
	private float monthlyTotalRevenue;
	/**
	 * calculate daily revenue 
	 * breakdown of ala carte revenue and set package revenue included 
	 */
	public float calculateDailyRevenue() throws DateTimeParseException {
		
		//exception handling
		String datechoice;
		DateTimeFormatter formatter;
		LocalDate date;
		
        while (true) {
        try {
        	System.out.println("\nEnter date (enter as yyyy-MM-dd): ");
            datechoice = sc.next();
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(datechoice, formatter);
            break;
        } catch (DateTimeParseException e) {
        	System.out.println("Invalid input format! Please re-enter.");
        	continue;}
        }
        
        System.out.println();
    
		for (Order or : orderRevenueArrayList) {
			LocalDate dateofOrder = LocalDate.parse(or.getDineDate(), formatter);

			if (dateofOrder.compareTo(date) == 0) {

				dailyAlaRevenue = dailyAlaRevenue + or.getAlaCarteTotalPrice();
				dailySetRevenue = dailySetRevenue + or.getPackageTotalPrice();
			}
		}
		dailyTotalRevenue = dailyAlaRevenue + dailySetRevenue;
		System.out.println("Date: " + datechoice);
		System.out.println("AlaCarte Revenue: "+ dailyAlaRevenue);
		System.out.println("Set revenue: " + dailySetRevenue);
		System.out.println("Total revenue: " + dailyTotalRevenue);
		System.out.println();
		return dailyTotalRevenue;
		
	}
	/**
	 * calculate monthly revenue 
	 * breakdown of ala carte revenue and set package revenue included 
	 */
	public float calculateMonthlyRevenue() {	
		
		System.out.println("Enter Year(yyyy): ");
		Scanner sc = new Scanner(System.in);
		int yearchoice = sc.nextInt();
		System.out.println("Enter Month(MM): ");
		int monthchoice = sc.nextInt();
		System.out.println();
		
		for (Order or : orderRevenueArrayList) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateofOrder = LocalDate.parse(or.getDineDate(), formatter);
			if ((dateofOrder.getMonthValue() == monthchoice) && dateofOrder.getYear() == yearchoice) {
				monthlyAlaRevenue = monthlyAlaRevenue + or.getAlaCarteTotalPrice();
				monthlySetRevenue = monthlySetRevenue + or.getPackageTotalPrice();
			}
		}
		monthlyTotalRevenue = monthlyAlaRevenue + monthlySetRevenue;
		
		System.out.println("Month: " + monthchoice);
		System.out.println("AlaCarte Revenue: "+ monthlyAlaRevenue);
		System.out.println("Set revenue: " + monthlySetRevenue);
		System.out.println("Total revenue: " + monthlyTotalRevenue);
		System.out.println();
		return monthlyTotalRevenue;
	}
	
}
