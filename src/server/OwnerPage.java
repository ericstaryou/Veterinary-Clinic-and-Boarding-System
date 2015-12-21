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
import java.io.FileOutputStream;

public class OwnerPage extends JFrame {

    private JTabbedPane tab1;
    private JPanel panel1, panel2, panel3;

    //panel1
    private JLabel position, ID, firstName, lastName, password, NRIC, tel, email, address, areaOfExpertise;
    private JTextField tID, tFirstName, tLastName, tPassword, tNRIC, tTel, tEmail, tAddress;
    private JRadioButton rReceptionist, rBoardingStaff, rVet;
    private ButtonGroup bgPosition;
    private JComboBox expertise, expertise2;
    private JButton addButton, removeButton;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    //panel2
    private JPopupMenu popupMenu;
    private JLabel workingRota, mon, tues, wed, thur, fri, sat, sun;
    private JComboBox[] tMon, tTues, tWed, tThur, tFri, tSat, tSun;
    private JButton createWR, monB, tuesB, wedB, thurB, friB, satB, sunB, refreshB;
    private JTable vetTable, rotaTable;
    private DefaultTableModel model2, model3;
    private JScrollPane scrollPane2, scrollPane3;
    private JTextField tRota;

    //panel3
    private JTable vetReport, petReport;
    private DefaultTableModel vetModel, petModel;
    private JScrollPane vetSP, petSP;
    private JLabel vTable, pTable, totalIncome, amount;

    public OwnerPage() {
        super("Owner Page");

        /**
         * Tab 1
         */
        tab1 = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(null);

        position = new JLabel("Position :");
        position.setLocation(20, 13);
        position.setSize(position.getPreferredSize());
        panel1.add(position);

        rReceptionist = new JRadioButton("Receptionist", true);
        rReceptionist.setLocation(80, 10);
        rReceptionist.setSize(rReceptionist.getPreferredSize());
        panel1.add(rReceptionist);

        rBoardingStaff = new JRadioButton("Boarding Staff", false);
        rBoardingStaff.setLocation(180, 10);
        rBoardingStaff.setSize(rBoardingStaff.getPreferredSize());
        panel1.add(rBoardingStaff);

        rVet = new JRadioButton("Vet", false);
        rVet.setLocation(290, 10);
        rVet.setSize(rVet.getPreferredSize());
        panel1.add(rVet);

        bgPosition = new ButtonGroup();
        bgPosition.add(rReceptionist);
        bgPosition.add(rBoardingStaff);
        bgPosition.add(rVet);

        ID = new JLabel("ID");
        ID.setLocation(20, 80);
        ID.setSize(ID.getPreferredSize());
        panel1.add(ID);

        tID = new JTextField();
        tID.setColumns(15);
        tID.setSize(tID.getPreferredSize());
        tID.setLocation(150, 80);
        tID.setEnabled(false);
        panel1.add(tID);

        firstName = new JLabel("First Name");
        firstName.setLocation(20, 120);
        firstName.setSize(firstName.getPreferredSize());
        panel1.add(firstName);

        tFirstName = new JTextField();
        tFirstName.setColumns(15);
        tFirstName.setSize(tFirstName.getPreferredSize());
        tFirstName.setLocation(150, 120);
        tFirstName.setToolTipText("Enter your first name");
        panel1.add(tFirstName);

        lastName = new JLabel("Last Name");
        lastName.setLocation(20, 160);
        lastName.setSize(lastName.getPreferredSize());
        panel1.add(lastName);

        tLastName = new JTextField();
        tLastName.setColumns(15);
        tLastName.setSize(tLastName.getPreferredSize());
        tLastName.setLocation(150, 160);
        tLastName.setToolTipText("Enter your last name");
        panel1.add(tLastName);

        password = new JLabel("Password");
        password.setLocation(20, 200);
        password.setSize(password.getPreferredSize());
        panel1.add(password);

        tPassword = new JTextField();
        tPassword.setColumns(15);
        tPassword.setSize(tPassword.getPreferredSize());
        tPassword.setLocation(150, 200);
        panel1.add(tPassword);

        NRIC = new JLabel("NRIC");
        NRIC.setLocation(20, 240);
        NRIC.setSize(NRIC.getPreferredSize());
        panel1.add(NRIC);

        tNRIC = new JTextField();
        tNRIC.setColumns(15);
        tNRIC.setSize(tNRIC.getPreferredSize());
        tNRIC.setLocation(150, 240);
        tNRIC.setToolTipText("e.g. 940328086126");
        panel1.add(tNRIC);

        tel = new JLabel("Tel. No");
        tel.setLocation(20, 280);
        tel.setSize(tel.getPreferredSize());
        panel1.add(tel);

        tTel = new JTextField();
        tTel.setColumns(15);
        tTel.setSize(tTel.getPreferredSize());
        tTel.setLocation(150, 280);
        tTel.setToolTipText("e.g. 0169532287");
        panel1.add(tTel);

        email = new JLabel("E-mail Address");
        email.setLocation(20, 320);
        email.setSize(email.getPreferredSize());
        panel1.add(email);

        tEmail = new JTextField();
        tEmail.setColumns(15);
        tEmail.setSize(tEmail.getPreferredSize());
        tEmail.setLocation(150, 320);
        tEmail.setToolTipText("e.g. eric.you@hotmail.com");
        panel1.add(tEmail);

        address = new JLabel("Home Address");
        address.setLocation(20, 360);
        address.setSize(address.getPreferredSize());
        panel1.add(address);

        tAddress = new JTextField();
        tAddress.setColumns(15);
        tAddress.setSize(tAddress.getPreferredSize());
        tAddress.setLocation(150, 360);
        tAddress.setToolTipText("e.g. eric.you@hotmail.com");
        panel1.add(tAddress);

        areaOfExpertise = new JLabel("Area of Expertise");
        areaOfExpertise.setLocation(20, 400);
        areaOfExpertise.setSize(areaOfExpertise.getPreferredSize());
        panel1.add(areaOfExpertise);

        String[] expertiseList = {"Amphibian", "Bird", "Fish", "Household Pet", "Reptile"};
        expertise = new JComboBox(expertiseList);
        expertise.setSize(expertise.getPreferredSize());
        expertise.setLocation(150, 400);
        expertise.setEnabled(false);
        panel1.add(expertise);

        expertise2 = new JComboBox(expertiseList);
        expertise2.setSize(expertise2.getPreferredSize());
        expertise2.setLocation(280, 400);
        expertise2.setEnabled(false);
        panel1.add(expertise2);

        addButton = new JButton("Add");
        addButton.setSize(80, 26);
        addButton.setLocation(130, 440);
        panel1.add(addButton);

        removeButton = new JButton("Remove");
        removeButton.setSize(80, 26);
        removeButton.setLocation(230, 440);
        panel1.add(removeButton);

        table = new JTable(model);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setLocation(400, 10);
        scrollPane.setSize(900, 400);
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(scrollPane);

        /**
         * Tab 2
         */
        panel2 = new JPanel();
        panel2.setLayout(null);

        java.util.List<Vet> list = new LinkedList();
        java.util.List<String> vID = new LinkedList();
        Vet.populateList(list);

        for (Vet x : list) {
            vID.add(x.getID());
        }

        String stuff[] = vID.toArray(new String[vID.size()]);

        workingRota = new JLabel("Working Rota");
        workingRota.setLocation(20, 10);
        workingRota.setSize(workingRota.getPreferredSize());
        panel2.add(workingRota);

        monB = new JButton("Set");
        monB.setSize(80, 24);
        monB.setLocation(320, 50);
        monB.addActionListener(new vetTextHandler());
        panel2.add(monB);

        mon = new JLabel("Monday");
        mon.setLocation(20, 50);
        mon.setSize(mon.getPreferredSize());
        panel2.add(mon);

        tMon = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tMon[i] = new JComboBox(stuff);
            tMon[i].setSize(tMon[i].getPreferredSize());
        }

        tMon[0].setLocation(120, 50);
        panel2.add(tMon[0]);

        tMon[1].setLocation(180, 50);
        panel2.add(tMon[1]);

        tMon[2].setLocation(240, 50);
        panel2.add(tMon[2]);

        tuesB = new JButton("Set");
        tuesB.setSize(80, 24);
        tuesB.setLocation(320, 90);
        tuesB.addActionListener(new vetTextHandler());
        panel2.add(tuesB);

        tues = new JLabel("Tuesday");
        tues.setLocation(20, 90);
        tues.setSize(tues.getPreferredSize());
        panel2.add(tues);

        tTues = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tTues[i] = new JComboBox(stuff);
            tTues[i].setSize(tTues[i].getPreferredSize());
        }

        tTues[0].setLocation(120, 90);
        panel2.add(tTues[0]);

        tTues[1].setLocation(180, 90);
        panel2.add(tTues[1]);

        tTues[2].setLocation(240, 90);
        panel2.add(tTues[2]);

        wedB = new JButton("Set");
        wedB.setSize(80, 24);
        wedB.setLocation(320, 130);
        wedB.addActionListener(new vetTextHandler());
        panel2.add(wedB);

        wed = new JLabel("Wednesday");
        wed.setLocation(20, 130);
        wed.setSize(wed.getPreferredSize());
        panel2.add(wed);

        tWed = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tWed[i] = new JComboBox(stuff);
            tWed[i].setSize(tWed[i].getPreferredSize());
        }

        tWed[0].setLocation(120, 130);
        panel2.add(tWed[0]);

        tWed[1].setLocation(180, 130);
        panel2.add(tWed[1]);

        tWed[2].setLocation(240, 130);
        panel2.add(tWed[2]);

        thurB = new JButton("Set");
        thurB.setSize(80, 24);
        thurB.setLocation(320, 170);
        thurB.addActionListener(new vetTextHandler());
        panel2.add(thurB);

        thur = new JLabel("Thursday");
        thur.setLocation(20, 170);
        thur.setSize(thur.getPreferredSize());
        panel2.add(thur);

        tThur = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tThur[i] = new JComboBox(stuff);
            tThur[i].setSize(tThur[i].getPreferredSize());
        }

        tThur[0].setLocation(120, 170);
        panel2.add(tThur[0]);

        tThur[1].setLocation(180, 170);
        panel2.add(tThur[1]);

        tThur[2].setLocation(240, 170);
        panel2.add(tThur[2]);

        friB = new JButton("Set");
        friB.setSize(80, 24);
        friB.setLocation(320, 210);
        friB.addActionListener(new vetTextHandler());
        panel2.add(friB);

        fri = new JLabel("Friday");
        fri.setLocation(20, 210);
        fri.setSize(fri.getPreferredSize());
        panel2.add(fri);

        tFri = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tFri[i] = new JComboBox(stuff);
            tFri[i].setSize(tFri[i].getPreferredSize());
        }

        tFri[0].setLocation(120, 210);
        panel2.add(tFri[0]);

        tFri[1].setLocation(180, 210);
        panel2.add(tFri[1]);

        tFri[2].setLocation(240, 210);
        panel2.add(tFri[2]);

        satB = new JButton("Set");
        satB.setSize(80, 24);
        satB.setLocation(320, 250);
        satB.addActionListener(new vetTextHandler());
        panel2.add(satB);

        sat = new JLabel("Saturday");
        sat.setLocation(20, 250);
        sat.setSize(sat.getPreferredSize());
        panel2.add(sat);

        tSat = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tSat[i] = new JComboBox(stuff);
            tSat[i].setSize(tSat[i].getPreferredSize());
        }

        tSat[0].setLocation(120, 250);
        panel2.add(tSat[0]);

        tSat[1].setLocation(180, 250);
        panel2.add(tSat[1]);

        tSat[2].setLocation(240, 250);
        panel2.add(tSat[2]);

        sunB = new JButton("Set");
        sunB.setSize(80, 24);
        sunB.setLocation(320, 290);
        sunB.addActionListener(new vetTextHandler());
        panel2.add(sunB);

        sun = new JLabel("Sunday");
        sun.setLocation(20, 290);
        sun.setSize(sun.getPreferredSize());
        panel2.add(sun);

        tSun = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            tSun[i] = new JComboBox(stuff);
            tSun[i].setSize(tSun[i].getPreferredSize());
        }

        tSun[0].setLocation(120, 290);
        panel2.add(tSun[0]);

        tSun[1].setLocation(180, 290);
        panel2.add(tSun[1]);

        tSun[2].setLocation(240, 290);
        panel2.add(tSun[2]);

        createWR = new JButton("Create New Rota for this Week");
        createWR.setSize(createWR.getPreferredSize());
        createWR.setLocation(150, 10);
        createWR.addActionListener(new vetTextHandler());
        panel2.add(createWR);

        rotaTable = new JTable();

        scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(rotaTable);
        scrollPane2.setLocation(20, 380);
        scrollPane2.setSize(1300, 100);
        scrollPane2.setVerticalScrollBarPolicy(scrollPane2.VERTICAL_SCROLLBAR_ALWAYS);
        panel2.add(scrollPane2);

        java.util.List<WeekRota> rotaList = new LinkedList();
        WeekRota.populateList(rotaList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String columnNames[] = {"Rota ID", "Rota Date", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        model2 = new DefaultTableModel(new String[0][0], columnNames);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        for (WeekRota x : rotaList) {
            String[] o = new String[9];
            o[0] = x.getID();
            o[1] = dateFormat.format(x.getRotaDate().getTime());
            try {
                o[2] = x.getMon()[0].getID() + ", " + x.getMon()[1].getID() + ", " + x.getMon()[2].getID();
                o[3] = x.getTues()[0].getID() + ", " + x.getTues()[1].getID() + ", " + x.getTues()[2].getID();
                o[4] = x.getWed()[0].getID() + ", " + x.getWed()[1].getID() + ", " + x.getWed()[2].getID();
                o[5] = x.getThur()[0].getID() + ", " + x.getThur()[1].getID() + ", " + x.getThur()[2].getID();
                o[6] = x.getFri()[0].getID() + ", " + x.getFri()[1].getID() + ", " + x.getFri()[2].getID();
                o[7] = x.getSat()[0].getID() + ", " + x.getSat()[1].getID() + ", " + x.getSat()[2].getID();
                o[8] = x.getSun()[0].getID() + ", " + x.getSun()[1].getID() + ", " + x.getSun()[2].getID();
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "The newest rota has not been completed yet!");
            } finally {
                model2.addRow(o);
            }
        }
        rotaTable.setModel(model2);

        tRota = new JTextField();
        tRota.setColumns(6);
        tRota.setSize(tRota.getPreferredSize());
        tRota.setLocation(350, 10);
        tRota.setEnabled(true);
        panel2.add(tRota);

        refreshB = new JButton("Refresh Table");
        refreshB.setSize(refreshB.getPreferredSize());
        refreshB.setLocation(20, 340);
        refreshB.addActionListener(new vetTextHandler());
        panel2.add(refreshB);

        vetTable = new JTable();
        scrollPane3 = new JScrollPane();
        scrollPane3.setViewportView(vetTable);
        scrollPane3.setLocation(450, 10);
        scrollPane3.setSize(900, 300);
        scrollPane3.setVerticalScrollBarPolicy(scrollPane3.VERTICAL_SCROLLBAR_ALWAYS);
        panel2.add(scrollPane3);

        java.util.List<Vet> vetList = new LinkedList();
        Vet.populateList(vetList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String cn[] = {"ID", "First Name", "Last Name", "Area Of Expertise 1", "Area Of Expertise 2"};
        model3 = new DefaultTableModel(new String[0][0], cn);

        for (Vet x : list) {
            String[] o = new String[5];
            o[0] = x.getID();
            o[1] = x.getFirstName();
            o[2] = x.getLastName();
            o[3] = x.getAreaOfExpertise()[0];
            o[4] = x.getAreaOfExpertise()[1];
            model3.addRow(o);
        }

        vetTable.setModel(model3);

        /**
         * Tab 3
         */
        panel3 = new JPanel();
        panel3.setLayout(null);

        vTable = new JLabel("Vet Report :");
        vTable.setSize(vTable.getPreferredSize());
        vTable.setLocation(20, 20);
        panel3.add(vTable);

        vetReport = new JTable();

        vetSP = new JScrollPane();
        vetSP.setViewportView(vetReport);
        vetSP.setLocation(20, 40);
        vetSP.setSize(1000, 100);
        vetSP.setVerticalScrollBarPolicy(vetSP.VERTICAL_SCROLLBAR_ALWAYS);
        panel3.add(vetSP);

        java.util.List<Appointment> alist = new LinkedList();
        Appointment.populateList(alist);

        java.util.List<Vet> vList = new LinkedList();
        Vet.populateList(vList);

        java.util.List<Customer> cList = new LinkedList();
        Customer.populateList(cList);

        String[] CN = {"VetID", "Vet Name", "Expertise", "No.of Pet Seen", "Sum of Charges"};
        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        vetModel = new DefaultTableModel(new String[0][0], CN);

        double income = 0;
        for (Vet vet : vList) {
            double sCharges = 0;
            int petSeen = 0;
            String[] y = new String[5];
            y[0] = vet.getID();
            y[1] = vet.getFirstName() + " " + vet.getLastName();
            y[2] = vet.getAreaOfExpertise()[0] + ", " + vet.getAreaOfExpertise()[1];

            for (Appointment app : alist) {
                if (app.getVet().getID().equalsIgnoreCase(vet.getID())) {
                    for (Customer c : cList) {
                        for (int i = 0; i < c.getNumberOfPet(); i++) {
                            if (c.getPet(i).getID().equalsIgnoreCase(app.getPetID())) {
                                if (Objects.equals(true, c.getPet(i).getSeen())) {
                                    petSeen += 1;
                                    sCharges += c.getPet(i).getCharges();
                                    income += sCharges;
                                }
                            }
                        }
                    }
                }
            }

            y[3] = Integer.toString(petSeen);
            y[4] = Double.toString(sCharges);

            vetModel.addRow(y);
        }

        vetReport.setModel(vetModel);

        totalIncome = new JLabel("Total Amount of Income: ");
        totalIncome.setSize(totalIncome.getPreferredSize());
        totalIncome.setLocation(820, 150);
        panel3.add(totalIncome);

        amount = new JLabel(Double.toString(income) + " MYR");
        amount.setSize(amount.getPreferredSize());
        amount.setLocation(950, 150);
        panel3.add(amount);

        pTable = new JLabel("Pet Report :");
        pTable.setSize(pTable.getPreferredSize());
        pTable.setLocation(20, 180);
        panel3.add(pTable);

        petReport = new JTable();

        petSP = new JScrollPane();
        petSP.setViewportView(petReport);
        petSP.setLocation(20, 200);
        petSP.setSize(1000, 43);
        panel3.add(petSP);

        String[] Cn = {"Amphibian", "Bird", "Fish", "Household Pet", "Reptile", "Total Pet", "No of Pet Staying Overnight"};
        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        petModel = new DefaultTableModel(new String[0][0], Cn);
        int amp = 0;
        int bird = 0;
        int fish = 0;
        int hp = 0;
        int rep = 0;
        int bs = 0;
        String[] y = new String[7];

        for (Customer x : cList) {
            for (int i = 0; i < x.getNumberOfPet(); i++) {
                if (x.getPet(i).getType().equalsIgnoreCase("Amphibian")) {
                    amp++;
                } else if (x.getPet(i).getType().equalsIgnoreCase("Bird")) {
                    bird++;
                } else if (x.getPet(i).getType().equalsIgnoreCase("Fish")) {
                    fish++;
                } else if (x.getPet(i).getType().equalsIgnoreCase("HouseholdPet")) {
                    hp++;
                } else if (x.getPet(i).getType().equalsIgnoreCase("Reptile")) {
                    rep++;
                }

                if (Objects.equals(true, x.getPet(i).getBoarding())) {
                    bs++;
                }
            }
        }
        y[0] = Integer.toString(amp);
        y[1] = Integer.toString(bird);
        y[2] = Integer.toString(fish);
        y[3] = Integer.toString(hp);
        y[4] = Integer.toString(rep);
        y[5] = Integer.toString(amp + bird + fish + hp + rep);
        y[6] = Integer.toString(bs);

        petModel.addRow(y);
        petReport.setModel(petModel);

        tab1.addTab("New Employee", panel1);
        tab1.addTab("New Working Rota", panel2);
        tab1.addTab("Reports", panel3);
        add(tab1);

        /**
         * Event Handlers
         */
        rReceptionist.addItemListener(new radioButtonHandler("r"));
        rBoardingStaff.addItemListener(new radioButtonHandler("bs"));
        rVet.addItemListener(new radioButtonHandler("v"));

        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tFirstName.getText().equals("") || tLastName.getText().equals("") || tPassword.getText().equals("") || tNRIC.getText().equals("") || tTel.getText().equals("") || tEmail.getText().equals("") || tAddress.getText().equals("")) {
                                throw new NullFieldException("Please complete all the field!");
                            }

                            Owner o = new Owner();

                            if (rReceptionist.isSelected()) {
                                java.util.List<Receptionist> list = new LinkedList();
                                o.addReceptionist(tFirstName.getText(), tLastName.getText(), tPassword.getText(), tNRIC.getText(), tTel.getText(), tEmail.getText(), tAddress.getText());
                                JOptionPane.showMessageDialog(null, "New Receptionist Added");
                            } else if (rBoardingStaff.isSelected()) {
                                java.util.List<BoardingStaff> list = new LinkedList();
                                o.addBoardingStaff(tFirstName.getText(), tLastName.getText(), tPassword.getText(), tNRIC.getText(), tTel.getText(), tEmail.getText(), tAddress.getText());
                                JOptionPane.showMessageDialog(null, "New Boarding Staff Added");
                            } else if (rVet.isSelected()) {
                                java.util.List<Vet> list = new LinkedList();
                                o.addVet((String) expertise.getSelectedItem(), (String) expertise2.getSelectedItem(), tFirstName.getText(), tLastName.getText(), tPassword.getText(), tNRIC.getText(), tTel.getText(), tEmail.getText(), tAddress.getText());
                                JOptionPane.showMessageDialog(null, "New Vet Added");

                            }
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        removeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tID.getText().equals("")) {
                                throw new NullFieldException("Please choose a staff from the table!");
                            }
                            Owner o = new Owner();

                            if (rReceptionist.isSelected()) {
                                java.util.List<Receptionist> list = new LinkedList();
                                int row = table.getSelectedRow();
                                o.removeReceptionist(table.getModel().getValueAt(row, 0).toString());
                                JOptionPane.showMessageDialog(null, "Receptionist Removed");
                            } else if (rBoardingStaff.isSelected()) {
                                java.util.List<BoardingStaff> list = new LinkedList();
                                int row = table.getSelectedRow();
                                o.removeBoardingStaff(table.getModel().getValueAt(row, 0).toString());
                                JOptionPane.showMessageDialog(null, "Boarding Staff Removed");
                            } else if (rVet.isSelected()) {
                                java.util.List<Vet> list = new LinkedList();
                                int row = table.getSelectedRow();
                                o.removeVet(table.getModel().getValueAt(row, 0).toString());
                                JOptionPane.showMessageDialog(null, "Vet Removed");
                            }

                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "This Staff has not been added to the system yet! Please choose one from the table.");
                        }
                    }
                }
        );

        table.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = table.getSelectedRow();
                        tID.setText(table.getModel().getValueAt(row, 0).toString());
                        tFirstName.setText(table.getModel().getValueAt(row, 1).toString());
                        tLastName.setText(table.getModel().getValueAt(row, 2).toString());
                        tNRIC.setText(table.getModel().getValueAt(row, 3).toString());
                        tTel.setText(table.getModel().getValueAt(row, 4).toString());
                        tEmail.setText(table.getModel().getValueAt(row, 5).toString());
                        tAddress.setText(table.getModel().getValueAt(row, 6).toString());

                    }
                }
        );

        createWR.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        Owner o = new Owner();
                        o.createWeekRota();
                        JOptionPane.showMessageDialog(null, "New Rota is Created");
                    }
                }
        );

        rotaTable.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = rotaTable.getSelectedRow();
                        tRota.setText(rotaTable.getModel().getValueAt(row, 0).toString());
                    }
                }
        );

        refreshB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        java.util.List<WeekRota> rotaList = new LinkedList();
                        WeekRota.populateList(rotaList);

                        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                        String columnNames[] = {"Rota ID", "Rota Date", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                        model2 = new DefaultTableModel(new String[0][0], columnNames);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                        for (WeekRota x : rotaList) {
                            String[] o = new String[9];
                            o[0] = x.getID();
                            o[1] = dateFormat.format(x.getRotaDate().getTime());
                            try {
                                o[2] = x.getMon()[0].getID() + ", " + x.getMon()[1].getID() + ", " + x.getMon()[2].getID();
                                o[3] = x.getTues()[0].getID() + ", " + x.getTues()[1].getID() + ", " + x.getTues()[2].getID();
                                o[4] = x.getWed()[0].getID() + ", " + x.getWed()[1].getID() + ", " + x.getWed()[2].getID();
                                o[5] = x.getThur()[0].getID() + ", " + x.getThur()[1].getID() + ", " + x.getThur()[2].getID();
                                o[6] = x.getFri()[0].getID() + ", " + x.getFri()[1].getID() + ", " + x.getFri()[2].getID();
                                o[7] = x.getSat()[0].getID() + ", " + x.getSat()[1].getID() + ", " + x.getSat()[2].getID();
                                o[8] = x.getSun()[0].getID() + ", " + x.getSun()[1].getID() + ", " + x.getSun()[2].getID();
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "The newest rota has not been completed yet!");
                            } finally {
                                model2.addRow(o);
                            }
                        }
                        rotaTable.setModel(model2);

                    }
                }
        );
    }

    //Inner Class 
    private class radioButtonHandler implements ItemListener {

        private String pos;

        public radioButtonHandler(String pos) {
            this.pos = pos;
        }

        public void itemStateChanged(ItemEvent e) {

            if (pos.equalsIgnoreCase("r")) {

                Receptionist r = new Receptionist();
                tID.setText(r.generateID());
                expertise.setEnabled(false);
                expertise2.setEnabled(false);

                java.util.List<Receptionist> list = new LinkedList();
                Receptionist.populateList(list);

                //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "Area Of Expertise 1", "Area Of Expertise 2"};
                model = new DefaultTableModel(new String[0][0], columnNames);

                for (Receptionist x : list) {
                    String[] o = new String[9];
                    o[0] = x.getID();
                    o[1] = x.getFirstName();
                    o[2] = x.getLastName();
                    o[3] = x.getNRIC();
                    o[4] = x.getTel();
                    o[5] = x.getEmail();
                    o[6] = x.getAddress();

                    model.addRow(o);
                }
                table.setModel(model);

            } else if (pos.equalsIgnoreCase("bs")) {

                BoardingStaff bs = new BoardingStaff();
                tID.setText(bs.generateID());
                expertise.setEnabled(false);
                expertise2.setEnabled(false);

                java.util.List<BoardingStaff> list = new LinkedList();
                BoardingStaff.populateList(list);

                //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "Area Of Expertise 1", "Area Of Expertise 2"};
                model = new DefaultTableModel(new String[0][0], columnNames);

                for (BoardingStaff x : list) {
                    String[] o = new String[9];
                    o[0] = x.getID();
                    o[1] = x.getFirstName();
                    o[2] = x.getLastName();
                    o[3] = x.getNRIC();
                    o[4] = x.getTel();
                    o[5] = x.getEmail();
                    o[6] = x.getAddress();

                    model.addRow(o);
                }
                table.setModel(model);

            } else if (pos.equalsIgnoreCase("v")) {

                Vet vet = new Vet();
                expertise.setEnabled(true);
                expertise2.setEnabled(true);
                tID.setText(vet.generateID());

                java.util.List<Vet> list = new LinkedList();
                Vet.populateList(list);

                //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "Area Of Expertise 1", "Area Of Expertise 2"};
                model = new DefaultTableModel(new String[0][0], columnNames);

                for (Vet x : list) {
                    String[] o = new String[9];
                    o[0] = x.getID();
                    o[1] = x.getFirstName();
                    o[2] = x.getLastName();
                    o[3] = x.getNRIC();
                    o[4] = x.getTel();
                    o[5] = x.getEmail();
                    o[6] = x.getAddress();
                    o[7] = x.getAreaOfExpertise()[0];
                    o[8] = x.getAreaOfExpertise()[1];
                    model.addRow(o);
                }

                table.setModel(model);
            }
        }
    }

    private class vetTextHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                java.util.List<Vet> list = new LinkedList();
                Vet.populateList(list);
                Vet[] vet = new Vet[3];
                Owner o = new Owner();

                java.util.List<WeekRota> list2 = new LinkedList();
                WeekRota.populateList(list2);
                String newRotaID = list2.get(list2.size() - 1).getID(); //get the last element in the list

                if (event.getSource().equals(monB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tMon[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "mon", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(tuesB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tTues[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "tues", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(wedB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tWed[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "wed", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(thurB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tThur[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "thur", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(friB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tFri[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "fri", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(satB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tSat[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "sat", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                } else if (event.getSource().equals(sunB)) {
                    for (Vet v : list) {
                        for (int i = 0; i < 3; i++) {
                            if (((String) tSun[i].getSelectedItem()).equalsIgnoreCase(v.getID())) {
                                vet[i] = v;
                            }
                        }
                    }

                    o.setRota(newRotaID, "sun", vet[0], vet[1], vet[2]);
                    JOptionPane.showMessageDialog(null, "Set!");
                }
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Please create a rota first!");
            }
        }
    }
}
