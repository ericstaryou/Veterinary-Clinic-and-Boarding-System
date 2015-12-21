package server;

import java.io.*;
import java.util.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Fish extends Pet implements Serializable {

    public Fish(String type, String species, String name) {
        super(type, species, name);
    }

}
