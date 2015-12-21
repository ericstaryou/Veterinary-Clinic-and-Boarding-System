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

public class Customer extends Human implements Serializable {

    private Pet[] pet = new Pet[10];
    private int numberOfPet = 1;

    public Customer() {

    }

    public Customer(String petType, String species, String petName) {

        if (petType.equalsIgnoreCase("Amphibian")) {
            this.pet[0] = new Amphibian(petType, species, petName);
         
        } else if (petType.equalsIgnoreCase("Bird")) {
            this.pet[0] = new Bird(petType, species, petName);
            
        } else if (petType.equalsIgnoreCase("Fish")) {
            this.pet[0] = new Fish(petType, species, petName);
            
        } else if (petType.equalsIgnoreCase("HouseholdPet")) {
            this.pet[0] = new HouseholdPet(petType, species, petName);
            
        } else if (petType.equalsIgnoreCase("Reptile")) {
            this.pet[0] = new Reptile(petType, species, petName);

        }
    }

    public void setCustomerID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    @Override
    //generate customer ID
    public String generateID() {
        try {
            int ID = 0;

            File file = new File("CustomerID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "C" + String.format("%03d", ID);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.ID;
    }

    @Override
    //Save generated Customer ID
    public void saveGeneratedID() {
        try {
            int ID = 0;
            File file = new File("CustomerID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("CustomerID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search and return object
    public static Customer searchObject(List<Customer> list, String ID) {
        Customer obj = null;

        for (Customer y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<Customer> list, String ID) {
        for (Customer y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                list.remove(y);
            }
        }
    }

    //Append new object to the LinkedList
    public static void addObject(List<Customer> list, Customer y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<Customer> list) {
        try {

            FileInputStream inStream = new FileInputStream("Customer.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                Customer temp = (Customer) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<Customer> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("Customer.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (Customer y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addNewPet(String petType, String species, String petName) {
        if (petType.equalsIgnoreCase("Amphibian")) {
            this.pet[numberOfPet] = new Amphibian(petType, species, petName);

        } else if (petType.equalsIgnoreCase("Bird")) {
            this.pet[numberOfPet] = new Bird(petType, species, petName);

        } else if (petType.equalsIgnoreCase("Fish")) {
            this.pet[numberOfPet] = new Fish(petType, species, petName);

        } else if (petType.equalsIgnoreCase("HouseholdPet")) {
            this.pet[numberOfPet] = new HouseholdPet(petType, species, petName);

        } else if (petType.equalsIgnoreCase("Reptile")) {
            this.pet[numberOfPet] = new Reptile(petType, species, petName);

        }

        numberOfPet++;
    }

    public int getNumberOfPet() {
        return numberOfPet;
    }

    public Pet getPet(int petNumber) {
        return pet[petNumber];
    }
}
