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

public class EditCustomerFrame extends JFrame {

    private JTabbedPane tab1;
    private JPanel panel1, panel2;

    //panel1
    private JLabel ID, firstName, lastName, NRIC, tel, email, address, petType, petSpecies, petName;
    private JTextField tID, tFirstName, tLastName, tNRIC, tTel, tEmail, tAddress, tPetSpecies, tPetName, tPetType;
    private JButton editCustomerButton, editPetButton;
    private JList petList;
    private JScrollPane sp1, sp2;
    private String customerID;

    //panel2
    private JLabel petID;
    private JTextField tPetID;

    public EditCustomerFrame(String customerID) {

        super("Edit Customer and Pet Details");
        this.customerID = customerID;

        //Tab 1
        tab1 = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(null);

        ID = new JLabel("ID");
        ID.setLocation(20, 20);
        ID.setSize(ID.getPreferredSize());
        panel1.add(ID);

        tID = new JTextField(customerID);
        tID.setColumns(15);
        tID.setSize(tID.getPreferredSize());
        tID.setLocation(150, 20);
        tID.setEnabled(false);
        panel1.add(tID);

        firstName = new JLabel("First Name");
        firstName.setLocation(20, 60);
        firstName.setSize(firstName.getPreferredSize());
        panel1.add(firstName);

        tFirstName = new JTextField();
        tFirstName.setColumns(15);
        tFirstName.setSize(tFirstName.getPreferredSize());
        tFirstName.setLocation(150, 60);
        tFirstName.setToolTipText("Enter your first name");
        panel1.add(tFirstName);

        lastName = new JLabel("Last Name");
        lastName.setLocation(20, 100);
        lastName.setSize(lastName.getPreferredSize());
        panel1.add(lastName);

        tLastName = new JTextField();
        tLastName.setColumns(15);
        tLastName.setSize(tLastName.getPreferredSize());
        tLastName.setLocation(150, 100);
        tLastName.setToolTipText("Enter your last name");
        panel1.add(tLastName);

        NRIC = new JLabel("NRIC");
        NRIC.setLocation(20, 140);
        NRIC.setSize(NRIC.getPreferredSize());
        panel1.add(NRIC);

        tNRIC = new JTextField();
        tNRIC.setColumns(15);
        tNRIC.setSize(tNRIC.getPreferredSize());
        tNRIC.setLocation(150, 140);
        tNRIC.setToolTipText("e.g. 940328086126");
        panel1.add(tNRIC);

        tel = new JLabel("Tel. No");
        tel.setLocation(20, 180);
        tel.setSize(tel.getPreferredSize());
        panel1.add(tel);

        tTel = new JTextField();
        tTel.setColumns(15);
        tTel.setSize(tTel.getPreferredSize());
        tTel.setLocation(150, 180);
        tTel.setToolTipText("e.g. 0169532287");
        panel1.add(tTel);

        email = new JLabel("E-mail Address");
        email.setLocation(20, 220);
        email.setSize(email.getPreferredSize());
        panel1.add(email);

        tEmail = new JTextField();
        tEmail.setColumns(15);
        tEmail.setSize(tEmail.getPreferredSize());
        tEmail.setLocation(150, 220);
        tEmail.setToolTipText("e.g. eric.you@hotmail.com");
        panel1.add(tEmail);

        address = new JLabel("Home Address");
        address.setLocation(20, 260);
        address.setSize(address.getPreferredSize());
        panel1.add(address);

        tAddress = new JTextField();
        tAddress.setColumns(15);
        tAddress.setSize(tAddress.getPreferredSize());
        tAddress.setLocation(150, 260);
        panel1.add(tAddress);

        editCustomerButton = new JButton("Edit Customer Profile");
        editCustomerButton.setSize(editCustomerButton.getPreferredSize());
        editCustomerButton.setLocation(150, 300);
        panel1.add(editCustomerButton);

        /**
         * tab2
         */
        panel2 = new JPanel();
        panel2.setLayout(null);

        petID = new JLabel("PetID");
        petID.setLocation(20, 20);
        petID.setSize(petID.getPreferredSize());
        panel2.add(petID);

        tPetID = new JTextField();
        tPetID.setColumns(15);
        tPetID.setSize(tPetID.getPreferredSize());
        tPetID.setLocation(150, 20);
        tPetID.setEnabled(false);
        panel2.add(tPetID);

        petType = new JLabel("Pet Type");
        petType.setLocation(20, 60);
        petType.setSize(petType.getPreferredSize());
        panel2.add(petType);

        tPetType = new JTextField();
        tPetType.setColumns(15);
        tPetType.setSize(tPetType.getPreferredSize());
        tPetType.setLocation(150, 60);
        tPetType.setEnabled(false);
        panel2.add(tPetType);

        petSpecies = new JLabel("Pet Species");
        petSpecies.setLocation(20, 100);
        petSpecies.setSize(petSpecies.getPreferredSize());
        panel2.add(petSpecies);

        tPetSpecies = new JTextField();
        tPetSpecies.setColumns(15);
        tPetSpecies.setSize(tPetSpecies.getPreferredSize());
        tPetSpecies.setLocation(150, 100);
        panel2.add(tPetSpecies);

        petName = new JLabel("Pet Name");
        petName.setLocation(20, 140);
        petName.setSize(petName.getPreferredSize());
        panel2.add(petName);

        tPetName = new JTextField();
        tPetName.setColumns(15);
        tPetName.setSize(tPetName.getPreferredSize());
        tPetName.setLocation(150, 140);
        panel2.add(tPetName);

        editPetButton = new JButton("Edit Pet Profile");
        editPetButton.setSize(editPetButton.getPreferredSize());
        editPetButton.setLocation(150, 180);
        panel2.add(editPetButton);

        //  JList Codes
        java.util.List<Customer> cList = new LinkedList();
        java.util.List<String> petID = new LinkedList();
        Customer.populateList(cList);
        String[] pets;

        for (Customer x : cList) {
            if (x.getID().equalsIgnoreCase(customerID)) {
                for (int i = 0; i < x.getNumberOfPet(); i++) {
                    petID.add(x.getPet(i).getID());
                }
            }
        }

        pets = petID.toArray(new String[petID.size()]);
        petList = new JList(pets);
        petList.setVisibleRowCount(10);
        petList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sp1 = new JScrollPane();
        sp1.setViewportView(petList);
        sp1.setLocation(300, 20);
        sp1.setSize(200, 250);
        sp1.setVerticalScrollBarPolicy(sp1.VERTICAL_SCROLLBAR_ALWAYS);
        panel2.add(sp1);

        //adding panels to tab and to frame
        tab1.addTab("Edit Customer", panel1);
        tab1.addTab("Edit Pet", panel2);
        add(tab1);

        //Populate all the text fields from the binary file
        java.util.List<Customer> list = new LinkedList();
        Customer.populateList(list);
        //String custID = (String) custList.getSelectedValue();
        for (Customer x : list) {
            if (x.getID().equalsIgnoreCase(customerID)) {
                tID.setText(x.getID());
                tFirstName.setText(x.getFirstName());
                tLastName.setText(x.getLastName());
                tNRIC.setText(x.getNRIC());
                tTel.setText(x.getTel());
                tEmail.setText(x.getEmail());
                tAddress.setText(x.getAddress());
            }
        }

        petList.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        java.util.List<Customer> list = new LinkedList();
                        Customer.populateList(list);
                        String petID = (String) petList.getSelectedValue();
                        for (Customer x : list) {
                            if (x.getID().equalsIgnoreCase(customerID)) {
                                for (int i = 0; i < x.getNumberOfPet(); i++) {
                                    if (x.getPet(i).getID().equalsIgnoreCase(petID)) {
                                        tPetID.setText(x.getPet(i).getID());
                                        tPetType.setText(x.getPet(i).getType());
                                        tPetSpecies.setText(x.getPet(i).getSpecies());
                                        tPetName.setText(x.getPet(i).getName());
                                    }
                                }
                            }
                        }
                    }
                }
        );

        editCustomerButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tFirstName.getText().equals("") || tLastName.getText().equals("") || tNRIC.getText().equals("") || tTel.getText().equals("") || tEmail.getText().equals("") || tAddress.getText().equals("")) {
                                throw new NullFieldException("Please fill in All the field!");
                            }
                            Receptionist r = new Receptionist();
                            r.editCustomerProfile(customerID, tFirstName.getText(), tLastName.getText(), tNRIC.getText(), tTel.getText(), tEmail.getText(), tAddress.getText());
                            JOptionPane.showMessageDialog(null, "Customer Details is Edited");
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        editPetButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tPetID.getText().equals("")) {
                                throw new NullFieldException("Please choose a pet from the list!");
                            }
                            if (tPetSpecies.getText().equals("") || tPetName.getText().equals("")) {
                                throw new NullFieldException("Please fill in all the field for pet!");
                            }
                            Receptionist r = new Receptionist();
                            r.editPetProfile(customerID, tPetID.getText(), tPetSpecies.getText(), tPetName.getText());
                            JOptionPane.showMessageDialog(null, "Pet Details is Edited");
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );
    }
}
