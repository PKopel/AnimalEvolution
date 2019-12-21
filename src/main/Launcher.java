package main;

import main.parameters.ParametersFileReader;
import main.display.AppView;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        ParametersFileReader.readConfig();
        AppView appView = new AppView();
        appView.setTitle("Animal Evolution");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(400, 180);
        appView.setVisible(true);
    }
}
