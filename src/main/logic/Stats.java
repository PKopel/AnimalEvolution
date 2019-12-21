package main.logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class Stats {
    private WorldMap worldMap;

    public Stats(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private double getAvgStat(ToDoubleFunction<Animal> stat) {
        return worldMap.getAnimals().stream()
                .mapToDouble(stat)
                .average()
                .orElse(0d);
    }

    public double avgAge() {
        return this.getAvgStat(Animal::getAge);
    }

    public double avgEnergy() {
        return this.getAvgStat(Animal::getEnergy);
    }

    public double avgChildren() {
        return this.getAvgStat(Animal::getChildrenNumber);
    }

    public int animalNumber() {
        return this.worldMap.getAnimals().size();
    }

    public int plantsNumber() {
        return (int) this.worldMap.getFields().values()
                .stream()
                .filter(Field::hasPlant)
                .count();
    }

    public Genes dominantGene() {
        HashMap<Genes, Integer> genesCount = new HashMap<>();
        for (Animal a : worldMap.getAnimals()) {
            Integer count = genesCount.getOrDefault(a.getDominantGene(), 0);
            genesCount.put(a.getDominantGene(), ++count);
        }
        return Collections.max(genesCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
