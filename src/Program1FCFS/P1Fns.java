//this is helper class
package Program1FCFS;

import SourcePackages.Main;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class P1Fns {

    static ArrayList<Program1FCFS.Process> processes = new ArrayList<>();
    static PnlProg1 panel1 = Main.pnlProg1;
    
    public static void clear(){
        reset();
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
    }
    
    public static void reset(){
        for (Component comp : panel1.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            }
        }
        panel1.model.setRowCount(0);
        processes.clear();
    }

    public static void add(JTextField tf1, JTextField tf2, JTextField tf3, DefaultTableModel model) {
        try {
            int id = Integer.parseInt(tf1.getText());
            int burst = Integer.parseInt(tf2.getText());
            int arrival = Integer.parseInt(tf3.getText());

            // Create new Process
            processes.add(new Process(id, burst, arrival));

            // Add row to table without scheduling results yet
            model.addRow(new Object[]{id, burst, arrival, "", "", ""});

            tf1.setText("");
            tf2.setText("");
            tf3.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Main.frame, "Please enter valid numbers.");
        }
    }

    public static void runFCFS(DefaultTableModel model, JTextField tf1, JTextField tf2, JTextField tf3, JPanel panel) {

        if (processes.isEmpty()) {
            JOptionPane.showMessageDialog(Main.frame, "No processes added.");
            return;
        }

        int currentTime = 0;
        double totalTAT = 0, totalWT = 0;

        // Sort by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);

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
        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);
            model.setValueAt(p.completionTime, i, 3);
            model.setValueAt(p.turnaroundTime, i, 4);
            model.setValueAt(p.waitingTime, i, 5);
        }

        // Show averages
        double avgTAT = totalTAT / processes.size();
        double avgWT = totalWT / processes.size();

        JOptionPane.showMessageDialog(Main.frame,
                String.format("Average Turnaround Time: %.2f\nAverage Waiting Time: %.2f",
                        avgTAT, avgWT));

        tf1.setText("");
        tf2.setText("");
        tf3.setText("");

        //clear panel first        
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        // grantt
        GanttChartPanel ganttPanel = new GanttChartPanel();
        ganttPanel.setBounds(0, 0, 1004, 100);
        panel.add(ganttPanel);
        panel.revalidate();
        panel.repaint();

        ganttPanel.setProcesses(processes);
    }

}
