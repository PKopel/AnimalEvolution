package main.logic;

import main.display.WorldView;

import javax.swing.*;

public class World implements Runnable {
    private static int counter = 0;
    private volatile boolean paused = true;
    private int frameTime;
    private WorldMap map = new WorldMap();
    private WorldView view = new WorldView(map, this);

    public World(int frameTime) {
        int id = ++counter;
        this.frameTime = frameTime;
        view.setTitle("Map no. " + id);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.setSize(970, 1000);
        view.setVisible(true);
    }

    public void pause() {
        this.paused = true;
    }

    public void start() {
        this.paused = false;
    }

    public void run() {
        while (!Thread.interrupted() && !map.isEmpty() && !paused) {
            map.day();

            try {
                Thread.sleep(frameTime);
            } catch (InterruptedException e) {
                view.dispose();
            }
        }
    }
}
