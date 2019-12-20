package main.logic;

import main.display.WorldView;

import javax.swing.*;

public class World implements Runnable {
    private static int counter = 0;
    private final int id = ++counter;
    private volatile boolean paused = true;
    private WorldMap map = new WorldMap();
    private WorldView view = new WorldView(map, this);

    public World() {
        view.setTitle("Map no. " + id);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.setSize(970, 1000);
        view.setVisible(true);
    }

    public void pause(){
        this.paused = true;
    }

    public void start(){
        this.paused = false;
    }

    public void run() {
        //int day = 1;
        while (!Thread.interrupted() && !map.isEmpty() && !paused) {
            //System.out.println(day++);
            //System.out.println(map);
            map.day();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                view.dispose();
            }
        }
    }
}
