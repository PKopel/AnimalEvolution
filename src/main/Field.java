package main;

import java.util.*;

public class Field {
    private ArrayList<Animal> animals = new ArrayList<>(3);
    private int plant = 0;

    public int getAnimalsOn() {
        return animals.size();
    }

    public boolean animalEnters(Animal animal) {
        if (animals.size() < 3) {
            animals.add(animal);
            animals.sort(Comparator.comparingInt(Animal::getEnergy));
            return true;
        } else return false;
    }

    public Animal getAnimal() {
        return animals.get(animals.size() - 1);
    }

    public Animal animalLeaves() {
        return animals.remove(animals.size() - 1);
    }

    public void animalsDie() {
        animals.removeIf(animal -> animal.getEnergy() == 0);
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
        }
    }

    public Animal reproduce() {
        animals.sort(Comparator.comparingInt(Animal::getEnergy).reversed());
        if (animals.size() > 1)
            return animals.get(0).mate(animals.get(1));
        else return null;
    }
}
