package SourcePackages;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
        // program 1
        Main.pnlProg1.model.setRowCount(0);
        Main.processes.clear();
        Main.pnlProg1.jPanel1.removeAll();
        Main.pnlProg1.jPanel1.revalidate();
        Main.pnlProg1.jPanel1.repaint();
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

}
