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

public class AppointmentPage extends JFrame {

    //Tab 1
    private JTabbedPane tab;
    private JPanel pane1, pane2, pane3, pane4;
    private JLabel petID, vetID, appointmentDate, appointmentTime, weekRota, existingAppointment, vetDetails;
    private JComboBox tPetID, tVetID, appointmentHour;
    private JButton addButton;
    private JDateChooser dateChooser;
    private JTable appointmentTable, rotaTable, vetTable;
    private JScrollPane sp1, sp2, sp3;
    private DefaultTableModel model, model1, model2;
    private String customerID;

    //Tab 2
    private JTable appointmentTable2;
    private JScrollPane sp4;
    private JLabel appointmentID, appointmentDate2, vetID2, appointmentTime2;
    private JTextField tAppointmentID;
    private JDateChooser dateChooser2;
    private JButton editButton, deleteButton;
    private JComboBox tVetID2, tAppointmentTime2;

    //Tab 3
    private JTable boardingBookingTable;
    private DefaultTableModel model3;
    private JScrollPane sp5;
    private JLabel petID3, boardingBookingDate, boardingBookingDuration;
    private JComboBox cbPetID, cbDuration;
    private JDateChooser dateChooser3;
    private JButton bookButton;

    //Tab 4
    private JTable boardingBookingTable2;
    private JScrollPane sp6;
    private JLabel boardingBookingID, boardingBookingDate2, boardingBookingDuration2;
    private JTextField tBoardingBookingID, tBoardingBookingDate2;
    private JComboBox cbDuration2;
    private JDateChooser dateChooser4;
    private JButton editBB, cancelBB;

    public AppointmentPage(String customerID) {

        super("Appointment and BoardingBooking Page");
        this.customerID = customerID;

        tab = new JTabbedPane();

        /**
         * First Tab
         */
        pane1 = new JPanel();
        pane1.setLayout(null);

        petID = new JLabel("Pet ID");
        petID.setLocation(20, 20);
        petID.setSize(petID.getPreferredSize());
        pane1.add(petID);

        java.util.List<Customer> cList = new LinkedList();
        Customer.populateList(cList);
        tPetID = new JComboBox();
        for (Customer x : cList) {
            if (x.getID().equalsIgnoreCase(customerID)) {
                for (int i = 0; i < x.getNumberOfPet(); i++) {
                    tPetID.addItem(x.getPet(i).getID());
                }
            }
        }
        tPetID.setSize(tPetID.getPreferredSize());
        tPetID.setLocation(150, 20);
        pane1.add(tPetID);

        vetID = new JLabel("Vet ID");
        vetID.setLocation(20, 100);
        vetID.setSize(vetID.getPreferredSize());
        pane1.add(vetID);

        java.util.List<Vet> vList = new LinkedList();
        Vet.populateList(vList);
        tVetID = new JComboBox();
        for (Vet x : vList) {
            tVetID.addItem(x.getID());
        }
        tVetID.setSize(tVetID.getPreferredSize());
        tVetID.setLocation(150, 100);
        pane1.add(tVetID);

        appointmentDate = new JLabel("Appointment Date");
        appointmentDate.setLocation(20, 60);
        appointmentDate.setSize(appointmentDate.getPreferredSize());
        pane1.add(appointmentDate);

        dateChooser = new JDateChooser();
        dateChooser.setSize(dateChooser.getPreferredSize());
        dateChooser.setLocation(150, 60);
        dateChooser.setBackground(new java.awt.Color(51, 255, 255));
        dateChooser.setDateFormatString("EEE dd/MM/yyyy");
        dateChooser.setMinSelectableDate(new java.util.Date(-62135794735000L));
        pane1.add(dateChooser);

        appointmentTime = new JLabel("Apppointment Time");
        appointmentTime.setLocation(20, 140);
        appointmentTime.setSize(appointmentTime.getPreferredSize());
        pane1.add(appointmentTime);

        String[] hour = {"0700", "0800", "0900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100", "2200"};
        appointmentHour = new JComboBox(hour);
        appointmentHour.setSize(appointmentHour.getPreferredSize());
        appointmentHour.setLocation(150, 140);
        pane1.add(appointmentHour);

        addButton = new JButton("Add");
        addButton.setSize(80, 23);
        addButton.setLocation(150, 180);
        pane1.add(addButton);

        JLabel lRota = new JLabel("This Week's Vet Working Rota :");
        lRota.setSize(lRota.getPreferredSize());
        lRota.setLocation(20, 240);
        pane1.add(lRota);

        rotaTable = new JTable();

        sp1 = new JScrollPane();
        sp1.setViewportView(rotaTable);
        sp1.setLocation(20, 260);
        sp1.setSize(500, 80);
        sp1.setVerticalScrollBarPolicy(sp1.VERTICAL_SCROLLBAR_ALWAYS);
        pane1.add(sp1);

        java.util.List<WeekRota> rotaList = new LinkedList();
        WeekRota.populateList(rotaList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String columnNames[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        model = new DefaultTableModel(new String[0][0], columnNames);

        WeekRota wr = rotaList.get(rotaList.size() - 1);
        String[] o = new String[21];
        try {
            for (int i = 0; i < 3; i++) {
                o[0] = wr.getMon()[i].getID();
                o[1] = wr.getTues()[i].getID();
                o[2] = wr.getWed()[i].getID();
                o[3] = wr.getThur()[i].getID();
                o[4] = wr.getFri()[i].getID();
                o[5] = wr.getSat()[i].getID();
                o[6] = wr.getSun()[i].getID();

                model.addRow(o);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "The newest rota has not been completed yet!");
        }
        rotaTable.setModel(model);

        JLabel app = new JLabel("Existing Appointments :");
        app.setSize(app.getPreferredSize());
        app.setLocation(280, 20);
        pane1.add(app);

        appointmentTable = new JTable();

        sp2 = new JScrollPane();
        sp2.setViewportView(appointmentTable);
        sp2.setLocation(280, 40);
        sp2.setSize(600, 100);
        sp2.setVerticalScrollBarPolicy(sp2.VERTICAL_SCROLLBAR_ALWAYS);
        pane1.add(sp2);

        java.util.List<Appointment> appList = new LinkedList();
        Appointment.populateList(appList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String cn[] = {"AppointmentID", "AppointmentDate", "AppointmentHour", "PetID", "VetID", "VetName"};
        model1 = new DefaultTableModel(new String[0][0], cn);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : appList) {
            String[] y = new String[6];
            y[0] = x.getID();
            try {
                y[1] = dateFormat.format(x.getAppointmentTime().getTime());
            } catch (NullPointerException e) {

            } finally {
                y[2] = x.getAppointmentHour();
                y[3] = x.getPetID();
                y[4] = x.getVet().getID();
                y[5] = x.getVet().getFirstName() + " " + x.getVet().getLastName();
                model1.addRow(y);
            }
        }
        appointmentTable.setModel(model1);

        JLabel vt = new JLabel("Vet Details :");
        vt.setSize(vt.getPreferredSize());
        vt.setLocation(550, 240);
        pane1.add(vt);

        vetTable = new JTable();

        sp3 = new JScrollPane();
        sp3.setViewportView(vetTable);
        sp3.setLocation(550, 260);
        sp3.setSize(500, 80);
        sp3.setVerticalScrollBarPolicy(sp3.VERTICAL_SCROLLBAR_ALWAYS);
        pane1.add(sp3);

        java.util.List<Vet> vetList = new LinkedList();
        Vet.populateList(vetList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String cN[] = {"VetID", "VetName", "Expertise1", "Expertise2"};
        model2 = new DefaultTableModel(new String[0][0], cN);

        for (Vet x : vetList) {
            String[] y = new String[4];
            y[0] = x.getID();
            y[1] = x.getFirstName() + " " + x.getLastName();
            y[2] = x.getAreaOfExpertise()[0];
            y[3] = x.getAreaOfExpertise()[1];
            model2.addRow(y);
        }
        vetTable.setModel(model2);

        /**
         * Second Tab
         */
        pane2 = new JPanel();
        pane2.setLayout(null);

        appointmentID = new JLabel("AppointmentID");
        appointmentID.setLocation(20, 20);
        appointmentID.setSize(appointmentID.getPreferredSize());
        pane2.add(appointmentID);

        tAppointmentID = new JTextField();
        tAppointmentID.setColumns(15);
        tAppointmentID.setSize(tAppointmentID.getPreferredSize());
        tAppointmentID.setLocation(150, 20);
        tAppointmentID.setEnabled(false);
        pane2.add(tAppointmentID);

        appointmentDate2 = new JLabel("Appointment Date");
        appointmentDate2.setLocation(20, 60);
        appointmentDate2.setSize(appointmentDate2.getPreferredSize());
        pane2.add(appointmentDate2);

        dateChooser2 = new JDateChooser();
        dateChooser2.setSize(dateChooser2.getPreferredSize());
        dateChooser2.setLocation(150, 60);
        dateChooser2.setBackground(new java.awt.Color(51, 255, 255));
        dateChooser2.setDateFormatString("EEE dd/MM/yyyy");
        dateChooser2.setMinSelectableDate(new java.util.Date(-62135794735000L));
        pane2.add(dateChooser2);

        vetID2 = new JLabel("Vet ID");
        vetID2.setLocation(20, 100);
        vetID2.setSize(vetID2.getPreferredSize());
        pane2.add(vetID2);

        tVetID2 = new JComboBox();
        for (Vet x : vList) {
            tVetID2.addItem(x.getID());
        }
        tVetID2.setSize(tVetID2.getPreferredSize());
        tVetID2.setLocation(150, 100);
        pane2.add(tVetID2);

        appointmentTime2 = new JLabel("Apppointment Time");
        appointmentTime2.setLocation(20, 140);
        appointmentTime2.setSize(appointmentTime2.getPreferredSize());
        pane2.add(appointmentTime2);

        tAppointmentTime2 = new JComboBox(hour);
        tAppointmentTime2.setSize(tAppointmentTime2.getPreferredSize());
        tAppointmentTime2.setLocation(150, 140);
        pane2.add(tAppointmentTime2);

        editButton = new JButton("Edit");
        editButton.setSize(80, 23);
        editButton.setLocation(150, 180);
        pane2.add(editButton);

        deleteButton = new JButton("Cancel");
        deleteButton.setSize(80, 23);
        deleteButton.setLocation(250, 180);
        pane2.add(deleteButton);

        appointmentTable2 = new JTable();

        sp4 = new JScrollPane();
        sp4.setViewportView(appointmentTable2);
        sp4.setLocation(300, 20);
        sp4.setSize(600, 100);
        sp4.setVerticalScrollBarPolicy(sp4.VERTICAL_SCROLLBAR_ALWAYS);
        pane2.add(sp4);
        updateAppointmentTable2();

        /**
         * Tab 3
         */
        pane3 = new JPanel();
        pane3.setLayout(null);

        petID3 = new JLabel("Pet ID");
        petID3.setLocation(20, 20);
        petID3.setSize(petID3.getPreferredSize());
        pane3.add(petID3);

        cbPetID = new JComboBox();
        for (Customer x : cList) {
            if (x.getID().equalsIgnoreCase(customerID)) {
                for (int i = 0; i < x.getNumberOfPet(); i++) {
                    cbPetID.addItem(x.getPet(i).getID());
                }
            }
        }
        cbPetID.setSize(cbPetID.getPreferredSize());
        cbPetID.setLocation(150, 20);
        pane3.add(cbPetID);

        boardingBookingDate = new JLabel("Boarding Date");
        boardingBookingDate.setLocation(20, 60);
        boardingBookingDate.setSize(boardingBookingDate.getPreferredSize());
        pane3.add(boardingBookingDate);

        dateChooser3 = new JDateChooser();
        dateChooser3.setSize(dateChooser3.getPreferredSize());
        dateChooser3.setLocation(150, 60);
        dateChooser3.setBackground(new java.awt.Color(51, 255, 255));
        dateChooser3.setDateFormatString("EEE dd/MM/yyyy");
        dateChooser3.setMinSelectableDate(new java.util.Date(-62135794735000L));
        pane3.add(dateChooser3);

        boardingBookingDuration = new JLabel("Boarding Duration(days)");
        boardingBookingDuration.setLocation(20, 100);
        boardingBookingDuration.setSize(boardingBookingDuration.getPreferredSize());
        pane3.add(boardingBookingDuration);

        String[] duration = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        cbDuration = new JComboBox(duration);
        cbDuration.setSize(cbDuration.getPreferredSize());
        cbDuration.setLocation(150, 100);
        pane3.add(cbDuration);

        bookButton = new JButton("Book");
        bookButton.setSize(80, 23);
        bookButton.setLocation(150, 140);
        pane3.add(bookButton);

        boardingBookingTable = new JTable();

        sp5 = new JScrollPane();
        sp5.setViewportView(boardingBookingTable);
        sp5.setLocation(250, 20);
        sp5.setSize(500, 100);
        sp5.setVerticalScrollBarPolicy(sp5.VERTICAL_SCROLLBAR_ALWAYS);
        pane3.add(sp5);

        java.util.List<BoardingBookings> bbList = new LinkedList();
        BoardingBookings.populateList(bbList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String CN[] = {"BoardingBookingID", "PetID", "Boarding Date", "Boarding Duration(days)"};
        model3 = new DefaultTableModel(new String[0][0], CN);

        for (BoardingBookings x : bbList) {
            String[] y = new String[4];
            y[0] = x.getID();
            y[1] = x.getPetID();
            y[2] = dateFormat.format(x.getBoardingTime().getTime());
            y[3] = Integer.toString(x.getBoardingDurationInDays());
            model3.addRow(y);
        }
        boardingBookingTable.setModel(model3);

        /**
         * Tab 4
         */
        pane4 = new JPanel();
        pane4.setLayout(null);

        boardingBookingID = new JLabel("BoardingBookingID");
        boardingBookingID.setLocation(20, 20);
        boardingBookingID.setSize(boardingBookingID.getPreferredSize());
        pane4.add(boardingBookingID);

        tBoardingBookingID = new JTextField();
        tBoardingBookingID.setColumns(15);
        tBoardingBookingID.setSize(tBoardingBookingID.getPreferredSize());
        tBoardingBookingID.setLocation(150, 20);
        tBoardingBookingID.setEnabled(false);
        pane4.add(tBoardingBookingID);

        boardingBookingDate2 = new JLabel("Boarding Date");
        boardingBookingDate2.setLocation(20, 60);
        boardingBookingDate2.setSize(boardingBookingDate2.getPreferredSize());
        pane4.add(boardingBookingDate2);

        dateChooser4 = new JDateChooser();
        dateChooser4.setSize(dateChooser4.getPreferredSize());
        dateChooser4.setLocation(150, 60);
        dateChooser4.setBackground(new java.awt.Color(51, 255, 255));
        dateChooser4.setDateFormatString("EEE dd/MM/yyyy");
        dateChooser4.setMinSelectableDate(new java.util.Date(-62135794735000L));
        pane4.add(dateChooser4);

        boardingBookingDuration2 = new JLabel("Boarding Duration(days)");
        boardingBookingDuration2.setLocation(20, 100);
        boardingBookingDuration2.setSize(boardingBookingDuration2.getPreferredSize());
        pane4.add(boardingBookingDuration2);

        cbDuration2 = new JComboBox(duration);
        cbDuration2.setSize(cbDuration2.getPreferredSize());
        cbDuration2.setLocation(150, 100);
        pane4.add(cbDuration2);

        editBB = new JButton("Edit");
        editBB.setSize(80, 23);
        editBB.setLocation(150, 140);
        pane4.add(editBB);

        cancelBB = new JButton("Cancel");
        cancelBB.setSize(80, 23);
        cancelBB.setLocation(250, 140);
        pane4.add(cancelBB);

        boardingBookingTable2 = new JTable();

        sp6 = new JScrollPane();
        sp6.setViewportView(boardingBookingTable2);
        sp6.setLocation(350, 20);
        sp6.setSize(500, 100);
        sp6.setVerticalScrollBarPolicy(sp6.VERTICAL_SCROLLBAR_ALWAYS);
        pane4.add(sp6);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        model3 = new DefaultTableModel(new String[0][0], CN);

        for (BoardingBookings x : bbList) {
            String[] y = new String[4];
            y[0] = x.getID();
            y[1] = x.getPetID();
            y[2] = dateFormat.format(x.getBoardingTime().getTime());
            y[3] = Integer.toString(x.getBoardingDurationInDays());
            model3.addRow(y);
        }
        boardingBookingTable2.setModel(model3);

        tab.addTab("Make Appointment", pane1);
        tab.addTab("Book Boarding Service", pane3);
        tab.addTab("Edit Appointment", pane2);
        tab.addTab("Edit Boarding Service", pane4);
        add(tab);

        /**
         * Event Handlers
         */
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (dateChooser.getCalendar() == null) {
                                throw new NullFieldException("Please choose a date!");
                            }

                            java.util.List<Customer> list = new LinkedList();
                            Customer.populateList(list);

                            Customer cust = null;
                            Vet vet = null;

                            for (Customer x : list) {
                                if (x.getID().equalsIgnoreCase(customerID)) {
                                    cust = x;
                                }
                            }

                            for (Vet x : vList) {
                                if (((String) tVetID.getSelectedItem()).equals(x.getID())) {
                                    vet = x;
                                }
                            }

                            Receptionist r = new Receptionist();
                            r.makeAppointment((String) tPetID.getSelectedItem(), cust, vet, dateChooser.getCalendar(), (String) appointmentHour.getSelectedItem());
                            JOptionPane.showMessageDialog(null, "New Appointment is Made");
                            updateAppointmentTable();
                            updateAppointmentTable2();

                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        editButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tAppointmentID.getText().equals("")) {
                                throw new NullFieldException("Please choose an appointment from the table");
                            }
                            if (dateChooser2.getCalendar() == null) {
                                throw new NullFieldException("Please choose a date");
                            }
                            Vet vet = null;

                            for (Vet x : vList) {
                                if (((String) tVetID2.getSelectedItem()).equals(x.getID())) {
                                    vet = x;
                                }
                            }

                            Receptionist r = new Receptionist();
                            r.editAppointment(tAppointmentID.getText(), dateChooser2.getCalendar(), (String) tAppointmentTime2.getSelectedItem(), vet);
                            JOptionPane.showMessageDialog(null, "Appointment Edited");
                            updateAppointmentTable();
                            updateAppointmentTable2();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        deleteButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tAppointmentID.getText().equals("")) {
                                throw new NullFieldException("Please choose an appointment from the table");
                            }
                            Receptionist r = new Receptionist();
                            r.cancelAppointment(tAppointmentID.getText());
                            JOptionPane.showMessageDialog(null, "Appointment Cancelled");
                            updateAppointmentTable();
                            updateAppointmentTable2();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        appointmentTable2.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = appointmentTable2.getSelectedRow();
                        tAppointmentID.setText((String) appointmentTable2.getModel().getValueAt(row, 0));
                        tVetID2.setSelectedItem((String) appointmentTable2.getModel().getValueAt(row, 4));
                        tAppointmentTime2.setSelectedItem((String) appointmentTable2.getModel().getValueAt(row, 2));
                    }
                }
        );

        bookButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (dateChooser3.getCalendar() == null) {
                                throw new NullFieldException("Please choose a date!");
                            }
                            Customer c = new Customer();
                            java.util.List<Customer> list = new LinkedList();
                            Customer.populateList(list);
                            Customer cust = null;

                            for (Customer x : list) {
                                if (x.getID().equalsIgnoreCase(customerID)) {
                                    cust = x;
                                }
                            }

                            Receptionist r = new Receptionist();
                            r.bookBoardingService((String) cbPetID.getSelectedItem(), cust, dateChooser3.getCalendar(), Integer.parseInt((String) cbDuration.getSelectedItem()));
                            JOptionPane.showMessageDialog(null, "Boarding Service is Booked");
                            updateBBTable();
                            updateBBTable2();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        boardingBookingTable2.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = boardingBookingTable2.getSelectedRow();
                        tBoardingBookingID.setText((String) boardingBookingTable2.getModel().getValueAt(row, 0));
                        cbDuration2.setSelectedItem((String) boardingBookingTable2.getModel().getValueAt(row, 3));
                    }
                }
        );

        editBB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tBoardingBookingID.getText().equals("")) {
                                throw new NullFieldException("Please choose a boarding booking from the table");
                            }
                            if (dateChooser4.getCalendar() == null) {
                                throw new NullFieldException("Please choose a date!");
                            }
                            Receptionist r = new Receptionist();
                            r.editBoardingService(tBoardingBookingID.getText(), dateChooser4.getCalendar(), Integer.parseInt((String) cbDuration2.getSelectedItem()));
                            JOptionPane.showMessageDialog(null, "Boarding Booking Edited");
                            updateBBTable();
                            updateBBTable2();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        cancelBB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tBoardingBookingID.getText().equals("")) {
                                throw new NullFieldException("Please choose a boarding booking!");
                            }
                            int row = boardingBookingTable2.getSelectedRow();
                            Receptionist r = new Receptionist();
                            r.cancelBoardingService((String) boardingBookingTable2.getModel().getValueAt(row, 1), tBoardingBookingID.getText());
                            JOptionPane.showMessageDialog(null, "Boarding Booking Cancelled");
                            updateBBTable();
                            updateBBTable2();
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        //validate appointment time
        appointmentHour.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent event) {
                        try {
                            if (dateChooser.getCalendar() == null) {
                                throw new NullFieldException("Please enter the appointment date first!");
                            }
                            DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
                            String days = dateFormat.format(dateChooser.getCalendar().getTime());

                            java.util.List<Appointment> aList = new LinkedList();
                            Appointment.populateList(aList);

                            for (Appointment a : aList) {

                                if (a.getVet().getID().equalsIgnoreCase(((String) tVetID.getSelectedItem()))) {
                                    if ((dateFormat.format(a.getAppointmentTime().getTime())).equalsIgnoreCase(days)) {
                                        if (a.getAppointmentHour().equalsIgnoreCase((String) appointmentHour.getSelectedItem())) {
                                            throw new NullFieldException("This time has been booked by other customer! Please choose another time.");
                                        }
                                    }
                                }
                            }

                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );
    }

    public void updateAppointmentTable() {
        java.util.List<Appointment> appList = new LinkedList();
        Appointment.populateList(appList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String cn[] = {"AppointmentID", "AppointmentDate", "AppointmentHour", "PetID", "VetID", "VetName"};
        model1 = new DefaultTableModel(new String[0][0], cn);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : appList) {
            String[] y = new String[6];
            y[0] = x.getID();
            try {
                y[1] = dateFormat.format(x.getAppointmentTime().getTime());
            } catch (NullPointerException e) {

            } finally {
                y[2] = x.getAppointmentHour();
                y[3] = x.getPetID();
                y[4] = x.getVet().getID();
                y[5] = x.getVet().getFirstName() + " " + x.getVet().getLastName();
                model1.addRow(y);
            }
        }
        appointmentTable.setModel(model1);
    }

    public void updateAppointmentTable2() {
        java.util.List<Appointment> appList = new LinkedList();
        Appointment.populateList(appList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String cn[] = {"AppointmentID", "AppointmentDate", "AppointmentHour", "PetID", "VetID", "VetName"};
        model1 = new DefaultTableModel(new String[0][0], cn);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : appList) {
            String[] y = new String[6];
            y[0] = x.getID();
            try {
                y[1] = dateFormat.format(x.getAppointmentTime().getTime());
            } catch (NullPointerException e) {

            } finally {
                y[2] = x.getAppointmentHour();
                y[3] = x.getPetID();
                y[4] = x.getVet().getID();
                y[5] = x.getVet().getFirstName() + " " + x.getVet().getLastName();
                model1.addRow(y);
            }
        }
        appointmentTable2.setModel(model1);
    }

    public void updateBBTable() {
        java.util.List<BoardingBookings> bbList = new LinkedList();
        BoardingBookings.populateList(bbList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String CN[] = {"BoardingBookingID", "PetID", "Boarding Date", "Boarding Duration"};
        model3 = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (BoardingBookings x : bbList) {
            String[] y = new String[4];
            y[0] = x.getID();
            y[1] = x.getPetID();
            y[2] = dateFormat.format(x.getBoardingTime().getTime());
            y[3] = Integer.toString(x.getBoardingDurationInDays());
            model3.addRow(y);
        }
        boardingBookingTable.setModel(model3);
    }

    public void updateBBTable2() {
        java.util.List<BoardingBookings> bbList = new LinkedList();
        BoardingBookings.populateList(bbList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String CN[] = {"BoardingBookingID", "PetID", "Boarding Date", "Boarding Duration"};
        model3 = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (BoardingBookings x : bbList) {
            String[] y = new String[4];
            y[0] = x.getID();
            y[1] = x.getPetID();
            y[2] = dateFormat.format(x.getBoardingTime().getTime());
            y[3] = Integer.toString(x.getBoardingDurationInDays());
            model3.addRow(y);
        }
        boardingBookingTable2.setModel(model3);
    }
}
