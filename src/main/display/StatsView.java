package main.display;

import main.WorldObserver;
import main.logic.Stats;
import main.logic.WorldMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StatsView extends JPanel implements WorldObserver {
    private Stats stats;

    private Chart ageChart = new Chart(10, Color.BLACK, "average age");
    private Chart energyChart = new Chart(10, Color.GREEN, "average energy");
    private Chart childrenChart = new Chart(10, Color.RED, "average children number");
    private Chart plantsChart = new Chart(10, Color.BLUE, "plants number");
    private Chart animalsChart = new Chart(10, Color.magenta, "animals number");
    private JTextField dominantGene = new JTextField();


    public StatsView(WorldMap worldMap, Stats stats) {
        worldMap.addObserver(this);
        this.stats = stats;
        this.setPreferredSize(new Dimension(970, 100));
        this.setLayout(new GridLayout(1, 6));
        this.add(ageChart);
        this.add(energyChart);
        this.add(childrenChart);
        this.add(plantsChart);
        this.add(animalsChart);
        dominantGene.setBorder(new TitledBorder("dominant gene"));
        dominantGene.setEditable(false);
        this.add(BorderLayout.SOUTH, dominantGene);
    }

    @Override
    public void worldChanged() {
        ageChart.addPoint(stats.avgAge());
        energyChart.addPoint(stats.avgEnergy());
        childrenChart.addPoint(stats.avgChildren());
        plantsChart.addPoint(stats.plantsNumber());
        animalsChart.addPoint(stats.animalNumber());
        dominantGene.setText(stats.dominantGene().toString());
    }
}
