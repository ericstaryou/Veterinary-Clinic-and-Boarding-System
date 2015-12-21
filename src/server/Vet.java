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

public class Vet extends Human implements Serializable {

    private String[] areaOfExpertise = new String[2];

    public Vet() {

    }

    public Vet(String areaOfExpertise1, String areaOfExpertise2) {

        this.areaOfExpertise[0] = areaOfExpertise1;
        this.areaOfExpertise[1] = areaOfExpertise2;

    }

    public void setVetID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Staff ID
    @Override
    public String generateID() {
        try {
            int ID = 0;

            File file = new File("VetID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "VET" + String.format("%03d", ID);

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
            File file = new File("VetID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("VetID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search and return object
    public static Vet searchObject(List<Vet> list, String ID) {
        Vet obj = null;

        for (Vet y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<Vet> list, String ID) {
        try {
            Vet x = null;
            for (Vet y : list) {
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
    public static void addObject(List<Vet> list, Vet y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<Vet> list) {
        try {

            FileInputStream inStream = new FileInputStream("Vet.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                Vet temp = (Vet) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<Vet> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("Vet.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (Vet y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterDiagnosisAndPrognosis(String petID, String Prognosis, String Diagnosis) {
        List<Customer> list = new LinkedList();
        Customer.populateList(list);

        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equalsIgnoreCase(petID)) {
                    x.getPet(i).setPrognosis(Prognosis);
                    x.getPet(i).setDiagnosis(Diagnosis);
                    x.getPet(i).setSeen(Boolean.TRUE);
                }
            }
        }
        Customer.overwriteBinaryFile(list);
    }

    public void inputCharges(String petID, double charges) {
        List<Customer> list = new LinkedList();
        Customer.populateList(list);

        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equalsIgnoreCase(petID)) {
                    x.getPet(i).setCharges(charges);
                }
            }
        }
        Customer.overwriteBinaryFile(list);
    }

    public void viewAppointments() {
        List<Appointment> list = new LinkedList();
        Appointment.populateList(list);
        for (Appointment x : list) {

        }
    }

    public String[] getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise1, String areaOfExpertise2) {
        this.areaOfExpertise[0] = areaOfExpertise1;
        this.areaOfExpertise[1] = areaOfExpertise2;
    }

}
