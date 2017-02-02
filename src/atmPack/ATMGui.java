package atmPack;
import javax.swing.JFrame;

public class ATMGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new ATMMainPanel());
        
        frame.pack();
        frame.setVisible(true);
    }
}
