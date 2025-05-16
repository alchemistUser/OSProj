package Program2Bankers;

import SourcePackages.Main;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class BankersAlgorithm {

    private int[][] allocation;
    private int[][] max;
    private int[] available;
    private int n, m;
    private boolean[] finish;
    private int[][] need;

    public static DefaultTableModel model = Main.pnlProg2.model;

    public BankersAlgorithm(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;
        this.n = allocation.length;
        this.m = allocation[0].length;
        this.finish = new boolean[n];
        calculateNeed();
    }

    private void calculateNeed() {
        need = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public String findSafeSequence() {
        this.finish = new boolean[n]; // Reset finish array
        int[] work = Arrays.copyOf(available, m);
        Queue<Integer> safeSequence = new LinkedList<>();
        int count = 0;

        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i] && isLessOrEqual(need[i], work)) {
                    for (int j = 0; j < m; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    safeSequence.add(i);
                    count++;
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }

        if (count == n) {
            StringBuilder sb = new StringBuilder("Safe Sequence: ");
            while (!safeSequence.isEmpty()) {
                sb.append("P").append(safeSequence.poll()).append(" ");
            }
            return sb.toString().trim();
        } else {
            return "System is in an unsafe state.";
        }
    }

    private boolean isLessOrEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    public int[][] getNeedMatrix() {
        return need;
    }

    public static void openInputDialog(PnlProg2 panel) {
        ConfigDialog cd = new ConfigDialog(Main.frame, true);
        cd.setVisible(true);
        if (!cd.isSubmitted()) {
            return;
        }

        int numProcesses = cd.getNumProcesses();
        int numResources = cd.getNumResources();

        InputDialog id = new InputDialog(Main.frame, true, numProcesses, numResources);
        id.setVisible(true);

        if (id.isSubmitted()) {
            int[][] allocation = id.getAllocation();
            int[][] max = id.getMax();
            int[] available = id.getAvailable();

            BankersAlgorithm ba = new BankersAlgorithm(allocation, max, available);

            model.setRowCount(0); // Clear all rows

            for (int i = 0; i < numProcesses; i++) {
                model.addRow(new Object[]{
                    "P" + i,
                    Arrays.toString(allocation[i]),
                    Arrays.toString(max[i]),
                    Arrays.toString(ba.getNeedMatrix()[i])
                });
            }

            panel.jTextArea1.setText(ba.findSafeSequence());
        }
    }

    public static void clearApplicationData() {
        model.setRowCount(0); // Clear all rows

        Main.pnlProg2.jTextArea1.setText(""); // Clear result text
    }
}
