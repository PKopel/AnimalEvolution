package main.display;

import main.logic.Stats;
import main.logic.World;
import main.logic.WorldMap;

import javax.swing.*;
import java.awt.*;

public class WorldView extends JFrame {
    public WorldView(WorldMap worldMap, World world) {
        MapView mapView = new MapView(worldMap);
        StatsView statsView = new StatsView(worldMap, new Stats(worldMap));
        this.add(mapView);
        statsView.setMinimumSize(new Dimension(100, 100));
        this.add(BorderLayout.SOUTH, statsView);
    }
}
