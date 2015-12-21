package server;

import java.util.*;
import java.io.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class BoardingBookings implements Serializable {

    private String ID;
    private Customer customer;
    private String petID;
    private Calendar boardingTime;
    private int boardingDurationInDays;

    public BoardingBookings() {

    }

    public BoardingBookings(Customer customer, Calendar boardingTime, int boardingDurationInDays) {
        this.setBoardingBookingsID();
        this.customer = customer;
        this.boardingTime = boardingTime;
        this.boardingDurationInDays = boardingDurationInDays;
    }

    private void setBoardingBookingsID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Boarding BookingsID
    private String generateID() {
        try {
            int ID = 0;

            File file = new File("BoardingBookingsID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "BB" + String.format("%03d", ID);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return getID();
    }

    //Save generated Boarding Bookings ID
    private void saveGeneratedID() {
        try {
            int ID = 0;
            File file = new File("BoardingBookingsID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("BoardingBookingsID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search and return object
    public static BoardingBookings searchObject(List<BoardingBookings> list, String ID) {
        BoardingBookings obj = null;

        for (BoardingBookings y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<BoardingBookings> list, String ID) {
        try {
            Iterator it = list.iterator();
            BoardingBookings y = null;
            while (it.hasNext()) {
                BoardingBookings x = (BoardingBookings) it.next();
                if (x.getID().equalsIgnoreCase(ID)) {
                    y = x;
                    break;
                }
            }
            
            list.remove(y);
        } catch (ConcurrentModificationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Append new object to the LinkedList
    public static void addObject(List<BoardingBookings> list, BoardingBookings y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<BoardingBookings> list) {
        try {

            FileInputStream inStream = new FileInputStream("BoardingBookings.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                BoardingBookings temp = (BoardingBookings) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<BoardingBookings> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("BoardingBookings.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (BoardingBookings y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            
        }
    }

    public String getID() {
        return ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public Calendar getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(Calendar boardingTime) {
        this.boardingTime = boardingTime;
    }

    public int getBoardingDurationInDays() {
        return boardingDurationInDays;
    }

    public void setBoardingDurationInDays(int boardingDurationInDays) {
        this.boardingDurationInDays = boardingDurationInDays;
    }

}
