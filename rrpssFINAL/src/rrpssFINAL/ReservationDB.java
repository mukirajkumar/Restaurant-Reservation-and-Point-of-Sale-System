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
import java.time.LocalDate;    
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter;  


public class ReservationDB
{  /**
	 * 
	 * File Location of reservations database
	 */
    private String R = "C:\\Restaurant Info\\reservation.csv";
    /**
	 * 
	 * ArrayList of Reservation Objects
	 */
    private ArrayList<Reservation> setReservationArrayList = new ArrayList<>();

    //constructors
    /**
	 * 
	 * Constructor that reads and converts the the database of reservations and passes into the Array List of Reservation objects
	 */
    public ReservationDB() {
		setReservationArrayList = convertSetData(readData(R));
	}
    /**
	 * 
	 * @return array list of reservations
	 */
    public ArrayList<Reservation> getReservationList() {
        return setReservationArrayList;
    }
    /**
	 * 
	 * @return true is file is empty 
	 * @return false if file is not empty
	 */
	public boolean checkFileEmpty(){
		File file = new File(R);
		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * updates reservation database when you remove reservations
	 * replaces the csv of reservations.
	 * @param updated array list of reservations
	 */
	public void replaceReservationList(ArrayList<Reservation> setReservationList) {
		try {
			File file = new File(R);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			for (int i=0; i<setReservationList.size(); i++) {
				bw.write(setReservationList.get(i).getName());
				bw.write(",");
				bw.write(Integer.toString(setReservationList.get(i).getTableNum()));
				bw.write(",");
				bw.write(Integer.toString(setReservationList.get(i).getContact()));
				bw.write(",");
				bw.write(Integer.toString(setReservationList.get(i).getPax()));
				bw.write(",");
				DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formattedLocalDate = setReservationList.get(i).getDate().format(formatterDate);
				bw.write(formattedLocalDate);
				bw.write(",");
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Previously was ("HH:mm:ss") changed to ("HH:mm")
				DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
				String formattedLocalTime = setReservationList.get(i).getTime().format(formatterTime);
				bw.write(formattedLocalTime);
				bw.write(",");
				bw.write(Boolean.toString(setReservationList.get(i).getMembershipStatus()));
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}
	/**
	 * updates reservation database when you create new reservations
	 * 
	 * @param array list of new reservations to be appended
	 */
    public void appendReservationList(ArrayList<Reservation> setReservationList) {
		try {
			File file = new File(R);
			boolean exists = file.exists();
			FileWriter fw = new FileWriter(file, exists /*=append*/);
			BufferedWriter bw = new BufferedWriter(fw);
			if (exists) {
				for (int i=0; i<setReservationList.size(); i++) {
					bw.write(setReservationList.get(i).getName());
					bw.write(",");
					bw.write(Integer.toString(setReservationList.get(i).getTableNum()));
					bw.write(",");
					bw.write(Integer.toString(setReservationList.get(i).getContact()));
					bw.write(",");
					bw.write(Integer.toString(setReservationList.get(i).getPax()));
					bw.write(",");
					DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String formattedLocalDate = setReservationList.get(i).getDate().format(formatterDate);
					bw.write(formattedLocalDate);
					bw.write(",");
					DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
					String formattedLocalTime = setReservationList.get(i).getTime().format(formatterTime);
					bw.write(formattedLocalTime);
					bw.write(",");
					bw.write(Boolean.toString(setReservationList.get(i).getMembershipStatus()));
					bw.write(System.getProperty("line.separator"));
				}
			}
			bw.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}
    /**
	 * read database of reservations
	 */
    public List<List<String>> readData(String file) { 
	    List<List<String>> templist = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(file)))  {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split(",");
		    	templist.add(Arrays.asList(values));
		    }
	    } catch (IOException e) { e.printStackTrace(); }
	    return templist;
	}
    /**
	 * 
	 * converts the Reservations DataBase from Strings to Reservation objects
	 * @return the array list of Reservation object
	 * @param list of strings: reservations
	 */
	public ArrayList<Reservation> convertSetData(List<List<String>> Reservations) {
		ArrayList<Reservation> setReservationArrayList = new ArrayList<>();
		for (int i=1; i<Reservations.size(); i++) {
			String setName = Reservations.get(i).get(0);
			int setTableNum = Integer.parseInt(Reservations.get(i).get(1));
			int setContact = Integer.parseInt(Reservations.get(i).get(2));
			int setPax = Integer.parseInt(Reservations.get(i).get(3));
			LocalDate setDate = LocalDate.parse(Reservations.get(i).get(4));  
			LocalTime setTime = LocalTime.parse(Reservations.get(i).get(5)); 
			boolean setMembershipStatus = Boolean.parseBoolean(Reservations.get(i).get(6));
			Reservation R = new Reservation(setName, setTableNum, setContact, setPax, setDate, setTime, setMembershipStatus);
			setReservationArrayList.add(R); 
		}
		return setReservationArrayList;
	}	
}
