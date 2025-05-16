package SourcePackages;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    public void visible() {
        this.setVisible(true);
    }

    void clrPnl() {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                clearInputsInPanel((JPanel) comp);
            }
        }
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    void clearInputsInPanel(JPanel pnl) {
        // clear textfield
        for (Component comp : pnl.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            }
        }
    }

    public void setPanel(JPanel pnlChild) {
        clrPnl();
        pnlChild.setBounds(0, 0, 1280, 720);
        panel.add(pnlChild);
    }

    public void startup() {
        clrPnl();
        setPanel(pnlmainmenu);
    }

    public void back() {
        Main.frame.startup();
    }

    public static void openProg1() {
        Main.frame.setPanel(Main.pnlProg1);
    }

    public static void openProg2() {
        Main.frame.setPanel(Main.pnlProg2);
    }

    public static void openProg3() {
        Main.frame.setPanel(Main.pnlProg3);
    }

    public static void clearTextFields(JPanel pnl) {
        for (Component comp : pnl.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            }
        }
    }
}
