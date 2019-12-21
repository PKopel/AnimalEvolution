package main.display;

import main.logic.stats.WorldStats;
import main.logic.map.WorldMap;

import javax.swing.*;
import java.awt.*;

public class WorldView extends JFrame {
    public WorldView(WorldMap worldMap, WorldStats worldStats) {
        MapView mapView = new MapView(worldMap);
        WorldStatsView worldStatsView = new WorldStatsView(worldMap, worldStats);
        this.add(mapView);
        worldStatsView.setMinimumSize(new Dimension(100, 100));
        this.add(BorderLayout.SOUTH, worldStatsView);
    }
}
