package rrpssFINAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class TableDB {
	/**
	 * 
	 * File location containing table database  
	 */
	private String tableFile = "C:\\Restaurant Info\\table.csv";
	/**
	 * 
	 * List of Lists of Strings of tables  
	 */
	List<List<String>> tableList = new ArrayList<>();
	/**
	 * 
	 * ArrayList of table objects  
	 */
	ArrayList<Table> tableArrayList = new ArrayList<>();
	
	//constructors
	/**
     * Constructor: within constructor read and convert the database and pass it into the array-list.
     */
	public TableDB() {
		//tableList = readTableInfo();
		tableArrayList = convertSetData(readData());
	}
	
	//get and set methods
	/**
	 * 
	 * @return ArrayList of table objects  
	 */
	public ArrayList<Table> getTableList() {
        return tableArrayList;
    }
	/**
	 * 
	 * read Table database
	 * @return List of Lists of Strings of tables 
	 */
	public List<List<String>> readData() {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(tableFile));) {
			while((line = br.readLine()) != null){
				String[] temp = line.split(",");
				this.tableList.add(Arrays.asList(temp));
			}
		} catch (IOException e) { e.printStackTrace(); }
	
	return this.tableList;
	}
	/**
	 * 
	 * converts the Table DataBase from Strings to Table objects
	 * @return the array list of Table object
	 * @param tableList list of strings: tables
	 */
	public ArrayList<Table> convertSetData(List<List<String>> tableList) {
		for (int i=0; i<tableList.size(); i++) {
			int numSeats = Integer.parseInt(tableList.get(i).get(0));
			int tableNum = Integer.parseInt(tableList.get(i).get(1));
			boolean availability = Boolean.parseBoolean(tableList.get(i).get(2));
			Table tableInfo = new Table(numSeats, tableNum, availability);
			this.tableArrayList.add(tableInfo); 
		}
		return this.tableArrayList;
	}	
	/**
	 * 
	 * update any Table changes made so that all edits are reflected in the Table database.
	 * @param tableList updated array list of Table objects  
	 */
	public void updateTableList(ArrayList<Table> tableList) {
	
		try {
			File file = new File(tableFile);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i=0; i<tableList.size(); i++) {
				bw.write(Integer.toString(tableList.get(i).getNumSeats()));
				bw.write(",");
				bw.write(Integer.toString(tableList.get(i).getTableNum()));
				bw.write(",");
				bw.write(Boolean.toString(tableList.get(i).getAvailability()));
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}

}
