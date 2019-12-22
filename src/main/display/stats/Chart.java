package main.display.stats;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class Chart extends JPanel {
    private LinkedList<Double> points = new LinkedList<>();
    private Color color;
    private int length;

    public Chart(int length, Color color, String title) {
        this.color = color;
        this.length = length;
        this.setBorder(new TitledBorder(title));
        for (int i = 0; i < length; i++) {
            points.push(0d);
        }
    }

    public void addPoint(double point) {
        this.points.add(point);
        this.points.pop();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int step = this.getWidth() / this.length;
        double scale = (this.getHeight() - 10) / Collections.max(points);
        g.setColor(color);
        for (int i = 0; i < points.size() - 1; i++) {
            g.drawLine(
                    i * step,
                    getHeight() - (int) (points.get(i) * scale) + 5,
                    (i + 1) * step,
                    getHeight() - (int) (points.get(i + 1) * scale) + 5
            );
        }
        char[] last = points.getLast().toString().toCharArray();
        g.drawChars(last, 0, last.length, (length - 1) * step,
                this.getHeight() - (int) (points.getLast() * scale) + 10);
    }
}
