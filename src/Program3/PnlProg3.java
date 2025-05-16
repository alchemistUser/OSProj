package Program3;

import javax.swing.*;
import javax.swing.table.*;

import Program2Bankers.BankersAlgorithm;
import SourcePackages.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class PnlProg3 extends JPanel {
    private JTextArea inputField;
    private JSpinner frameSpinner;
    private JTable resultTable;
    private JLabel hitRateLabel, missRateLabel, faultsLabel;

    public PnlProg3() {
        setLayout(new BorderLayout(20, 20));
        initTopBar();         // ⬅ New top bar with back button
        initMainLayout();     // main simulation UI
    }

    private void initTopBar() {
        JButton backButton = new JButton("⬅");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel title = new JLabel("FIFO Page Replacement");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(Color.WHITE);
        topBar.add(backButton);
        topBar.add(title);

        add(topBar, BorderLayout.NORTH);

        // Replace this with your actual navigation logic
        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            Main.frame.back();        
            BankersAlgorithm.clearApplicationData();
        });
    }

    private void initMainLayout() {
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);

        // === LEFT PANEL (Input) ===
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Configuration"));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(250, 200));

        JLabel inputLabel = new JLabel("Reference String:");
        inputLabel.setFont(labelFont);

        inputField = new JTextArea(5, 20);
        inputField.setFont(fieldFont);
        inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(inputField);
        inputScroll.setPreferredSize(new Dimension(220, 100));

        JLabel frameLabel = new JLabel("Number of Frames:");
        frameLabel.setFont(labelFont);

        frameSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        frameSpinner.setFont(fieldFont);

        JButton runButton = new JButton("Run Simulation");
        runButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        int gap = 10;
        leftPanel.add(inputLabel);
        leftPanel.add(Box.createVerticalStrut(gap));
        leftPanel.add(inputScroll);
        leftPanel.add(Box.createVerticalStrut(gap));
        leftPanel.add(frameLabel);
        leftPanel.add(Box.createVerticalStrut(gap));
        leftPanel.add(frameSpinner);
        leftPanel.add(Box.createVerticalStrut(gap * 2));
        leftPanel.add(runButton);

        // === CENTER PANEL (Table + Summary) ===
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(Color.WHITE);

        resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("FIFO Simulation Table"));

        JPanel summaryPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        summaryPanel.setBackground(Color.WHITE);

        hitRateLabel = new JLabel();
        missRateLabel = new JLabel();
        faultsLabel = new JLabel();

        hitRateLabel.setFont(labelFont);
        missRateLabel.setFont(labelFont);
        faultsLabel.setFont(labelFont);

        summaryPanel.add(hitRateLabel);
        summaryPanel.add(missRateLabel);
        summaryPanel.add(faultsLabel);

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(summaryPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        runButton.addActionListener((ActionEvent e) -> runSimulation());
    }

    private void runSimulation() {
        String input = inputField.getText().trim();
        int frameCount = (Integer) frameSpinner.getValue();

        int[] pages;
        try {
            String[] tokens = input.split("[\\s\\n]+");
            pages = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid page input.");
            return;
        }

        FIFO fifo = new FIFO();
        fifo.execute(pages, frameCount);
        PageReplacementResult result = fifo.getResult();

        if (result == null) {
            JOptionPane.showMessageDialog(this, "Failed to get result.");
            return;
        }

        String[] columnNames = new String[frameCount + 3];
        columnNames[0] = "Ref";
        for (int i = 0; i < frameCount; i++) columnNames[i + 1] = "Frame " + (i + 1);
        columnNames[frameCount + 1] = "Hit";
        columnNames[frameCount + 2] = "Fault Count";

        Object[][] data = new Object[result.referenceList.size()][columnNames.length];
        int faultCounter = 0;

        for (int i = 0; i < result.referenceList.size(); i++) {
            int page = result.referenceList.get(i);
            List<Integer> frame = result.frameTable.get(i);
            boolean isHit = result.hitList.get(i);

            data[i][0] = page;
            for (int j = 0; j < frameCount; j++) {
                data[i][j + 1] = j < frame.size() ? frame.get(j) : "";
            }
            data[i][frameCount + 1] = isHit ? "✅" : "❌";
            if (!isHit) faultCounter++;
            data[i][frameCount + 2] = faultCounter;
        }

        resultTable.setModel(new DefaultTableModel(data, columnNames) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });

        JTableHeader header = resultTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        resultTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(CENTER);
                if (column == 0) {
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    c.setBackground(new Color(232, 240, 255));
                } else if (column > 0 && column <= frameCount && !result.hitList.get(row)) {
                    c.setBackground(new Color(255, 204, 204));
                } else {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                }
                return c;
            }
        });

        int total = result.referenceList.size();
        int faults = result.pageFaults;
        faultsLabel.setText("Total Page Faults: " + faults);
        hitRateLabel.setText(String.format("Hit rate: %.2f", (total - faults) / (double) total));
        missRateLabel.setText(String.format("Miss rate: %.2f", faults / (double) total));
    }
}
