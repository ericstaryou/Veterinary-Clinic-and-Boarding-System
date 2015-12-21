package server;

import java.util.*;
import java.io.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Appointment implements Serializable {

    private String ID;
    private Customer customer;
    private String petID;
    private Calendar appointmentTime;
    private String appointmentHour;
    private Vet vet;

    public Appointment() {

    }

    public Appointment(Customer customer, Vet vet, Calendar appointmentTime, String appointmentHour) {
        this.setAppointmentID();
        this.customer = customer;
        this.vet = vet;
        this.appointmentTime = appointmentTime;
        this.appointmentHour = appointmentHour;
    }

    private void setAppointmentID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Appointment ID
    private String generateID() {
        try {
            int ID = 0;

            File file = new File("AppointmentID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "AP" + String.format("%03d", ID);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ID;
    }

    //Save generated Appointment ID
    private void saveGeneratedID() {
        try {
            int ID = 0;
            File file = new File("AppointmentID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("AppointmentID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getID() {
        return this.ID;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public Calendar getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Calendar appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    //Search and return object
    public static Appointment searchObject(List<Appointment> list, String ID) {
        Appointment obj = null;

        for (Appointment y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<Appointment> list, String ID) {

        try {
            Appointment x = null;
            for (Appointment y : list) {
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
    public static void addObject(List<Appointment> list, Appointment y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<Appointment> list) {
        try {

            FileInputStream inStream = new FileInputStream("Appointment.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                Appointment temp = (Appointment) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<Appointment> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("Appointment.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (Appointment y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(String appointmentHour) {
        this.appointmentHour = appointmentHour;
    }
}
