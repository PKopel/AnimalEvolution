package main;

public class Field extends Position {
    private Animal[] animals = new Animal[3];
    private boolean plant = false;
    private int animalsOn = 0;

    public Field(int x, int y) {
        super(x, y);
    }

    public int getAnimalsOn() {
        return animalsOn;
    }

    public boolean animalEnters(Animal animal) {
        if (animalsOn < 3) {
            animals[animalsOn++] = animal;
            return true;
        } else return false;
    }

    public Animal animalLeaves() {
        return animals[--animalsOn];
    }

    public void animalsDie() {
        if (animalsOn > 0) {
            Animal[] survivors = new Animal[3];
            int i = 0;
            for (Animal a : animals) {
                if (a != null) {
                    if (a.getEnergy() > 0) survivors[i++] = a;
                    else animalsOn--;
                }
            }
            animals = survivors;
        }
    }

    public boolean addPlant() {
        if (!plant) {
            plant = true;
            return true;
        } else return false;
    }

    public boolean hasPlant() {
        return plant;
    }

    public boolean removePlant() {
        if (plant) {
            plant = false;
            return true;
        } else return false;
    }

    public boolean reproduce() {
        return this.animalEnters(animals[0].mate(animals[1]));
    }
}
