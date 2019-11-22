package main;


import java.util.TreeSet;

public class Map {
    private TreeSet<Field> fields = new TreeSet<>((f1, f2) -> {
        if (f1.x > f2.x) return 1;
        else if (f1.x < f2.x) return -1;
        else return Integer.compare(f1.y, f2.y);
    });



}
