package Program2Bankers;


import SourcePackages.Main;
import javax.swing.*;

public class ConfigDialog extends JDialog {
    private int numProcesses = -1;
    private int numResources = -1;
    private boolean submitted = false;

    public ConfigDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        setTitle("Configuration");
        setSize(300, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelProcesses = new JPanel();
        JLabel labelProcesses = new JLabel("Number of Processes:");
        JTextField txtProcesses = new JTextField(10);
        panelProcesses.add(labelProcesses);
        panelProcesses.add(txtProcesses);

        JPanel panelResources = new JPanel();
        JLabel labelResources = new JLabel("Number of Resources:");
        JTextField txtResources = new JTextField(10);
        panelResources.add(labelResources);
        panelResources.add(txtResources);

        JButton btnSubmit = new JButton("Next");
        btnSubmit.addActionListener(evt -> {
            try {
                numProcesses = Integer.parseInt(txtProcesses.getText());
                numResources = Integer.parseInt(txtResources.getText());
                if (numProcesses <= 0 || numResources <= 0) throw new NumberFormatException();
                submitted = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter positive integers.");
            }
        });

        add(panelProcesses);
        add(panelResources);
        add(btnSubmit);
        pack();
        setLocationRelativeTo(Main.frame);
    }

    public int getNumProcesses() {
        return numProcesses;
    }

    public int getNumResources() {
        return numResources;
    }

    public boolean isSubmitted() {
        return submitted;
    }
}