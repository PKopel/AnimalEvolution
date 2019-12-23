package main.logic.map;

import main.logic.animal.Animal;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private LinkedList<Animal> animals = new LinkedList<>();
    private boolean plant = false;

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
        List<Animal> dead = animals.stream().filter(animal -> animal.getEnergy() <= 0).collect(Collectors.toList());
        animals.removeAll(dead);
        return dead;
    }

    public boolean addPlant() {
        if (this.plant) {
            return false;
        } else {
            this.plant = true;
            return true;
        }
    }

    public boolean hasPlant() {
        return plant;
    }

    public void eatPlant() {
        if (animals.size() > 0 && plant) {
            Collections.max(
                    animals, Comparator.comparingInt(Animal::getEnergy)).eat();
            plant = false;
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
