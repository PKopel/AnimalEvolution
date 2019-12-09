package main;

public class World {


    public static void main(String[] args) {
        Map map = new Map(50,30,5, 100, 30, 10 , 10);
        int day = 1;
        while (!map.isEmpty()){
            System.out.println(day++);
            System.out.println(map);
            map.day();
        }
    }
}
