package server;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class WeekRota implements Serializable {

    private String ID;
    private Calendar rotaDate;
    private Vet[] mon = new Vet[3];
    private Vet[] tues = new Vet[3];
    private Vet[] wed = new Vet[3];
    private Vet[] thur = new Vet[3];
    private Vet[] fri = new Vet[3];
    private Vet[] sat = new Vet[3];
    private Vet[] sun = new Vet[3];

    public WeekRota() {
        this.rotaDate = GregorianCalendar.getInstance();
        this.setWeekRotaID();
    }

    private void setWeekRotaID() {
        this.ID = this.generateID();
        this.saveGeneratedID();
    }

    //Generate Rota ID
    public String generateID() {
        try {
            int ID = 0;

            File file = new File("WeekRotaID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            this.ID = "WR" + String.format("%03d", ID);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ID;
    }

    //Save generated Rota ID
    public void saveGeneratedID() {
        try {
            int ID = 0;
            File file = new File("WeekRotaID.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNext()) {
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();

            ID++;

            FileWriter fw = new FileWriter("WeekRotaID.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.print(ID);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Calendar getRotaDate() {
        return rotaDate;
    }

    public void setRotaDate(Calendar rotaDate) {
        this.rotaDate = rotaDate;
    }

    public Vet[] getMon() {
        return mon;
    }

    public void setMon(Vet[] mon) {
        this.mon = mon;
    }

    public Vet[] getTues() {
        return tues;
    }

    public void setTues(Vet[] tues) {
        this.tues = tues;
    }

    public Vet[] getWed() {
        return wed;
    }

    public void setWed(Vet[] wed) {
        this.wed = wed;
    }

    public Vet[] getThur() {
        return thur;
    }

    public void setThur(Vet[] thur) {
        this.thur = thur;
    }

    public Vet[] getFri() {
        return fri;
    }

    public void setFri(Vet[] fri) {
        this.fri = fri;
    }

    public Vet[] getSat() {
        return sat;
    }

    public void setSat(Vet[] sat) {
        this.sat = sat;
    }

    public Vet[] getSun() {
        return sun;
    }

    public void setSun(Vet[] sun) {
        this.sun = sun;
    }

    //Search and return object
    public static WeekRota searchObject(List<WeekRota> list, String ID) {
        WeekRota obj = null;

        for (WeekRota y : list) {
            if (y.getID().equalsIgnoreCase(ID)) {
                obj = y;
            }
        }

        return obj;
    }

    //Remove the selected Object
    public static void removeObject(List<WeekRota> list, String ID) {
        try {
            WeekRota x = null;
            for (WeekRota y : list) {
                if (y.getID().equalsIgnoreCase(ID)) {
                    x = y;
                }
            }
            list.remove(x);
        } catch (ConcurrentModificationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Append new object to the LinkedList
    public static void addObject(List<WeekRota> list, WeekRota y) {
        list.add(y);
    }

    //Populate the LinkedList with the content of binary file
    public static void populateList(List<WeekRota> list) {
        try {

            FileInputStream inStream = new FileInputStream("WeekRota.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

            while (true) {

                WeekRota temp = (WeekRota) objectInputFile.readObject();

                list.add(temp);

            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<WeekRota> list) {

        try {
            FileOutputStream outStream = new FileOutputStream("WeekRota.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

            for (WeekRota y : list) {
                objectOutputFile.writeObject(y);
            }

            objectOutputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
