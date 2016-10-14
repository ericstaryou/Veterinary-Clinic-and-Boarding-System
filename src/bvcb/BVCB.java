package bvcb;

import server.*;
import java.util.*;
import java.text.*;
import javax.swing.JFrame;
import javax.swing.UIManager.*;
import javax.swing.*;

public class BVCB {

    public static void main(String[] args) { 
       
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());

                    Login lp = new Login();
                    lp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    lp.setSize(395, 220);
                    lp.setVisible(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());//null
        }
    }
}
