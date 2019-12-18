package main;

import main.logic.World;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static int initialAnimals = 20;
    public static int initialEnergy = 40;
    public static int energyFromPlant = 50;
    public static int width = 20;
    public static int height = 20;
    public static int jungleWidth = 10;
    public static int jungleHeight = 10;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new World());
        exec.execute(new World());
        exec.execute(new World());
        exec.execute(new World());

    }
}
