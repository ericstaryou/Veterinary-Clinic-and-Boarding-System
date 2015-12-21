package server;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;

public class BoardingStaff extends Human implements Serializable {

    public void setBoardingStaffID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Staff ID
    @Override
    public String generateID() {
        try {
            int ID = 0;

            File file = new File("BoardingStaffID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "BS" + String.format("%03d", ID);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ID;
    }

    //Save generated Staff ID
    @Override
    public void saveGeneratedID() {
        try {
            int ID = 0;
            File file = new File("BoardingStaffID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("BoardingStaffID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search and return object
    public static BoardingStaff searchObject(List<BoardingStaff> list, String ID) {
        BoardingStaff obj = null;

        for (BoardingStaff y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<BoardingStaff> list, String ID) {
        try {
            BoardingStaff x = null;
            for (BoardingStaff y : list) {
                if (y.getID().equalsIgnoreCase(ID)) {
                    x = y;
                    break;
                }
            }
            list.remove(x);
        } catch (ConcurrentModificationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Append new object to the LinkedList
    public static void addObject(List<BoardingStaff> list, BoardingStaff y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<BoardingStaff> list) {
        try {

            FileInputStream inStream = new FileInputStream("BoardingStaff.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                BoardingStaff temp = (BoardingStaff) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<BoardingStaff> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("BoardingStaff.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (BoardingStaff y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePetStatus(String petID, String status) {
        List<Customer> list = new LinkedList();
        Customer.populateList(list);
        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equalsIgnoreCase(petID)) {
                    x.getPet(i).setStatus(status);
                }
            }
        }
        Customer.overwriteBinaryFile(list);
    }

    public void updatePetLastFedTime(String petID) {
        List<Customer> list = new LinkedList();
        Customer.populateList(list);
        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equalsIgnoreCase(petID)) {
                    x.getPet(i).setLastFedTime();
                }
            }
        }
        Customer.overwriteBinaryFile(list);
    }
}
