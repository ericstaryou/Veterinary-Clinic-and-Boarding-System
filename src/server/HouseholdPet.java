

package server;
import java.io.*;
import java.util.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HouseholdPet extends Pet implements Serializable{
    
    public HouseholdPet(String type, String species, String name){
        super(type, species, name);
    }
    
}
