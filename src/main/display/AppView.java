package main.display;

import main.logic.Stats;
import main.logic.World;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AppView extends JFrame{
    private WorldView worldView;
    private StatsView statsView;

    public AppView(World world){
        this.worldView = new WorldView(world);
        this.statsView = new StatsView(world, new Stats(world));
        this.add(worldView);
        statsView.setMinimumSize(new Dimension(100,100));
        this.add(BorderLayout.SOUTH, statsView);
    }
}
