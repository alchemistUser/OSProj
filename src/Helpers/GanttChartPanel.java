package Helpers;

import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

public class GanttChartPanel extends JPanel {
    private List<Process> processes;

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (processes == null || processes.isEmpty()) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int y = 30;
        int height = 40;
        int padding = 5;

        // Calculate total execution time (makespan)
        int makespan = 0;
        for (Process p : processes) {
            makespan += p.burstTime;
        }

        // Get available width and calculate scale
        int panelWidth = getWidth() - 20; // leave some margin
        double scale = (double) panelWidth / makespan;

        int x = 10;

        // Draw each process block
        for (Process p : processes) {
            int width = (int) (p.burstTime * scale);

            // Set color for this process
            g2d.setColor(getColorForId(p.id));
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, width, height);

            // Centered label
            String label = "P" + p.id;
            FontMetrics fm = g.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelX = x + (width - labelWidth) / 2;
            int labelY = y + (height + fm.getAscent()) / 2;

            g2d.setColor(Color.WHITE);
            g2d.drawString(label, labelX, labelY);
            g2d.setColor(Color.BLACK);

            // Time label below the bar
            g2d.drawString(String.valueOf(p.completionTime), x, y + height + 15);

            x += width;
        }

        // Draw start time (0)
        g2d.drawString("0", 10, y + height + 15);
    }

    private Color getColorForId(int id) {
        switch (id % 6) {
            case 0: return new Color(66, 133, 244); // blue
            case 1: return new Color(52, 168, 83);  // green
            case 2: return new Color(251, 188, 4);  // yellow
            case 3: return new Color(219, 68, 55);  // red
            case 4: return new Color(116, 66, 200); // purple
            case 5: return new Color(0, 172, 133);  // teal
            default: return Color.GRAY;
        }
    }
}