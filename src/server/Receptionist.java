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
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Receptionist extends Human implements Serializable {

    public void setReceptionistID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Staff ID
    @Override
    public String generateID() {
        try {
            int ID = 0;

            File file = new File("ReceptionistID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "REC" + String.format("%03d", ID);

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
            File file = new File("ReceptionistID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("ReceptionistID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search and return object
    public static Receptionist searchObject(List<Receptionist> list, String ID) {
        Receptionist obj = null;

        for (Receptionist y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<Receptionist> list, String ID) {
        try {
            Receptionist x = null;
            for (Receptionist y : list) {
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
    public static void addObject(List<Receptionist> list, Receptionist y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<Receptionist> list) {
        try {

            FileInputStream inStream = new FileInputStream("Receptionist.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                Receptionist temp = (Receptionist) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<Receptionist> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("Receptionist.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (Receptionist y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //to add new pet for existing customer
    public void createPetProfile(String ID, String petType, String species, String petName) {
        List<Customer> list = new LinkedList();
        Customer.populateList(list);
        Customer x = Customer.searchObject(list, ID);
        x.addNewPet(petType, species, petName);
        Customer.overwriteBinaryFile(list);
    }

    //create cust profile + pet
    public void createCustomerProfile(String petType, String species, String petName, String firstName, String lastName, String NRIC, String tel, String email, String address) {
        List<Customer> list = new LinkedList();

        Customer c = new Customer(petType, species, petName);
        c.setCustomerID();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setNRIC(NRIC);
        c.setTel(tel);
        c.setEmail(email);
        c.setAddress(address);

        Customer.populateList(list);

        Customer.addObject(list, c);

        Customer.overwriteBinaryFile(list);

    }

    public void editCustomerProfile(String ID, String firstName, String lastName, String NRIC, String tel, String email, String address) {

        List<Customer> list = new LinkedList();
        Customer.populateList(list);
        Customer c = Customer.searchObject(list, ID);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setNRIC(NRIC);
        c.setTel(tel);
        c.setEmail(email);
        c.setAddress(address);

        Customer.overwriteBinaryFile(list);
    }

    public void editPetProfile(String custID, String petID, String petSpecies, String petName) {

        List<Customer> list = new LinkedList();
        Customer.populateList(list);
        Customer c = Customer.searchObject(list, custID);

        for (int i = 0; i < c.getNumberOfPet(); i++) {
            if (c.getPet(i).getID().equalsIgnoreCase(petID)) {
                c.getPet(i).setSpecies(petSpecies);
                c.getPet(i).setName(petName);
            }
        }

        Customer.overwriteBinaryFile(list);
    }

    public void makeAppointment(String petID, Customer customer, Vet vet, Calendar appointmentTime, String appointmentHour) {
        List<Appointment> list = new LinkedList();

        Appointment appointment = new Appointment(customer, vet, appointmentTime, appointmentHour);
        appointment.setPetID(petID);

        Appointment.populateList(list);
        Appointment.addObject(list, appointment);
        Appointment.overwriteBinaryFile(list);
    }

    public void editAppointment(String ID, Calendar date, String hour, Vet vet) {
        List<Appointment> list = new LinkedList();
        Appointment.populateList(list);

        Appointment app = Appointment.searchObject(list, ID);
        app.setAppointmentTime(date);
        app.setAppointmentHour(hour);
        app.setVet(vet);

        Appointment.overwriteBinaryFile(list);
    }

    public void cancelAppointment(String ID) {
        List<Appointment> list = new LinkedList();

        Appointment.populateList(list);
        Appointment.removeObject(list, ID);
        Appointment.overwriteBinaryFile(list);
    }

    public void bookBoardingService(String petID, Customer customer, Calendar boardingTime, int boardingDurationInDays) {

        List<BoardingBookings> list = new LinkedList();
        BoardingBookings.populateList(list);

        BoardingBookings bb = new BoardingBookings(customer, boardingTime, boardingDurationInDays);
        bb.setPetID(petID);

        BoardingBookings.addObject(list, bb);
        BoardingBookings.overwriteBinaryFile(list);

        List<Customer> cList = new LinkedList();
        Customer.populateList(cList);
        for (Customer x : cList) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equals(petID)) {
                    x.getPet(i).setBoarding(true);
                }
            }
        }
        Customer.overwriteBinaryFile(cList);

    }

    public void editBoardingService(String BoardingBookingID, Calendar boardingTime, int boardingDurationInDays) {
        List<BoardingBookings> list = new LinkedList();
        BoardingBookings.populateList(list);

        BoardingBookings bb = BoardingBookings.searchObject(list, BoardingBookingID);
        bb.setBoardingTime(boardingTime);
        bb.setBoardingDurationInDays(boardingDurationInDays);

        BoardingBookings.overwriteBinaryFile(list);
    }

    public void cancelBoardingService(String petID, String BoardingBookingID) {
        List<BoardingBookings> list = new LinkedList();
        BoardingBookings.populateList(list);
        BoardingBookings.removeObject(list, BoardingBookingID);
        BoardingBookings.overwriteBinaryFile(list);

        Customer c = new Customer();
        List<Customer> cList = new LinkedList();
        Customer.populateList(cList);
        for (Customer x : cList) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getID().equals(petID)) {
                    x.getPet(i).setBoarding(false);
                }
            }
        }
        Customer.overwriteBinaryFile(cList);
    }
}
