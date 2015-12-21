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

public class ReceptionistPage extends JFrame {

    private JTabbedPane tab1;
    private JPanel panel1;

    //panel1
    private JLabel ID, firstName, lastName, NRIC, tel, email, address, petType, petSpecies, petName;
    private JTextField tID, tFirstName, tLastName, tNRIC, tTel, tEmail, tAddress, tPetSpecies, tPetName;
    private JRadioButton rNewCustomer, rRegisterPet;
    private ButtonGroup bg;
    private JComboBox cbPetType;
    private JButton addButton, makeAppointmentButton;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public ReceptionistPage() {
        super("Receptionist Page");

        //Tab 1
        tab1 = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(null);

        rNewCustomer = new JRadioButton("New Customer", true);
        rNewCustomer.setLocation(80, 20);
        rNewCustomer.setSize(rNewCustomer.getPreferredSize());
        panel1.add(rNewCustomer);

        rRegisterPet = new JRadioButton("Register Pet Only", false);
        rRegisterPet.setLocation(180, 20);
        rRegisterPet.setSize(rRegisterPet.getPreferredSize());
        panel1.add(rRegisterPet);

        bg = new ButtonGroup();
        bg.add(rNewCustomer);
        bg.add(rRegisterPet);

        ID = new JLabel("ID");
        ID.setLocation(20, 80);
        ID.setSize(ID.getPreferredSize());
        panel1.add(ID);

        Customer cus = new Customer();
        tID = new JTextField(cus.generateID());
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

        NRIC = new JLabel("NRIC");
        NRIC.setLocation(20, 200);
        NRIC.setSize(NRIC.getPreferredSize());
        panel1.add(NRIC);

        tNRIC = new JTextField();
        tNRIC.setColumns(15);
        tNRIC.setSize(tNRIC.getPreferredSize());
        tNRIC.setLocation(150, 200);
        tNRIC.setToolTipText("e.g. 940328086126");
        panel1.add(tNRIC);

        tel = new JLabel("Tel. No");
        tel.setLocation(20, 240);
        tel.setSize(tel.getPreferredSize());
        panel1.add(tel);

        tTel = new JTextField();
        tTel.setColumns(15);
        tTel.setSize(tTel.getPreferredSize());
        tTel.setLocation(150, 240);
        tTel.setToolTipText("e.g. 0169532287");
        panel1.add(tTel);

        email = new JLabel("E-mail Address");
        email.setLocation(20, 280);
        email.setSize(email.getPreferredSize());
        panel1.add(email);

        tEmail = new JTextField();
        tEmail.setColumns(15);
        tEmail.setSize(tEmail.getPreferredSize());
        tEmail.setLocation(150, 280);
        tEmail.setToolTipText("e.g. eric.you@hotmail.com");
        panel1.add(tEmail);

        address = new JLabel("Home Address");
        address.setLocation(20, 320);
        address.setSize(address.getPreferredSize());
        panel1.add(address);

        tAddress = new JTextField();
        tAddress.setColumns(15);
        tAddress.setSize(tAddress.getPreferredSize());
        tAddress.setLocation(150, 320);
        panel1.add(tAddress);

        petType = new JLabel("Pet Type");
        petType.setLocation(20, 360);
        petType.setSize(petType.getPreferredSize());
        panel1.add(petType);

        String[] petTypeList = {"Amphibian", "Bird", "Fish", "HouseholdPet", "Reptile"};
        cbPetType = new JComboBox(petTypeList);
        cbPetType.setSize(cbPetType.getPreferredSize());
        cbPetType.setLocation(150, 360);
        panel1.add(cbPetType);

        petSpecies = new JLabel("Pet Species");
        petSpecies.setLocation(20, 400);
        petSpecies.setSize(petSpecies.getPreferredSize());
        panel1.add(petSpecies);

        tPetSpecies = new JTextField();
        tPetSpecies.setColumns(15);
        tPetSpecies.setSize(tPetSpecies.getPreferredSize());
        tPetSpecies.setLocation(150, 400);
        panel1.add(tPetSpecies);

        petName = new JLabel("Pet Name");
        petName.setLocation(20, 440);
        petName.setSize(petName.getPreferredSize());
        panel1.add(petName);

        tPetName = new JTextField();
        tPetName.setColumns(15);
        tPetName.setSize(tPetName.getPreferredSize());
        tPetName.setLocation(150, 440);
        panel1.add(tPetName);

        addButton = new JButton("Add");
        addButton.setSize(80, 23);
        addButton.setLocation(150, 480);
        panel1.add(addButton);

        makeAppointmentButton = new JButton("Make Appointment");
        makeAppointmentButton.setSize(makeAppointmentButton.getPreferredSize());
        makeAppointmentButton.setLocation(1230, 420);
        panel1.add(makeAppointmentButton);

        table = new JTable(model);
        table.setToolTipText("Double-click to edit");
        java.util.List<Customer> customerList = new LinkedList();
        Customer.populateList(customerList);

        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "No. of Pet", "PetID", "PetType", "Pet Species", "PetName"};
        model = new DefaultTableModel(new String[0][0], columnNames);

        for (Customer x : customerList) {

            String[] o = new String[12];
            o[0] = x.getID();
            o[1] = x.getFirstName();
            o[2] = x.getLastName();
            o[3] = x.getNRIC();
            o[4] = x.getTel();
            o[5] = x.getEmail();
            o[6] = x.getAddress();
            o[7] = Integer.toString(x.getNumberOfPet());

            String ID, type, species, name;
            ID = x.getPet(0).getID();
            type = x.getPet(0).getType();
            species = x.getPet(0).getSpecies();
            name = x.getPet(0).getName();

            for (int i = 1; i < x.getNumberOfPet(); i++) {
                ID = ID + "," + x.getPet(i).getID();

                type = type + "," + x.getPet(i).getType();

                species = species + "," + x.getPet(i).getSpecies();

                name = name + "," + x.getPet(i).getName();

            }
            o[8] = ID;
            o[9] = type;
            o[10] = species;
            o[11] = name;
            model.addRow(o);
        }
        table.setModel(model);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setLocation(320, 10);
        scrollPane.setSize(1040, 400);
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(scrollPane);

        tab1.addTab("Registration", panel1);
        add(tab1);

        //Event Handler
        rNewCustomer.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        Customer c = new Customer();
                        tID.setText(c.generateID());
                    }
                }
        );

        rNewCustomer.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {

                        tFirstName.setEnabled(true);
                        tLastName.setEnabled(true);
                        tNRIC.setEnabled(true);
                        tTel.setEnabled(true);
                        tEmail.setEnabled(true);
                        tAddress.setEnabled(true);

                        Customer c = new Customer();
                        tID.setText(c.generateID());
                    }
                }
        );

        rRegisterPet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        tID.setText("");
                        JOptionPane.showMessageDialog(null, "Please Choose a Customer from the TABLE First", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                }
        );

        rRegisterPet.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent event) {
                        tID.setEnabled(false);
                        tFirstName.setEnabled(false);
                        tLastName.setEnabled(false);
                        tNRIC.setEnabled(false);
                        tTel.setEnabled(false);
                        tEmail.setEnabled(false);
                        tAddress.setEnabled(false);
                    }
                }
        );

        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            Receptionist r = new Receptionist();
                            java.util.List<Customer> customerList = new LinkedList();

                            if (rNewCustomer.isSelected()) {
                                if (tID.getText().equals("") || tFirstName.getText().equals("") || tLastName.getText().equals("") || tNRIC.getText().equals("") || tTel.getText().equals("") || tEmail.getText().equals("") || tAddress.getText().equals("") || tPetSpecies.getText().equals("") || tPetName.getText().equals("")) {
                                    throw new NullFieldException("Please Fill in all the Field!");
                                }

                                r.createCustomerProfile((String) cbPetType.getSelectedItem(), tPetSpecies.getText(), tPetName.getText(), tFirstName.getText(), tLastName.getText(), tNRIC.getText(), tTel.getText(), tEmail.getText(), tAddress.getText());
                                JOptionPane.showMessageDialog(null, "New Customer Created!");

                                Customer.populateList(customerList);

                                //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                                String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "No. of Pet", "PetID", "PetType", "Pet Species", "PetName"};
                                model = new DefaultTableModel(new String[0][0], columnNames);

                                for (Customer x : customerList) {

                                    String[] o = new String[12];
                                    o[0] = x.getID();
                                    o[1] = x.getFirstName();
                                    o[2] = x.getLastName();
                                    o[3] = x.getNRIC();
                                    o[4] = x.getTel();
                                    o[5] = x.getEmail();
                                    o[6] = x.getAddress();
                                    o[7] = Integer.toString(x.getNumberOfPet());

                                    String ID, type, species, name;
                                    ID = x.getPet(0).getID();
                                    type = x.getPet(0).getType();
                                    species = x.getPet(0).getSpecies();
                                    name = x.getPet(0).getName();

                                    for (int i = 1; i < x.getNumberOfPet(); i++) {
                                        ID = ID + "," + x.getPet(i).getID();

                                        type = type + "," + x.getPet(i).getType();

                                        species = species + "," + x.getPet(i).getSpecies();

                                        name = name + "," + x.getPet(i).getName();

                                    }
                                    o[8] = ID;
                                    o[9] = type;
                                    o[10] = species;
                                    o[11] = name;
                                    model.addRow(o);
                                }
                                table.setModel(model);

                            } else if (rRegisterPet.isSelected()) {
                                if (tID.getText().equals("")) {
                                    throw new NullFieldException("Please Choose a Customer from the Table!");
                                }

                                if (tPetSpecies.getText().equals("") || tPetName.getText().equals("")) {
                                    throw new NullFieldException("Please fill in all the field for Pet!");
                                }

                                r.createPetProfile(tID.getText(), (String) cbPetType.getSelectedItem(), tPetSpecies.getText(), tPetName.getText());
                                JOptionPane.showMessageDialog(null, "New Pet Added! ");

                                Customer.populateList(customerList);

                                //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
                                String columnNames[] = {"ID", "First Name", "Last Name", "NRIC", "Tel", "E-mail", "Address", "No. of Pet", "PetID", "PetType", "Pet Species", "PetName"};
                                model = new DefaultTableModel(new String[0][0], columnNames);

                                for (Customer x : customerList) {

                                    String[] o = new String[12];
                                    o[0] = x.getID();
                                    o[1] = x.getFirstName();
                                    o[2] = x.getLastName();
                                    o[3] = x.getNRIC();
                                    o[4] = x.getTel();
                                    o[5] = x.getEmail();
                                    o[6] = x.getAddress();
                                    o[7] = Integer.toString(x.getNumberOfPet());

                                    String ID, type, species, name;
                                    ID = x.getPet(0).getID();
                                    type = x.getPet(0).getType();
                                    species = x.getPet(0).getSpecies();
                                    name = x.getPet(0).getName();

                                    for (int i = 1; i < x.getNumberOfPet(); i++) {
                                        ID = ID + "," + x.getPet(i).getID();

                                        type = type + "," + x.getPet(i).getType();

                                        species = species + "," + x.getPet(i).getSpecies();

                                        name = name + "," + x.getPet(i).getName();

                                    }
                                    o[8] = ID;
                                    o[9] = type;
                                    o[10] = species;
                                    o[11] = name;
                                    model.addRow(o);
                                }
                                table.setModel(model);
                            }
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        table.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        int row = table.getSelectedRow();
                        tID.setText(table.getModel().getValueAt(row, 0).toString());
                    }
                }
        );

        table.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        if (event.getClickCount() % 2 == 0) {
                            int row = table.getSelectedRow();

                            EditCustomerFrame ec = new EditCustomerFrame(table.getModel().getValueAt(row, 0).toString());
                            ec.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            ec.setSize(600, 450);
                            ec.setLocation(400, 80);
                            ec.setVisible(true);
                        }
                    }
                }
        );

        makeAppointmentButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            int row = table.getSelectedRow();

                            AppointmentPage ap = new AppointmentPage(table.getModel().getValueAt(row, 0).toString());
                            ap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            ap.setSize(1350, 450);
                            ap.setVisible(true);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Please choose a Customer from the Table");
                        }
                    }
                }
        );
    }
}
