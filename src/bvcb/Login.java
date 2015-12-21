package bvcb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import server.*;

public class Login extends JFrame {

    private HashMap hashTable;
    private JRadioButton rRep, rBoard, rVet, rOwner;
    private ButtonGroup bg;
    private JLabel staffID, password;
    private JTextField tStaffID;
    private JPasswordField ps;
    private JButton loginButton;

    public Login() {
        super("Login Page");
        setLayout(null);

        rRep = new JRadioButton("Receptionist", true);
        rRep.setLocation(20, 10);
        rRep.setSize(rRep.getPreferredSize());
        add(rRep);

        rBoard = new JRadioButton("Boarding Staff", false);
        rBoard.setLocation(120, 10);
        rBoard.setSize(rBoard.getPreferredSize());
        add(rBoard);

        rVet = new JRadioButton("Vet", false);
        rVet.setLocation(230, 10);
        rVet.setSize(rVet.getPreferredSize());
        add(rVet);

        rOwner = new JRadioButton("Owner", false);
        rOwner.setLocation(300, 10);
        rOwner.setSize(rOwner.getPreferredSize());
        add(rOwner);

        bg = new ButtonGroup();
        bg.add(rRep);
        bg.add(rBoard);
        bg.add(rVet);
        bg.add(rOwner);

        staffID = new JLabel("Staff ID");
        staffID.setLocation(60, 50);
        staffID.setSize(staffID.getPreferredSize());
        add(staffID);

        tStaffID = new JTextField();
        tStaffID.setColumns(15);
        tStaffID.setSize(tStaffID.getPreferredSize());
        tStaffID.setLocation(150, 50);
        add(tStaffID);

        password = new JLabel("Password");
        password.setLocation(60, 90);
        password.setSize(password.getPreferredSize());
        add(password);

        ps = new JPasswordField();
        ps.setLocation(150, 90);
        ps.setColumns(15);
        ps.setSize(ps.getPreferredSize());
        add(ps);

        loginButton = new JButton("Login");
        loginButton.setSize(80, 23);
        loginButton.setLocation(150, 130);
        add(loginButton);

        loginButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tStaffID.getText().equals("") || ps.getPassword().equals("")) {
                                throw new NullFieldException("Please Fill in all the Field!");
                            }

                            hashTable = new LinkedHashMap();
                            String key = tStaffID.getText();;
                            char[] password = ps.getPassword();

                            if (rRep.isSelected()) {

                                java.util.List<Receptionist> list = new LinkedList();
                                Receptionist.populateList(list);
                                Receptionist r = null;

                                for (Receptionist x : list) {
                                    hashTable.put(x.getID(), x);
                                }

                                r = (Receptionist) hashTable.get(key);

                                try {
                                    if (Arrays.equals(password, r.getPassword().toCharArray())) {
                                        JOptionPane.showMessageDialog(null, "Login Sucessful! Welcome " + r.getFirstName() + " " + r.getLastName());

                                        ReceptionistPage rp = new ReceptionistPage();
                                        rp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        rp.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                        rp.setVisible(true);

                                        setVisible(false);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NullPointerException e) {
                                    JOptionPane.showMessageDialog(null, "Wrong StaffID!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                }

                            } else if (rBoard.isSelected()) {

                                java.util.List<BoardingStaff> list = new LinkedList();
                                BoardingStaff.populateList(list);
                                BoardingStaff b = null;

                                for (BoardingStaff x : list) {
                                    hashTable.put(x.getID(), x);
                                }

                                b = (BoardingStaff) hashTable.get(key);

                                try {
                                    if (Arrays.equals(password, b.getPassword().toCharArray())) {
                                        JOptionPane.showMessageDialog(null, "Login Sucessful! Welcome " + b.getFirstName() + " " + b.getLastName());

                                        BoardingStaffPage bp = new BoardingStaffPage();
                                        bp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        bp.setSize(750, 450);
                                        bp.setVisible(true);

                                        setVisible(false);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NullPointerException e) {
                                    JOptionPane.showMessageDialog(null, "Wrong StaffID!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                }

                            } else if (rVet.isSelected()) {

                                java.util.List<Vet> list = new LinkedList();
                                Vet.populateList(list);
                                Vet v = null;

                                for (Vet x : list) {
                                    hashTable.put(x.getID(), x);
                                }

                                v = (Vet) hashTable.get(key);

                                try {
                                    if (Arrays.equals(password, v.getPassword().toCharArray())) {
                                        JOptionPane.showMessageDialog(null, "Login Sucessful! Welcome " + v.getFirstName() + " " + v.getLastName());

                                        VetPage rp = new VetPage(v.getID());
                                        rp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        rp.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                        rp.setVisible(true);

                                        setVisible(false);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NullPointerException e) {
                                    JOptionPane.showMessageDialog(null, "Wrong StaffID!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                }

                            } else if (rOwner.isSelected()) {

                                java.util.List<Owner> list = new LinkedList();
                                Owner.populateList(list);
                                Owner o = null;

                                for (Owner x : list) {
                                    hashTable.put(x.getID(), x);
                                }

                                o = (Owner) hashTable.get(key);

                                try {
                                    if (Arrays.equals(password, o.getPassword().toCharArray())) {
                                        JOptionPane.showMessageDialog(null, "Login Sucessful! Welcome " + o.getFirstName() + " " + o.getLastName());

                                        OwnerPage op = new OwnerPage();
                                        op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        op.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                        op.setVisible(true);

                                        setVisible(false);

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong Password!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NullPointerException e) {
                                    JOptionPane.showMessageDialog(null, "Wrong StaffID!", "Login Unsuccesfull", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );
    }
}
