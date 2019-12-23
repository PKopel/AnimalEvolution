package main.logic.stats;

import main.logic.animal.Animal;
import main.logic.map.Field;
import main.logic.animal.Genes;
import main.logic.map.WorldMap;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class WorldStats {
    private WorldMap worldMap;

    public WorldStats(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private double getAvgStat(ToDoubleFunction<Animal> stat) {
        return worldMap.getAnimals().stream()
                .mapToDouble(stat)
                .average()
                .orElse(0d);
    }

    public double getAvgDeathAge() {
        return this.worldMap.getAvgDeathAge();
    }

    public double getAvgEnergy() {
        return this.getAvgStat(Animal::getEnergy);
    }

    public double getAvgChildren() {
        return this.getAvgStat(Animal::getChildrenNumber);
    }

    public int getAnimalNumber() {
        return this.worldMap.getAnimals().size();
    }

    public int getPlantsNumber() {
        return (int) this.worldMap.getFields().values()
                .stream()
                .filter(Field::hasPlant)
                .count();
    }

    public Integer getDay() {
        return worldMap.getDay();
    }

    public Genes getDominantGene() {
        HashMap<Genes, Integer> genesCount = new HashMap<>();
        for (Animal a : worldMap.getAnimals()) {
            Integer count = genesCount.getOrDefault(a.getDominantGene(), 0);
            genesCount.put(a.getDominantGene(), ++count);
        }
        if (genesCount.isEmpty()) return Genes.N;
        else return Collections.max(genesCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
