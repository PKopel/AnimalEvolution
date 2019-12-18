package main.display;

import main.logic.World;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppView extends JFrame {
    private boolean paused = false;
    private JTextField fileAddressField = new JTextField();
    private JTextField mapNumberField = new JTextField();
    private JButton createButton = new JButton("CREATE");
    private JButton stopButton = new JButton("STOP");
    private JButton exitBUtton = new JButton("EXIT");
    private ExecutorService exec = Executors.newCachedThreadPool();

    public AppView() {
        mapNumberField.setBorder(new TitledBorder("NUMBER OF MAPS:"));
        fileAddressField.setBorder(new TitledBorder("CONFIG. FILE ADDRESS"));

        stopButton.addActionListener(e -> {
            if (paused) {
                paused = false;
            } else {
                paused = true;
            }
        });

        createButton.addActionListener(e -> {
            try {
                int mapNumber = Integer.parseInt(mapNumberField.getText().trim());
                while (mapNumber-- > 0) {
                    exec.execute(new World());
                }
            } catch (NumberFormatException nfe) {
                mapNumberField.setText("PLEASE ENTER NUMBER OF MAPS");
            }

        });

        exitBUtton.addActionListener(e -> {
            this.dispose();
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(createButton);
        buttons.add(stopButton);
        buttons.add(exitBUtton);

        this.add(BorderLayout.SOUTH, buttons);
        this.add(BorderLayout.CENTER, fileAddressField);
        this.add(BorderLayout.NORTH, mapNumberField);
    }
}
