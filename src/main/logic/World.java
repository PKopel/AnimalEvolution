package main.logic;

import main.display.WorldView;

import javax.swing.*;

public class World implements Runnable {
    private static int counter = 0;
    private final int id = ++counter;
    private WorldMap map = new WorldMap();

    public void pause() {
        System.out.println("pausing");
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        WorldView worldView = new WorldView(map, this);
        worldView.setTitle("Map no. " + id);
        worldView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        worldView.setSize(970, 1000);
        worldView.setVisible(true);
        //int day = 1;
        while (!Thread.interrupted() && !map.isEmpty()) {
            //System.out.println(day++);
            //System.out.println(map);
            map.day();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
