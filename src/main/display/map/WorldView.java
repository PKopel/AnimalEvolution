package main.display.map;

import main.display.stats.AnimalStatsView;
import main.display.stats.WorldStatsView;
import main.logic.stats.WorldStats;
import main.logic.map.WorldMap;
import main.save.StatsFileWriter;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class WorldView extends JFrame {
    private LinkedList<AnimalStatsView> animalStatsViews = new LinkedList<>();

    public WorldView(WorldMap worldMap, WorldStats worldStats, StatsFileWriter fileWriter) {
        MapView mapView = new MapView(worldMap, this);
        WorldStatsView worldStatsView = new WorldStatsView(worldMap, worldStats, fileWriter);
        this.add(mapView);
        worldStatsView.setMinimumSize(new Dimension(100, 100));
        this.add(BorderLayout.SOUTH, worldStatsView);
    }

    public void addAnimalStatsView(AnimalStatsView animalStatsView){
        animalStatsViews.add(animalStatsView);
    }

    @Override
    public void dispose() {
        animalStatsViews.forEach(AnimalStatsView::dispose);
        super.dispose();
    }
}
