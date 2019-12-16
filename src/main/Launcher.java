package main;

import main.display.AppView;
import main.logic.World;

import javax.swing.*;

public class Launcher {
    public static int initialAnimals = 20;
    public static int initialEnergy = 40;
    public static int energyFromPlant = 50;
    public static int width = 20;
    public static int height = 20;
    public static int jungleWidth = 10;
    public static int jungleHeight = 10;
    private static World world = new World();

    public static void run() {
        int day = 1;
        while (!world.isEmpty()){
            System.out.println(day++);
            System.out.println(world);
            world.day();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AppView appView = new AppView(world);
        appView.setTitle("Animal Evolution");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(970,1000);
        appView.setVisible(true);
        run();
    }
}
