

package server;
import java.io.*;
import java.util.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class Pet implements Serializable{
    protected String ID;
    protected String type;
    protected String species;
    protected String name;
    protected String status;
    protected Boolean boarding;
    protected Calendar boardingDate;
    protected Calendar lastFedTime;
    protected double charges;
    protected String diagnosis;
    protected String prognosis;
    protected Boolean seen;
    
    public Pet(String type, String species, String name){
        this.setPetID();
        this.type = type;
        this.species = species;
        this.name = name;
    }
    
    public void setPetID(){
        this.ID = this.generateID();
        this.saveGeneratedID();
    }
    
    //Generate Pet ID
    public String generateID(){
        try{
            int ID = 0;
            
            File file = new File("PetID.txt");
            Scanner inputFile = new Scanner(file);
            
            while(inputFile.hasNext()){
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();            
            
            ID++;
            
            this.ID = "P" + String.format("%03d", ID);
                                       
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return this.ID;
    }
    
    //Save generated Pet ID
    public void saveGeneratedID(){
        try{
            int ID = 0;
            File file = new File("PetID.txt");
            Scanner inputFile = new Scanner(file);
            
            while(inputFile.hasNext()){
                ID = Integer.parseInt(inputFile.nextLine());
            }
            inputFile.close();
            
            ID++;
            
            FileWriter fw = new FileWriter("PetID.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.print(ID);
            
            pw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getBoarding() {
        return boarding;
    }

    public void setBoarding(Boolean boarding) {
        this.boarding = boarding;
    }

    public Calendar getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate() {
        this.boardingDate = GregorianCalendar.getInstance();
    }

    public Calendar getLastFedTime() {
        return lastFedTime;
    }

    public void setLastFedTime() {
        this.lastFedTime = GregorianCalendar.getInstance();
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
    
}
