package rrpssFINAL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RevenueDB  
{ 	/**
     * File location containing the Pacakages database (csv) stored in string type
     */
	private String revenueFile = "C:\\Restaurant Info\\revenue.csv";
	/**
     * Array List of order objects
     */
	private ArrayList<Order> revenueArrayList = new ArrayList<>();
	
	//constructors
	/**
     * Constructor: within constructor read and convert the database and pass it into the array-list.
     */
	public RevenueDB() {
		revenueArrayList = convertRevenueData(readData(revenueFile));
	}
	
	//get methods
	/**
     * @return revenue array list 
     */
	public ArrayList<Order> getrevenueList() {
        return revenueArrayList;
    }
	
	//update database
	/**
	 * updates revenue database when you create new orders
	 * 
	 * @param array list of new orders to be appended
	 */
	public void appendRevenueList(ArrayList<Order> revenueList) {
		try {
			File file = new File(revenueFile);
			boolean exists = file.exists();
			FileWriter fw = new FileWriter(file, exists /*=append*/);
			BufferedWriter bw = new BufferedWriter(fw);
			if (exists) {
				for (int i=0; i<revenueList.size(); i++) {
					bw.write(revenueList.get(i).getDineTime());
					bw.write(",");
					bw.write(revenueList.get(i).getDineDate());
					bw.write(",");
					bw.write(Integer.toString(revenueList.get(i).getTableNum()));
					bw.write(",");
					bw.write(Float.toString(revenueList.get(i).getAlaCarteTotalPrice()));
					bw.write(",");
					bw.write(Float.toString(revenueList.get(i).getPackageTotalPrice()));
					bw.write(",");
					bw.write(Float.toString(revenueList.get(i).getTotalPrice()));
					bw.write(System.getProperty("line.separator"));
				}
			}
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}
	
	
	//read and convert data
	/**
     * @return list of lists of strings containing the data the file read
     * @param revenueFile file location of file that needs to be read
     */
	public List<List<String>> readData(String revenueFile) { 
	    List<List<String>> templist = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(revenueFile)))  {
	    	String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        templist.add(Arrays.asList(values));
		    }
	    } catch (IOException e) { e.printStackTrace(); }
	    return templist;
	}
	/**
     * @param or list of lists of strings containing the revenue data to be converted
     * @return array list of orders
     */
	
	public ArrayList<Order> convertRevenueData(List<List<String>> or) {
		ArrayList<Order> orderRevenueArrayList = new ArrayList<>();
		for (int i=1; i<or.size(); i++) {
			String dineTime = or.get(i).get(0);
			String dineDate = or.get(i).get(1);   
			int tableNum = Integer.parseInt(or.get(i).get(2));
			float totalAlaCarePrice = Float.parseFloat(or.get(i).get(3));
			float totalPackagePrice = Float.parseFloat(or.get(i).get(4));
			float totalPrice = Float.parseFloat(or.get(i).get(5));

			Order o = new Order(dineTime, dineDate, tableNum, totalAlaCarePrice, totalPackagePrice, totalPrice);
			orderRevenueArrayList.add(o); 
		}
		return orderRevenueArrayList;
	}

}


