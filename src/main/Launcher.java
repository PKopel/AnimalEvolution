package main;

import main.display.AppView;
import javax.swing.*;

public class Launcher {
    public static int initialAnimals = 20;
    public static int initialEnergy = 40;
    public static int energyFromPlant = 50;
    public static int width = 20;
    public static int height = 20;
    public static int jungleWidth = 10;
    public static int jungleHeight = 10;

    public static void main(String[] args) {
        AppView appView = new AppView();
        appView.setTitle("Animal Evolution");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(400, 150);
        appView.setVisible(true);
    }
}
