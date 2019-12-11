package main;

public class World {
    public static int initialAnimals = 15;
    public static int initialEnergy = 20;
    public static int energyFromPlant = 5;
    public static int width = 20;
    public static int height = 20;
    public static int jungleWidth = 10;
    public static int jungleHeight = 10;

    public static void main(String[] args) {
        Map map = new Map();
        int day = 1;
        while (!map.isEmpty()){
            System.out.println(day++);
            System.out.println(map);
            map.day();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
