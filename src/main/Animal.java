package main;

import java.util.Arrays;
import java.util.Random;

public class Animal {
    private static int counter = 0;
    private final int id;
    private int energy;
    private Position position;
    private final int[] genotype = new int[32];

    public Animal(int energy, Position position) {
        this.energy = energy;
        this.position = position;
        this.id = ++counter;
        Random r = new Random(17 * id);
        for (int i = 0; i < 32; i++) {
            genotype[i] = r.nextInt(8);
        }
    }

    private Animal(int energy, Position position, int[] genotype) {
        this.energy = energy;
        this.position = position;
        System.arraycopy(genotype, 0, this.genotype, 0, 32);
        this.id = ++counter;
    }

    public int getEnergy() {
        return energy;
    }

    public void eat(){
        energy+=World.energyFromPlant;
    }

    public Animal mate(Animal partner) {
        int[] newGenotype = new int[32];
        int newGenotypeIndex = 0;
        int splitStart = 0;
        Random r = new Random(17 * id * energy);
        boolean partFromThis = true;
        do {
            int splitEnd = splitStart + r.nextInt(16) + 1;
            if (splitEnd > 32) splitEnd = 32;

            int[] part;

            if (partFromThis) {
                part = Arrays.copyOfRange(this.genotype, splitStart, splitEnd);
            } else {
                part = Arrays.copyOfRange(partner.genotype, splitStart, splitEnd);
            }

            for (int i : part) {
                newGenotype[newGenotypeIndex++] = i;
            }

            splitStart = splitEnd;
            partFromThis = !partFromThis;
        } while (splitStart < 31);

        int indexOfMutation = r.nextInt(32);
        newGenotype[indexOfMutation] = r.nextInt(8);

        int energyFromThis = this.energy / 2;
        int energyFromPartner = partner.energy / 2;
        this.energy -= energyFromThis;
        partner.energy -= energyFromPartner;

        return new Animal(energyFromThis + energyFromPartner, this.position, newGenotype);
    }

    @Override
    public String toString() {
        return "Animla: " + id + "; genotype: " + Arrays.toString(genotype);
    }
}
