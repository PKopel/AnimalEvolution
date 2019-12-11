package main;

import main.display.Display;
import main.logic.World;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        World world = new World();
        Display display = new Display(world.getMap());
        display.setTitle("Animal Evolution");
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(1000,1000);
        display.setVisible(true);
        world.run();
    }
}
