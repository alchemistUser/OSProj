
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Pololoers
 */
public class TheFrame extends JFrame {

    JPanel panel = new JPanel() {
        {
            setBackground(Color.WHITE);
            setBounds(0, 0, 1280, 720);
            setLayout(null);
        }
    };

    TheFrame() {
        super("OS Final Project");
        setSize(1295, 758);
        setLayout(null);

        add(panel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void clrPnl() {
        panel.removeAll();

        panel.revalidate();
        panel.repaint();
    }
    
    void setPanel(JPanel pnlChild){
        clrPnl();
        panel.add(pnlChild);
    }
}
