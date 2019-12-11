package main.logic;

public class World {
    public static int initialAnimals = 20;
    public static int initialEnergy = 40;
    public static int energyFromPlant = 50;
    public static int width = 20;
    public static int height = 20;
    public static int jungleWidth = 10;
    public static int jungleHeight = 10;
    private Map map = new Map();

    public Map getMap(){
        return this.map;
    }

    public void run() {
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
