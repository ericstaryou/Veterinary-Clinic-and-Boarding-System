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

public class VetPage extends JFrame {

    private JTable vetDiary;
    private JScrollPane sp;
    private DefaultTableModel model;
    private JLabel myDiary;
    private JButton PnD;
    private JComboBox diaryMode;
    private String vetID;

    public VetPage(String vetID) {
        super("Vet Page");
        setLayout(null);
        this.vetID = vetID;

        PnD = new JButton("Enter Prognosis, Diagnosis and Charges");
        PnD.setSize(PnD.getPreferredSize());
        PnD.setLocation(800, 150);
        add(PnD);

        myDiary = new JLabel("My Diary :");
        myDiary.setLocation(20, 20);
        myDiary.setSize(myDiary.getPreferredSize());
        add(myDiary);

        vetDiary = new JTable();

        sp = new JScrollPane();
        sp.setViewportView(vetDiary);
        sp.setLocation(20, 40);
        sp.setSize(1000, 100);
        sp.setVerticalScrollBarPolicy(sp.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp);

        java.util.List<Appointment> list = new LinkedList();
        Appointment.populateList(list);

        String[] CN = {"PetID", "Pet Type", "Pet Species", "Pet Name", "Appointment Date", "Appointment Hour", "Boarding", "Pet Status", "Pet Seen"};
        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        model = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : list) {
            if (vetID.equalsIgnoreCase(x.getVet().getID())) {
                for (int i = 0; i < x.getCustomer().getNumberOfPet(); i++) {
                    String[] y = new String[9];
                    if (x.getCustomer().getPet(i).getID().equalsIgnoreCase(x.getPetID())) {
                        y[0] = x.getPetID();
                        y[1] = x.getCustomer().getPet(i).getType();
                        y[2] = x.getCustomer().getPet(i).getSpecies();
                        y[3] = x.getCustomer().getPet(i).getName();
                        y[4] = dateFormat.format(x.getAppointmentTime().getTime());
                        y[5] = x.getAppointmentHour();

                        java.util.List<Customer> cList = new LinkedList();
                        Customer.populateList(cList);
                        for (Customer c : cList) {
                            if (x.getCustomer().getID().equalsIgnoreCase(c.getID())) {
                                for (int j = 0; j < c.getNumberOfPet(); j++) {
                                    if (Objects.equals(c.getPet(j).getBoarding(), true)) {
                                        y[6] = "Yes";
                                        y[7] = c.getPet(j).getStatus();
                                    } else {
                                        y[6] = "No";
                                    }

                                    if (Objects.equals(c.getPet(j).getSeen(), true)) {
                                        y[8] = "Yes";
                                    } else {
                                        y[8] = "No";
                                    }
                                }
                            }
                        }
                        model.addRow(y);

                    }
                }
            }
        }
        vetDiary.setModel(model);

        String[] mode = {"Show All", "Show Only Today"};
        diaryMode = new JComboBox(mode);
        diaryMode.setSize(diaryMode.getPreferredSize());
        diaryMode.setLocation(20, 150);
        add(diaryMode);

        diaryMode.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent event) {
                        if (((String) diaryMode.getSelectedItem()).equalsIgnoreCase("Show All")) {
                            showAll();
                        } else if (((String) diaryMode.getSelectedItem()).equalsIgnoreCase("Show Only Today")) {
                            showToday();
                        }
                    }
                }
        );

        PnD.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        int row = vetDiary.getSelectedRow();
                        try {
                            ProgDiagChargesPage p = new ProgDiagChargesPage((String) vetDiary.getModel().getValueAt(row, 0));
                            p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            p.setSize(800, 500);
                            p.setVisible(true);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Please Choose a Pet from your Diary");
                        }
                    }
                }
        );
    }

    public void showAll() {
        java.util.List<Appointment> list = new LinkedList();
        Appointment.populateList(list);

        String[] CN = {"PetID", "Pet Type", "Pet Species", "Pet Name", "Appointment Date", "Appointment Hour", "Boarding", "Pet Status", "Pet Seen"};
        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        model = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : list) {
            if (vetID.equalsIgnoreCase(x.getVet().getID())) {
                for (int i = 0; i < x.getCustomer().getNumberOfPet(); i++) {
                    String[] y = new String[9];
                    if (x.getCustomer().getPet(i).getID().equalsIgnoreCase(x.getPetID())) {
                        y[0] = x.getPetID();
                        y[1] = x.getCustomer().getPet(i).getType();
                        y[2] = x.getCustomer().getPet(i).getSpecies();
                        y[3] = x.getCustomer().getPet(i).getName();
                        y[4] = dateFormat.format(x.getAppointmentTime().getTime());
                        y[5] = x.getAppointmentHour();

                        java.util.List<Customer> cList = new LinkedList();
                        Customer.populateList(cList);
                        for (Customer c : cList) {
                            if (x.getCustomer().getID().equalsIgnoreCase(c.getID())) {
                                for (int j = 0; j < c.getNumberOfPet(); j++) {
                                    if (Objects.equals(c.getPet(j).getBoarding(), true)) {
                                        y[6] = "Yes";
                                        y[7] = c.getPet(j).getStatus();
                                    } else {
                                        y[6] = "No";
                                    }

                                    if (Objects.equals(c.getPet(j).getSeen(), true)) {
                                        y[8] = "Yes";
                                    } else {
                                        y[8] = "No";
                                    }
                                }
                            }
                        }
                        model.addRow(y);

                    }
                }
            }
        }
        vetDiary.setModel(model);
    }

    public void showToday() {
        java.util.List<Appointment> list = new LinkedList();
        Appointment.populateList(list);
        Calendar c = Calendar.getInstance();

        String[] CN = {"PetID", "Pet Type", "Pet Species", "Pet Name", "Appointment Date", "Appointment Hour", "Boarding", "Pet Status", "Pet Seen"};
        //ref: http://stackoverflow.com/questions/17368505/populating-jtable-using-list
        model = new DefaultTableModel(new String[0][0], CN);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (Appointment x : list) {
            if (vetID.equalsIgnoreCase(x.getVet().getID())) {
                if (x.getAppointmentTime().get(Calendar.YEAR) == c.get(Calendar.YEAR) && x.getAppointmentTime().get(Calendar.MONTH) == c.get(Calendar.MONTH) && x.getAppointmentTime().get(Calendar.DATE) == c.get(Calendar.DATE)) {
                    for (int i = 0; i < x.getCustomer().getNumberOfPet(); i++) {
                        String[] y = new String[9];
                        if (x.getCustomer().getPet(i).getID().equalsIgnoreCase(x.getPetID())) {
                            y[0] = x.getPetID();
                            y[1] = x.getCustomer().getPet(i).getType();
                            y[2] = x.getCustomer().getPet(i).getSpecies();
                            y[3] = x.getCustomer().getPet(i).getName();
                            y[4] = dateFormat.format(x.getAppointmentTime().getTime());
                            y[5] = x.getAppointmentHour();

                            java.util.List<Customer> cList = new LinkedList();
                            Customer.populateList(cList);
                            for (Customer cust : cList) {
                                if (x.getCustomer().getID().equalsIgnoreCase(cust.getID())) {
                                    for (int j = 0; j < cust.getNumberOfPet(); j++) {
                                        if (Objects.equals(cust.getPet(j).getBoarding(), true)) {
                                            y[6] = "Yes";
                                            y[7] = cust.getPet(j).getStatus();
                                        } else {
                                            y[6] = "No";
                                        }

                                        if (Objects.equals(cust.getPet(j).getSeen(), true)) {
                                            y[8] = "Yes";
                                        } else {
                                            y[8] = "No";
                                        }
                                    }
                                }
                            }
                            model.addRow(y);
                        }
                    }
                }
            }
        }
        vetDiary.setModel(model);
    }
}
