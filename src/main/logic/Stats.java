package main.logic;

import main.WorldObserver;

import java.util.function.ToDoubleFunction;

public class Stats implements WorldObserver {
    private World world;

    public Stats(World world){
        this.world = world;
        world.addObserver(this);
    }

    private double getAvgStat(ToDoubleFunction<Animal> stat){
        return world.getAnimals().stream()
                .mapToDouble(stat)
                .average()
                .orElse(0d);
    }

    public double avgAge(){
        return this.getAvgStat(Animal::getAge);
    }

    public double avgEnergy(){
        return this.getAvgStat(Animal::getEnergy);
    }

    public double avgChildren(){
        return this.getAvgStat(Animal::getChildrenNumber);
    }

    public int animalNumber(){
        return this.world.getAnimals().size();
    }

    public int plantsNumber(){
        return (int)this.world.getFields().values()
                .stream()
                .filter(Field::hasPlant)
                .count();
    }

    @Override
    public void worldChanged() {

    }
}
