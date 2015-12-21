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

public class ProgDiagChargesPage extends JFrame {

    private JTextArea tProg, tDiag;
    private JLabel prognosis, diagnosis, charges, myr;
    private JSpinner tCharges;
    private SpinnerNumberModel sModel;
    private JButton savePnD, saveCharges, showPnD;
    private JScrollPane sp1, sp2;

    public ProgDiagChargesPage(String petID) {
        super("Prognosis, Diagnosis and Charges");
        setLayout(null);

        prognosis = new JLabel("Prognosis :");
        prognosis.setSize(prognosis.getPreferredSize());
        prognosis.setLocation(20, 20);
        add(prognosis);

        tProg = new JTextArea();

        sp1 = new JScrollPane();
        sp1.setViewportView(tProg);
        sp1.setLocation(20, 60);
        sp1.setSize(300, 100);
        sp1.setVerticalScrollBarPolicy(sp1.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp1);

        diagnosis = new JLabel("Diagnosis :");
        diagnosis.setSize(diagnosis.getPreferredSize());
        diagnosis.setLocation(350, 20);
        add(diagnosis);

        tDiag = new JTextArea();

        sp2 = new JScrollPane();
        sp2.setViewportView(tDiag);
        sp2.setLocation(350, 60);
        sp2.setSize(300, 100);
        sp2.setVerticalScrollBarPolicy(sp2.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp2);

        charges = new JLabel("Charges :");
        charges.setSize(charges.getPreferredSize());
        charges.setLocation(20, 250);
        add(charges);

        double min = 0.00;
        double value = 0.00;
        double max = 10000000.0;
        double stepSize = 0.10;

        sModel = new SpinnerNumberModel(value, min, max, stepSize);

        tCharges = new JSpinner(sModel);
        tCharges.setSize(50, 20);
        tCharges.setLocation(100, 250);
        add(tCharges);

        myr = new JLabel("MYR");
        myr.setSize(myr.getPreferredSize());
        myr.setLocation(160, 255);
        add(myr);

        savePnD = new JButton("Save Prognogsis and Dianogsis");
        savePnD.setSize(savePnD.getPreferredSize());
        savePnD.setLocation(100, 300);
        add(savePnD);

        saveCharges = new JButton("Save Charges");
        saveCharges.setSize(saveCharges.getPreferredSize());
        saveCharges.setLocation(350, 300);
        add(saveCharges);
        
        showPnD = new JButton("Show Previous Prognosis and Diagnosis result");
        showPnD.setSize(showPnD.getPreferredSize());
        showPnD.setLocation(350, 200);
        add(showPnD);

        savePnD.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (tProg.getText().equals("") || tDiag.getText().equals("")) {
                                throw new NullFieldException("Please do not leave the prognosis or diagnosis blank!");
                            }
                            Vet v = new Vet();
                            v.enterDiagnosisAndPrognosis(petID, tProg.getText(), tDiag.getText());
                            JOptionPane.showMessageDialog(null, "Saved");
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );

        saveCharges.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            if (((double) (tCharges.getModel().getValue()) <= 0 || tCharges.getModel().getValue() == null)) {
                                throw new NullFieldException("Please enter a positive amount of charges!");
                            }
                            Vet v = new Vet();
                            v.inputCharges(petID, ((double) (tCharges.getModel().getValue())));
                            JOptionPane.showMessageDialog(null, "Charges Set");
                        } catch (NullFieldException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
        );
        
        showPnD.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        java.util.List<Customer> list = new LinkedList();
                        Customer.populateList(list);
                        
                        for(Customer c : list){
                            for (int i = 0; i < c.getNumberOfPet(); i++) {
                                if(c.getPet(i).getID().equalsIgnoreCase(petID)){
                                    tProg.setText(c.getPet(i).getPrognosis());
                                    tDiag.setText(c.getPet(i).getDiagnosis());
                                    break;
                                }
                            }
                        }
                    }
                }
        );
    }
}
