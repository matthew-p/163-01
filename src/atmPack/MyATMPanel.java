package atmPack;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyATMPanel extends JPanel {
    
    private ATM myAtm;
   // private JLabel headingLbl;
    private JLabel hundredsColLbl;
    private JLabel fiftiesColLbl;
    private JLabel twentiesColLbl;
    private JLabel hundredsCountLbl, fiftiesCountLbl, twentiesCountLbl;
    private JTextField hField, fField, tField;
    
    private JPanel atmPanel;
    
    private JButton takeOutBtn, putInBtn, saveBtn, loadBtn;
    
    public MyATMPanel() {
        
        myAtm = new ATM();
        
       // headingLbl = new JLabel("<html><font size=+2>ATM:</font></html>");
        hundredsColLbl = new JLabel("<html><font size=+1>Hundreds</font></html>");
        fiftiesColLbl = new JLabel("<html><font size=+1>Fifties</font></html>");
        twentiesColLbl = new JLabel("<html><font size=+1>Twenties</font></html>");
        
        hundredsCountLbl = new JLabel(Integer.toString(myAtm.getHundreds()));
        fiftiesCountLbl = new JLabel(Integer.toString(myAtm.getFifties()));
        twentiesCountLbl = new JLabel(Integer.toString(myAtm.getTwenties()));
        
        hField = new JTextField("0");
        fField = new JTextField("0");
        tField = new JTextField("0");
        
        takeOutBtn = new JButton("Remove Bills");
        putInBtn = new JButton("Add Bills");
        saveBtn = new JButton("Save ATM");
        loadBtn = new JButton("Load ATM");
        
        ButtonListener listener = new ButtonListener();
        takeOutBtn.addActionListener(listener);
        putInBtn.addActionListener(listener);
        saveBtn.addActionListener(listener);
        loadBtn.addActionListener(listener);
        
        atmPanel = new JPanel();
        // six rows, 3 columns
        atmPanel.setLayout(new GridLayout(5, 3, 5, 5));
        
        atmPanel.add(hundredsColLbl);
        atmPanel.add(fiftiesColLbl);
        atmPanel.add(twentiesColLbl);
        atmPanel.add(hundredsCountLbl);
        atmPanel.add(fiftiesCountLbl);
        atmPanel.add(twentiesCountLbl);
        atmPanel.add(hField);
        atmPanel.add(fField);
        atmPanel.add(tField);
        atmPanel.add(saveBtn);
        atmPanel.add(takeOutBtn);
        atmPanel.add(putInBtn);
        atmPanel.add(loadBtn);
        
        add(atmPanel);
        atmPanel.setVisible(true);
    }
    
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveBtn) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        myAtm.save(chooser.getSelectedFile().getName());
                    } catch (FileNotFoundException ex) {
                        new FileNotFoundException(ex.getMessage());
                    }
                }
            }
            if (e.getSource() == loadBtn) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        myAtm.load(chooser.getSelectedFile().getName());
                        
                        hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                        fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                        twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
                        
                    } catch (FileNotFoundException ex) {
                        new FileNotFoundException(ex.getMessage());
                    }
                }
            }
            if (e.getSource() == takeOutBtn) {
                myAtm.takeOut(Integer.parseInt(hField.getText()), 
                              Integer.parseInt(fField.getText()), 
                              Integer.parseInt(tField.getText()));
                hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
            }
            if (e.getSource() == putInBtn) {
                myAtm.putIn(Integer.parseInt(hField.getText()), 
                            Integer.parseInt(fField.getText()), 
                            Integer.parseInt(tField.getText()));
                hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
            }
        }
        
    }
}
