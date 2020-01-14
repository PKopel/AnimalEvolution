package main.display;

import main.logic.World;
import main.parameters.ParametersFileReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppView extends JFrame {

    private JTextField frameTimeField = new JTextField();
    private JTextField mapNumberField = new JTextField();
    private JButton readParamButton = new JButton("READ PARAMETERS");
    private JButton createButton = new JButton("CREATE");
    private JButton stopButton = new JButton("START");
    private JButton exitButton = new JButton("EXIT");
    private final ExecutorService exec = Executors.newCachedThreadPool();
    private LinkedList<World> worlds = new LinkedList<>();
    private boolean paused = true;

    public AppView() {
        this.setLayout(new GridLayout(4, 1));

        mapNumberField.setBorder(new TitledBorder("NUMBER OF MAPS:"));
        mapNumberField.setText("1");

        frameTimeField.setBorder(new TitledBorder("FRAME DURATION (ms):"));
        frameTimeField.setText("1000");

        readParamButton.addActionListener(e ->{
            ParametersFileReader.readConfig();
        });

        createButton.addActionListener(e -> {
            try {
                int frameTime = Integer.parseInt(frameTimeField.getText().trim());
                int mapNumber = Integer.parseInt(mapNumberField.getText().trim());
                while (mapNumber-- > 0) {
                    worlds.add(new World(frameTime));
                }
                worlds.forEach(exec::submit);
            } catch (NumberFormatException nfe) {
                mapNumberField.setText("PLEASE ENTER NUMBER OF MAPS");
            }

        });

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

        exitButton.addActionListener(e -> {
            worlds.forEach(World::dispose);
            exec.shutdownNow();
            this.dispose();
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 4));
        buttons.add(readParamButton);
        buttons.add(createButton);
        buttons.add(stopButton);
        buttons.add(exitButton);

        this.add(mapNumberField);
        this.add(frameTimeField);
        this.add(readParamButton);
        this.add(buttons);
    }
}
