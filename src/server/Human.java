

package server;
import java.io.*;
import java.util.*;

public abstract class Human implements Serializable{
    protected String ID;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String NRIC;
    protected String tel;
    protected String email;
    protected String address;
    
    public Human(){
        
    }
    
    //Generate Staff ID
    public abstract String generateID();
        
    //Save generated Staff ID
    public abstract void saveGeneratedID();
    
    //Create a binary file
    public static void createFile(String fileName){
        try{
            FileOutputStream outStream = new FileOutputStream(fileName + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(outStream);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getID() {
        return ID;
    }
    
    public void setID(String ID){
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
   
}
