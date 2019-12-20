package main.display;

import main.logic.World;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppView extends JFrame {
    /*
    private JTextField fileAddressField = new JTextField();
    private JButton selectButton = new JButton("SELECT");
     */
    private JTextField mapNumberField = new JTextField();
    private JButton createButton = new JButton("CREATE");
    private JButton stopButton = new JButton("START");
    private JButton exitBUtton = new JButton("EXIT");
    private final ExecutorService exec = Executors.newCachedThreadPool();
    private LinkedList<World> worlds = new LinkedList<>();
    private boolean paused = true;

    public AppView() {
        mapNumberField.setBorder(new TitledBorder("NUMBER OF MAPS:"));

        this.add(BorderLayout.NORTH, mapNumberField);

        stopButton.addActionListener(e -> {
            if (paused) {
                worlds.forEach(World::start);
                worlds.forEach(exec::execute);
                stopButton.setText("STOP");
            } else {
                worlds.forEach(World::pause);
                stopButton.setText("START");
            }
            paused = !paused;
        });

        createButton.addActionListener(e -> {
            try {
                int mapNumber = Integer.parseInt(mapNumberField.getText().trim());
                while (mapNumber-- > 0) {
                    worlds.add(new World());
                }
                worlds.forEach(exec::execute);
            } catch (NumberFormatException nfe) {
                mapNumberField.setText("PLEASE ENTER NUMBER OF MAPS");
            }

        });

        exitBUtton.addActionListener(e -> {
            exec.shutdownNow();
            this.dispose();
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(createButton);
        buttons.add(stopButton);
        buttons.add(exitBUtton);

        this.add(BorderLayout.SOUTH, buttons);
/*
        JPanel config = new JPanel();
        config.setLayout(new GridLayout(2,1));
        config.add(fileAddressField);
        config.add(selectButton);
        config.setBorder(new TitledBorder("CONFIG. FILE ADDRESS"));

        this.add(BorderLayout.CENTER, config);
*/
    }
}
