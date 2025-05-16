package Program2Bankers;


import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class InputDialog extends JDialog {
    private boolean submitted = false;
    private int numProcesses, numResources;
    private JPanel panelAllocation, panelMax, panelAvailable;
    private JTextField[][] tfAllocation, tfMax;
    private JTextField[] tfAvailable;

    public InputDialog(java.awt.Frame parent, boolean modal, int numProcesses, int numResources) {
        super(parent, modal);
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        initComponents();
    }

    private void initComponents() {
        setTitle("Input Process Data");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Main Panel (will be wrapped in JScrollPane)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Allocation Panel
        panelAllocation = new JPanel();
        panelAllocation.setBorder(BorderFactory.createTitledBorder("Allocation"));
        generateAllocationFields();
        JScrollPane scrollAllocation = new JScrollPane(panelAllocation);

        // Max Panel
        panelMax = new JPanel();
        panelMax.setBorder(BorderFactory.createTitledBorder("Max"));
        generateMaxFields();
        JScrollPane scrollMax = new JScrollPane(panelMax);

        // Available Panel
        panelAvailable = new JPanel();
        panelAvailable.setBorder(BorderFactory.createTitledBorder("Available"));
        generateAvailableFields();
        JScrollPane scrollAvailable = new JScrollPane(panelAvailable);

        // Buttons
        JButton btnAutoGenerate = new JButton("Auto-Generate Values");
        btnAutoGenerate.addActionListener(evt -> autoGenerateValues());

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(evt -> {
            submitted = true;
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAutoGenerate);
        buttonPanel.add(btnSubmit);

        // Add components to main panel
        mainPanel.add(scrollAllocation);
        mainPanel.add(scrollMax);
        mainPanel.add(scrollAvailable);
        mainPanel.add(buttonPanel);

        // Set max size for dialog
        mainPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 720));
        mainPanel.setPreferredSize(new Dimension(700, 720));

        // Wrap everything in a JScrollPane
        JScrollPane outerScrollPane = new JScrollPane(mainPanel);
        outerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(outerScrollPane, BorderLayout.CENTER);

        pack();
        setSize(new Dimension(750, 720)); // Width includes scrollbar space
        setLocationRelativeTo(getParent());
    }

    private void generateAllocationFields() {
        panelAllocation.setLayout(new GridLayout(numProcesses, numResources + 1));
        panelAllocation.removeAll();

        tfAllocation = new JTextField[numProcesses][numResources];
        for (int i = 0; i < numProcesses; i++) {
            panelAllocation.add(new JLabel("P" + i));
            for (int j = 0; j < numResources; j++) {
                tfAllocation[i][j] = new JTextField(3);
                panelAllocation.add(tfAllocation[i][j]);
            }
        }
    }

    private void generateMaxFields() {
        panelMax.setLayout(new GridLayout(numProcesses, numResources + 1));
        panelMax.removeAll();

        tfMax = new JTextField[numProcesses][numResources];
        for (int i = 0; i < numProcesses; i++) {
            panelMax.add(new JLabel("P" + i));
            for (int j = 0; j < numResources; j++) {
                tfMax[i][j] = new JTextField(3);
                panelMax.add(tfMax[i][j]);
            }
        }
    }

    private void generateAvailableFields() {
        panelAvailable.setLayout(new GridLayout(1, numResources));
        panelAvailable.removeAll();

        tfAvailable = new JTextField[numResources];
        for (int j = 0; j < numResources; j++) {
            tfAvailable[j] = new JTextField(3);
            panelAvailable.add(tfAvailable[j]);
        }
    }

    private void autoGenerateValues() {
        Random rand = new Random();
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                int alloc = rand.nextInt(5); // Allocation: 0–4
                int max = alloc + rand.nextInt(5); // Max >= Allocation
                tfAllocation[i][j].setText(String.valueOf(alloc));
                tfMax[i][j].setText(String.valueOf(max));
            }
        }

        for (int j = 0; j < numResources; j++) {
            tfAvailable[j].setText(String.valueOf(rand.nextInt(10)));
        }
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public int[][] getAllocation() {
        int[][] alloc = new int[numProcesses][numResources];
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                alloc[i][j] = Integer.parseInt(tfAllocation[i][j].getText());
            }
        }
        return alloc;
    }

    public int[][] getMax() {
        int[][] max = new int[numProcesses][numResources];
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                max[i][j] = Integer.parseInt(tfMax[i][j].getText());
            }
        }
        return max;
    }

    public int[] getAvailable() {
        int[] avail = new int[numResources];
        for (int j = 0; j < numResources; j++) {
            avail[j] = Integer.parseInt(tfAvailable[j].getText());
        }
        return avail;
    }
}