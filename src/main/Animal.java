package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Calendar;
import java.util.Random;

public class Animal {
    private static int counter = 0;
    private final int id;
    private int energy;
    private Position position = null;
    private Genes orientation;
    private final Genes[] genotype = new Genes[32];

    public Animal(int energy) {
        this.energy = energy;
        this.id = ++counter;
        Random r = new Random(17 * id * Calendar.getInstance().getTimeInMillis());
        for (int i = 0; i < 32; i++) {
            genotype[i] = Genes.values()[r.nextInt(8)];
        }
        Arrays.sort(genotype);
        this.orientation = Genes.values()[r.nextInt(8)];
    }

    private Animal(int energy, Position position, Genes[] genotype) {
        this.energy = energy;
        System.arraycopy(genotype, 0, this.genotype, 0, 32);
        this.id = ++counter;
        this.position = position;
    }

    public int getEnergy() {
        return energy;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void eat(int plant) {
        energy += plant;
    }

    public Position move() {
        this.energy--;
        Random r = new Random(17 * id * energy * Calendar.getInstance().getTimeInMillis());
        this.orientation = Genes.values()[
                (this.orientation.ordinal() + genotype[r.nextInt(32)].ordinal()) % 8];
        return this.orientation.nextPosition(this.position);
    }

    public Animal mate(Animal partner) {
        if (this.energy > 10 && partner.energy > 10) {
            System.out.println("sex");
            Genes[] newGenotype = new Genes[32];

            Random r = new Random(17 * id * energy * Calendar.getInstance().getTimeInMillis());
            int firstEnd = r.nextInt(14) + 1;
            int secondEnd = firstEnd + r.nextInt(14) + 1;

            System.arraycopy(this.genotype, 0, newGenotype, 0, firstEnd);
            System.arraycopy(partner.genotype, firstEnd, newGenotype, firstEnd, secondEnd - firstEnd);
            System.arraycopy(this.genotype, secondEnd, newGenotype, secondEnd, 32 - secondEnd);

            for (Genes g : Genes.values()) {
                if (!Arrays.asList(newGenotype).contains(g)) {
                    int indexOfMutation = r.nextInt(32);
                    newGenotype[indexOfMutation] = g;
                }
            }

            Arrays.sort(newGenotype, Comparator.comparingInt(Enum::ordinal));

            int energyFromThis = this.energy / 4;
            int energyFromPartner = partner.energy / 4;
            this.energy -= energyFromThis;
            partner.energy -= energyFromPartner;

            Animal offspring = new Animal(energyFromThis + energyFromPartner, this.position, newGenotype);
            System.out.println(offspring);
            return offspring;
        } else return null;
    }

    @Override
    public String toString() {
        return "Animal: " + id + "; position: " + position.x + " " + position.y + "; energy: " + energy;
    }
}
