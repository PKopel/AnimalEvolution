package main.save;

import main.logic.animal.Genes;
import main.logic.stats.WorldStats;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class StatsFileWriter {
    private WorldStats stats;
    private int id;
    private int timeout = -1;

    private int base = 0;
    private double avgChildren = 0;
    private double avgAnimalNumber = 0;
    private double avgDeathAge = 0;
    private double avgEnergy = 0;
    private double avgPlantsNumber;
    private LinkedList<Genes> dominantGenes = new LinkedList<>();

    public StatsFileWriter(WorldStats stats, int id) {
        this.stats = stats;
        this.id = id;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
        this.base = timeout;
    }

    private void apppendStatistic(StringBuilder statistics, String label, double data) {
        statistics.append(label);
        statistics.append(data / base);
        statistics.append("\n");
    }

    public void update() {
        if (timeout > 0) {
            avgAnimalNumber += stats.getAnimalNumber();
            avgChildren += stats.getAvgChildren();
            avgDeathAge += stats.getAvgDeathAge();
            avgEnergy += stats.getAvgEnergy();
            avgPlantsNumber += stats.getPlantsNumber();
            dominantGenes.add(stats.getDominantGene());
            timeout--;
        } else if (timeout == 0) {
            StringBuilder statistics = new StringBuilder();

            apppendStatistic(statistics, "average number of children: ", avgChildren);

            apppendStatistic(statistics, "average energy: ", avgEnergy);

            apppendStatistic(statistics, "average death age: ", avgDeathAge);

            apppendStatistic(statistics, "average number of animals: ", avgAnimalNumber);

            apppendStatistic(statistics, "average number of plants: ", avgPlantsNumber);

            statistics.append("dominant genes: ");
            statistics.append(dominantGenes.toString());

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("world_" + id + "_stats.txt"));
                writer.write(statistics.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            timeout = -1;
            base = 0;
            avgPlantsNumber = 0;
            avgAnimalNumber = 0;
            avgEnergy = 0;
            avgDeathAge = 0;
            avgChildren = 0;
            dominantGenes.clear();
        }
    }
}
