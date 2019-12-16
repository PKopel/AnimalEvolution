package main.display;

import main.WorldObserver;
import main.logic.Animal;
import main.logic.Genes;
import main.logic.Stats;
import main.logic.World;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class StatsView extends JPanel implements WorldObserver {
    private World world;
    private Stats stats;

    private Chart ageChart = new Chart(10, Color.white, "average age");
    private Chart energyChart = new Chart(10, Color.GREEN, "average energy");
    private Chart childrenChart = new Chart(10, Color.RED, "average children number");
    private Chart plantsChart = new Chart(10, Color.BLUE, "plants number");
    private Chart animalsChart = new Chart(10, Color.CYAN, "animals number");


    public StatsView(World world, Stats stats) {
        this.world = world;
        world.addObserver(this);
        this.stats = stats;
        this.setPreferredSize(new Dimension(970, 100));
        this.setLayout(new GridLayout(1,6));
        this.add(ageChart);
        this.add(energyChart);
        this.add(childrenChart);
        this.add(plantsChart);
        this.add(animalsChart);
    }

    @Override
    public void worldChanged() {
        ageChart.addPoint(stats.avgAge());
        energyChart.addPoint(stats.avgEnergy());
        childrenChart.addPoint(stats.avgChildren());
        plantsChart.addPoint(stats.plantsNumber());
        animalsChart.addPoint(stats.animalNumber());
    }
}
