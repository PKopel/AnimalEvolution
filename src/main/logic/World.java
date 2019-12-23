package main.logic;

import main.display.map.WorldView;
import main.logic.map.WorldMap;
import main.logic.stats.WorldStats;
import main.save.StatsFileWriter;

import javax.swing.*;

public class World implements Runnable {
    private static int counter = 0;
    private int id = ++counter;
    private volatile boolean paused = true;
    private int frameTime;
    private WorldMap map = new WorldMap();
    private WorldStats stats = new WorldStats(map);
    private StatsFileWriter writer = new StatsFileWriter(stats, id);
    private WorldView view = new WorldView(map, stats, writer);

    public World(int frameTime) {
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

    public void dispose(){
        this.paused = true;
        view.dispose();
    }

    public void run() {
        while (!Thread.interrupted() && !paused) {
            map.day();
            writer.update();
            try {
                Thread.sleep(frameTime);
            } catch (InterruptedException e) {
                view.dispose();
            }
        }
    }
}
