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
    private JTextField hField, fField, tField, fileName;
    
    private JPanel atmPanel;
    
    private JButton takeOutBtn, putInBtn, saveBtn, loadBtn;
    
    public MyATMPanel() {
        
        myAtm = new ATM();

        hundredsColLbl = new JLabel("<html><font size=+1>Hundreds</font></html>");
        fiftiesColLbl = new JLabel("<html><font size=+1>Fifties</font></html>");
        twentiesColLbl = new JLabel("<html><font size=+1>Twenties</font></html>");
        
        hundredsCountLbl = new JLabel(Integer.toString(myAtm.getHundreds()));
        fiftiesCountLbl = new JLabel(Integer.toString(myAtm.getFifties()));
        twentiesCountLbl = new JLabel(Integer.toString(myAtm.getTwenties()));
        
        hField = new JTextField("0");
        fField = new JTextField("0");
        tField = new JTextField("0");
        fileName = new JTextField("My ATM.txt");
        
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
        atmPanel.add(fileName);
        
        add(atmPanel);
        atmPanel.setVisible(true);
    }
    
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveBtn) {
                try {
                    myAtm.save(fileName.getText());
                } catch (FileNotFoundException ex) {
                    new FileNotFoundException(ex.getMessage());
                }
                
            }

            if (e.getSource() == loadBtn) {

                try {
                    myAtm.load(fileName.getText());
                    
                    hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                    fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                    twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
                    
                } catch (FileNotFoundException ex) {
                    new FileNotFoundException(ex.getMessage());
                }
                
            }
            if (e.getSource() == takeOutBtn) {
                try {
                        myAtm.takeOut(Integer.parseInt(hField.getText()), 
                                      Integer.parseInt(fField.getText()), 
                                      Integer.parseInt(tField.getText()));
                        hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                        fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                        twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
                        hField.setText("0");
                        fField.setText("0");
                        tField.setText("0");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            if (e.getSource() == putInBtn) {
                try {
                    myAtm.putIn(Integer.parseInt(hField.getText()), 
                                Integer.parseInt(fField.getText()), 
                                Integer.parseInt(tField.getText()));
                    hundredsCountLbl.setText(Integer.toString(myAtm.getHundreds()));
                    fiftiesCountLbl.setText(Integer.toString(myAtm.getFifties()));
                    twentiesCountLbl.setText(Integer.toString(myAtm.getTwenties()));
                    hField.setText("0");
                    fField.setText("0");
                    tField.setText("0");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        
    }
}
