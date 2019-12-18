package main.display;

import javax.swing.*;

public class AppView extends JFrame {
    private boolean paused = false;
    private JTextField worldsNUmber = new JTextField();
    JButton stop = new JButton("STOP");

    public AppView() {
        stop.addActionListener(e -> {
            if (paused) {
                paused = false;
            } else {
                paused = true;
            }
        });
    }
}
