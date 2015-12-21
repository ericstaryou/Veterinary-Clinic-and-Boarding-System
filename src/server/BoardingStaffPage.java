package server;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BoardingStaffPage extends JFrame {

    private JLabel petID, petStatus;
    private JTextField tPetID;
    private JComboBox cbPetStatus;
    private JButton updateButton, fedButton;
    private JTable petTable;
    private DefaultTableModel model;
    private JScrollPane sp;

    public BoardingStaffPage() {
        super("Boarding Staff Page");
        setLayout(null);

        petID = new JLabel("Pet ID");
        petID.setLocation(20, 20);
        petID.setSize(petID.getPreferredSize());
        add(petID);

        tPetID = new JTextField();
        tPetID.setColumns(15);
        tPetID.setSize(tPetID.getPreferredSize());
        tPetID.setLocation(150, 20);
        tPetID.setEnabled(false);
        add(tPetID);

        fedButton = new JButton("Fed");
        fedButton.setSize(80, 23);
        fedButton.setLocation(300, 20);
        add(fedButton);

        petStatus = new JLabel("Pet Status");
        petStatus.setLocation(20, 60);
        petStatus.setSize(petStatus.getPreferredSize());
        add(petStatus);

        String[] status = {"healthy", "ill", "very ill", "critically ill"};
        cbPetStatus = new JComboBox(status);
        cbPetStatus.setLocation(150, 60);
        cbPetStatus.setSize(cbPetStatus.getPreferredSize());
        add(cbPetStatus);

        updateButton = new JButton("Update");
        updateButton.setSize(80, 23);
        updateButton.setLocation(150, 100);
        add(updateButton);

        petTable = new JTable();

        sp = new JScrollPane();
        sp.setViewportView(petTable);
        sp.setLocation(20, 150);
        sp.setSize(700, 100);
        sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp);

        java.util.List<Customer> list = new LinkedList();
        Customer.populateList(list);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String CN[] = {"PetID", "Pet Type", "Pet Name", "Pet Status", "Last Fed Time"};
        model = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy  hh:mm:ss");

        String petID = null;
        String petType = null;
        String petName = null;
        String petStatus = null;
        Calendar lastFedTime = null;

        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (Objects.equals(Boolean.TRUE, x.getPet(i).getBoarding())) {
                    petID = x.getPet(i).getID();
                    petType = x.getPet(i).getType();
                    petName = x.getPet(i).getName();
                    petStatus = x.getPet(i).getStatus();
                    lastFedTime = x.getPet(i).getLastFedTime();

                    String[] y = new String[5];

                    y[0] = petID;
                    y[1] = petType;
                    y[2] = petName;
                    y[3] = petStatus;
                    try {
                        y[4] = dateFormat.format(lastFedTime.getTime());
                    } catch (NullPointerException e) {

                    } finally {
                        model.addRow(y);
                    }
                }
            }
        }
        petTable.setModel(model);

        /**
         * Event Handler
         */
        fedButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tPetID.getText().equals("")) {
                                throw new NullFieldException("Please choose a pet from the table!");
                            }
                            BoardingStaff bs = new BoardingStaff();
                            bs.updatePetLastFedTime(tPetID.getText());
                            JOptionPane.showMessageDialog(null, "Last Fed Time is Updated");
                            updatePetTable();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        updateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tPetID.getText().equals("")) {
                                throw new NullFieldException("Please choose a pet from the table!");
                            }
                            if (cbPetStatus.getSelectedItem() == null) {
                                throw new NullFieldException("Please choose a status for the pet!");
                            }
                            BoardingStaff bs = new BoardingStaff();
                            bs.updatePetStatus(tPetID.getText(), (String) cbPetStatus.getSelectedItem());
                            JOptionPane.showMessageDialog(null, "Pet Status Updated");
                            updatePetTable();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        petTable.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = petTable.getSelectedRow();
                        tPetID.setText((String) petTable.getModel().getValueAt(row, 0));
                        cbPetStatus.setSelectedItem((String) petTable.getModel().getValueAt(row, 3));
                    }
                }
        );

    }

    public void updatePetTable() {
        java.util.List<Customer> list = new LinkedList();
        Customer.populateList(list);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String CN[] = {"PetID", "Pet Type", "Pet Name", "Pet Status", "Last Fed Time"};
        model = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy hh:mm:ss");

        String petID = null;
        String petType = null;
        String petName = null;
        String petStatus = null;
        Calendar lastFedTime = null;

        for (Customer x : list) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (Objects.equals(Boolean.TRUE, x.getPet(i).getBoarding())) {
                    petID = x.getPet(i).getID();
                    petType = x.getPet(i).getType();
                    petName = x.getPet(i).getName();
                    petStatus = x.getPet(i).getStatus();
                    lastFedTime = x.getPet(i).getLastFedTime();

                    String[] y = new String[5];

                    y[0] = petID;
                    y[1] = petType;
                    y[2] = petName;
                    y[3] = petStatus;
                    try {
                        y[4] = dateFormat.format(lastFedTime.getTime());
                    } catch (NullPointerException e) {

                    } finally {
                        model.addRow(y);
                    }
                }
            }
        }
        petTable.setModel(model);
    }
}
