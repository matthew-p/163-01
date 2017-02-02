package atmPack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ATMMainPanel extends JPanel {
    private MyATMPanel atm1;
    private MyATMPanel atm2;
    private MyATMPanel atm3;
    
    private JButton suspend;
    private JPanel mainPanel;
    
    public ATMMainPanel() {
        setLayout(new GridLayout(3,4));
        
        atm1 = new MyATMPanel();
        atm2 = new MyATMPanel();
        atm3 = new MyATMPanel();
        atm1.setVisible(true);
        atm2.setVisible(true);
        atm3.setVisible(true);
        
        MainListener listener = new MainListener();
        
        suspend = new JButton("Suspend ATMs");
        suspend.addActionListener(listener);
        
        add(atm1);
        add(atm2);
        add(atm3);
        add(suspend);
        
        setVisible(true);
        
    }
    
    private class MainListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == suspend) {
                if (ATM.isSuspended()) {
                    ATM.suspend(false);
                    suspend.setText("Suspend ATMs");
                }
                else {
                    ATM.suspend(true);
                    suspend.setText("Activate ATMs");
                }

            } 
        }
    }
}
