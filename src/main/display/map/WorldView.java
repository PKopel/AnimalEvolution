package main.display.map;

import main.display.stats.WorldStatsView;
import main.logic.stats.WorldStats;
import main.logic.map.WorldMap;
import main.save.StatsFileWriter;

import javax.swing.*;
import java.awt.*;

public class WorldView extends JFrame {
    public WorldView(WorldMap worldMap, WorldStats worldStats, StatsFileWriter fileWriter) {
        MapView mapView = new MapView(worldMap);
        WorldStatsView worldStatsView = new WorldStatsView(worldMap, worldStats, fileWriter);
        this.add(mapView);
        worldStatsView.setMinimumSize(new Dimension(100, 100));
        this.add(BorderLayout.SOUTH, worldStatsView);
    }
}
