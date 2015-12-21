

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

public class Owner extends Human implements Serializable{
    
    public void setOwnerID(){
        this.ID = this.generateID();
        this.saveGeneratedID();
    }
    
    //Generate Staff ID
    @Override
    public String generateID(){
        try{
            int ID = 0;
            
            File file = new File("OwnerID.txt");
            Scanner inputFile = new Scanner(file);
            
            while(inputFile.hasNext()){
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();            
            
            ID++;
            
            this.ID = "OWN" + String.format("%03d", ID);
                                       
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return ID;
    }
    
    //Save generated Staff ID
    @Override
    public void saveGeneratedID(){
        try{
            int ID = 0;
            File file = new File("OwnerID.txt");
            Scanner inputFile = new Scanner(file);
            
            while(inputFile.hasNext()){
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();
            
            ID++;
            
            FileWriter fw = new FileWriter("OwnerID.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.print(ID);
            
            pw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    //Search and return object
    public static Owner searchObject(List<Owner> list, String ID){
        Owner obj = null;
       
        for(Owner y : list){
            if(y.getID().equalsIgnoreCase(ID))
                obj = y;
        }
        
        return obj;
    }
    
    //Remove the selected Object
    public static void removeObject(List<Owner> list, String ID){
        for(Owner y : list)
            if(y.getID().equalsIgnoreCase(ID))
                list.remove(y);   
    }
    
    //Append new object to the LinkedList
    public static void addObject(List<Owner> list, Owner y){
        list.add(y);
    }
    
    //Populate the LinkedList with the content of binary file
    public static void populateList(List<Owner> list){
         try{
            
            FileInputStream inStream = new FileInputStream("Owner.dat");
            ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
            
            while (true){
                             
                Owner temp = (Owner)objectInputFile.readObject();
                
                list.add(temp);
                                             
            }
                          
        }
        catch(EOFException e){
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Replace the whole binary file content with the LinkedList
    public static void overwriteBinaryFile(List<Owner> list){
        
        try{
            FileOutputStream outStream = new FileOutputStream("Owner.dat");
            ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
            
            for (Owner y : list)
            objectOutputFile.writeObject(y);
            
            objectOutputFile.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void createWeekRota(){
        WeekRota wr = new WeekRota();
        List<WeekRota> list = new LinkedList();
        WeekRota.populateList(list);
        WeekRota.addObject(list, wr);
        WeekRota.overwriteBinaryFile(list);
        
    }
    
    public void setRota(String RotaID, String day, Vet vet1, Vet vet2, Vet vet3) throws NullPointerException{
        Vet[] vet = {vet1, vet2, vet3};
        List<WeekRota> list = new LinkedList();
        WeekRota.populateList(list);
        WeekRota wr = WeekRota.searchObject(list, RotaID);
        
        switch(day){
            case "mon": wr.setMon(vet);
                break;
            case "tues": wr.setTues(vet);
                break;
            case "wed": wr.setWed(vet);
                break;
            case "thur": wr.setThur(vet);
                break;
            case "fri": wr.setFri(vet);
                break;
            case "sat": wr.setSat(vet);
                break;
            case "sun": wr.setSun(vet);
                break;
        }
        
        WeekRota.overwriteBinaryFile(list);
    }
    
    public void addVet(String areaOfExpertise1, String areaOfExpertise2, String firstName, String lastName, String password, String NRIC, String tel, String email, String address){
       List<Vet> list = new LinkedList();
        
       Vet vet = new Vet(areaOfExpertise1, areaOfExpertise2);
       vet.setVetID();
       vet.setFirstName(firstName);
       vet.setLastName(lastName);
       vet.setPassword(password);
       vet.setNRIC(NRIC);
       vet.setTel(tel);
       vet.setEmail(email);
       vet.setAddress(address);
       
       Vet.populateList(list);
       Vet.addObject(list, vet);
       Vet.overwriteBinaryFile(list); 
    }
    
    public void addReceptionist(String firstName, String lastName, String password, String NRIC, String tel, String email, String address){
       List<Receptionist> list = new LinkedList();
        
       Receptionist r = new Receptionist();
       r.setReceptionistID();
       r.setFirstName(firstName);
       r.setLastName(lastName);
       r.setPassword(password);
       r.setNRIC(NRIC);
       r.setTel(tel);
       r.setEmail(email);
       r.setAddress(address);
       
       Receptionist.populateList(list);
       Receptionist.addObject(list, r);
       Receptionist.overwriteBinaryFile(list); 
    }
    
    public void addBoardingStaff(String firstName, String lastName, String password, String NRIC, String tel, String email, String address){
       List<BoardingStaff> list = new LinkedList();
        
       BoardingStaff bs = new BoardingStaff();
       bs.setBoardingStaffID();
       bs.setFirstName(firstName);
       bs.setLastName(lastName);
       bs.setPassword(password);
       bs.setNRIC(NRIC);
       bs.setTel(tel);
       bs.setEmail(email);
       bs.setAddress(address);
       
       BoardingStaff.populateList(list);
       BoardingStaff.addObject(list, bs);
       BoardingStaff.overwriteBinaryFile(list); 
    }
    
    public void removeVet(String ID){
        List<Vet> list = new LinkedList();
        Vet.populateList(list);
        Vet.removeObject(list, ID);
        Vet.overwriteBinaryFile(list);
    }
    
    public void removeReceptionist(String ID){
        List<Receptionist> list = new LinkedList();
        Receptionist.populateList(list);
        Receptionist.removeObject(list, ID);
        Receptionist.overwriteBinaryFile(list);
    }
    
    public void removeBoardingStaff(String ID){
        List<BoardingStaff> list = new LinkedList();
        BoardingStaff.populateList(list);
        BoardingStaff.removeObject(list, ID);
        BoardingStaff.overwriteBinaryFile(list);
    }
}