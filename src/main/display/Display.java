package main.display;

import main.logic.Map;

import javax.swing.*;

public class Display extends JFrame implements MapObserver{
    private MapDisplay mapDisplay;

    public Display(Map map){
        this.mapDisplay = new MapDisplay(map);
        map.addObserver(this);
        this.add(mapDisplay);
    }

    @Override
    public void mapChanged() {
        mapDisplay.repaint();
    }
}
