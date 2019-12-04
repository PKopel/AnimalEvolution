package main;

public class World {


    public static void main(String[] args) {

        int energyFromPlant;
        if (args.length>0){
            energyFromPlant = Integer.parseInt(args[0]);
        } else {
            energyFromPlant = 5;
        }

        int initialEnergy ;
        if (args.length>1){
            initialEnergy = Integer.parseInt(args[1]);
        } else {
            initialEnergy = 20;
        }

        int initialAnimals ;
        if (args.length>2){
            initialAnimals = Integer.parseInt(args[2]);
        } else {
            initialAnimals = 5;
        }



        Map map = new Map(initialAnimals, initialEnergy, energyFromPlant);

    }
}
