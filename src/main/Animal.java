package main;

import java.util.Random;

public class Animal {
    private int energy;
    private Position position;
    private int[] genotype = new int[32];

    public Animal(int energy, Position position){
        this.energy=energy;
        this.position=position;
        Random r = new Random(17);
        for (int i=0; i<32; i++){
            genotype[i] = r.nextInt();
        }
    }
}
