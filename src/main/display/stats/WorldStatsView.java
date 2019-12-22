package main.display.stats;

import main.logic.Observer;
import main.logic.stats.WorldStats;
import main.logic.map.WorldMap;
import main.save.StatsFileWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class WorldStatsView extends JPanel implements Observer {
    private WorldStats worldStats;

    private Chart ageChart = new Chart(10, Color.BLACK, "Average death age");
    private Chart energyChart = new Chart(10, Color.decode("#01a220"), "Average energy");
    private Chart childrenChart = new Chart(10, Color.RED, "Average children number");
    private Chart plantsChart = new Chart(10, Color.BLUE, "Plants number");
    private Chart animalsChart = new Chart(10, Color.magenta, "Animals number");
    private JTextField dominantGene = new JTextField();
    private JTextField dayField = new JTextField();
    private JTextField saveAfter = new JTextField();


    public WorldStatsView(WorldMap worldMap, WorldStats worldStats, StatsFileWriter fileWriter) {
        worldMap.addObserver(this);
        this.worldStats = worldStats;
        this.setPreferredSize(new Dimension(970, 100));
        this.setLayout(new GridLayout(1, 6));
        this.add(ageChart);
        this.add(energyChart);
        this.add(childrenChart);
        this.add(plantsChart);
        this.add(animalsChart);

        this.dominantGene.setBorder(new TitledBorder("Dominant gene"));
        this.dominantGene.setEditable(false);

        this.dayField.setBorder(new TitledBorder("Day:"));
        this.dayField.setEditable(false);

        this.saveAfter.setBorder(new TitledBorder("Save after:"));
        this.saveAfter.setText("n days");
        this.saveAfter.addActionListener(e -> fileWriter.setTimeout(
                Integer.parseInt(saveAfter.getText().trim())
        ));

        JPanel noChartStats = new JPanel();

        noChartStats.setLayout(new GridLayout(3, 1));
        noChartStats.add(dominantGene);
        noChartStats.add(dayField);
        noChartStats.add(saveAfter);
        this.add(noChartStats);
    }

    @Override
    public void change() {
        ageChart.addPoint(worldStats.getAvgDeathAge());
        energyChart.addPoint(worldStats.getAvgEnergy());
        childrenChart.addPoint(worldStats.getAvgChildren());
        plantsChart.addPoint(worldStats.getPlantsNumber());
        animalsChart.addPoint(worldStats.getAnimalNumber());
        dominantGene.setText(worldStats.getDominantGene().toString());
        dayField.setText(worldStats.getDay().toString());
    }
}
