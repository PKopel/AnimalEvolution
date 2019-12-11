package main.logic;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private LinkedList<Animal> animals = new LinkedList<>();
    private int plant = 0;

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public int getAnimalsOn() {
        return animals.size();
    }

    public boolean animalEnters(Animal animal) {
        animals.add(animal);
        animals.sort(Comparator.comparingInt(Animal::getEnergy));
        return true;
    }

    public void animalLeaves(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> animalsDie() {
        List<Animal> dead = animals.stream().filter(animal -> animal.getEnergy() == 0).collect(Collectors.toList());
        animals.removeAll(dead);
        return dead;
    }

    public boolean addPlant(int plant) {
        if (this.plant == 0) {
            this.plant = plant;
            return true;
        } else return false;
    }

    public boolean hasPlant() {
        return plant > 0;
    }

    public void eatPlant() {
        if (animals.size() > 0) {
            Collections.max(
                    animals, Comparator.comparingInt(Animal::getEnergy)).eat(plant);
            plant = 0;
        }
    }

    public Animal reproduce() {
        animals.sort(Comparator.comparingInt(Animal::getEnergy).reversed());
        return animals.get(0).mate(animals.get(1));
    }

    @Override
    public String toString() {
        return "plant: " + this.hasPlant() + "; animals: " + this.getAnimalsOn();
    }
}
