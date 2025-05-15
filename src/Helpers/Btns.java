package Helpers;

import SourcePackages.Main;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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


public class Btns {

    
    
    Btns() {
        System.out.println("hi");
    }

    public static void back() {
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

    public static void runFCFS(DefaultTableModel model) {

        if (Main.processes.isEmpty()) {
            JOptionPane.showMessageDialog(Main.frame, "No processes added.");
            return;
        }

        int currentTime = 0;
        double totalTAT = 0, totalWT = 0;

        // Sort by arrival time
        Main.processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        for (int i = 0; i < Main.processes.size(); i++) {
            Process p = Main.processes.get(i);

            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            p.completionTime = currentTime + p.burstTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;

            totalTAT += p.turnaroundTime;
            totalWT += p.waitingTime;

            currentTime = p.completionTime;
        }

        // Update table with results
        for (int i = 0; i < Main.processes.size(); i++) {
            Process p = Main.processes.get(i);
            model.setValueAt(p.completionTime, i, 3);
            model.setValueAt(p.turnaroundTime, i, 4);
            model.setValueAt(p.waitingTime, i, 5);
        }

        // Show averages
        double avgTAT = totalTAT / Main.processes.size();
        double avgWT = totalWT / Main.processes.size();

        JOptionPane.showMessageDialog(Main.frame,
                String.format("Average Turnaround Time: %.2f\nAverage Waiting Time: %.2f",
                        avgTAT, avgWT));
        
        clearTextFields(Main.pnlProg1);
    }
}
