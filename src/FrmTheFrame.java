
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
public class FrmTheFrame extends JFrame {

    PnlMainMenu pnlmainmenu = new PnlMainMenu();
    
    JPanel panel = new JPanel() {
        {
            setBackground(Color.WHITE);
            setBounds(0, 0, 1280, 720);
            setLayout(null);
        }
    };

    FrmTheFrame() {
        super("OS Final Project");
        setSize(1295, 758);
        setResizable(false);
        setLayout(null);

        add(panel);
        startup();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void visible() {
        this.setVisible(true);
    }

    void clrPnl() {
        panel.removeAll();

        panel.revalidate();
        panel.repaint();
    }

    void setPanel(JPanel pnlChild) {
        clrPnl();
        pnlChild.setBounds(0, 0, 1280, 720);
        panel.add(pnlChild);
    }
    
    void startup(){        
        clrPnl();
        setPanel(pnlmainmenu);
    }

}
