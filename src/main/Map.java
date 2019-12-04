package main;


import java.util.HashMap;
import java.util.Random;

public class Map {
    private HashMap<Position,Field> fields = new HashMap<>();
    private int height;
    private int width;

    public Map(int initialAnimals, int initialEnergy, int energyFromPlant, int width, int height){
        this.height = height;
        this.width = width;
        for (int i = 0; i < initialAnimals; i++) {
            Random r = new Random(i + 17 + initialAnimals + initialEnergy + energyFromPlant);
            Position newPosition;
            do {
                newPosition = new Position(r.nextInt(width), r.nextInt(height));
            } while (!changeField(new Animal(initialEnergy), newPosition, new Field()));
        }
    }

    private boolean changeField(Animal animal, Position position, Field current){
        Field newField = fields.getOrDefault(position, new Field());
        if (newField.animalEnters(animal)){
            current.animalLeaves();
            fields.putIfAbsent(position,newField);
            return true;
        } else return false;
    }

    private void initialField(Animal animal){
        if(animal!=null) {
            Random r = new Random(17);
            Position newPosition;
            Field newField;
            do {
                newPosition = new Position(r.nextInt(width), r.nextInt(height));
                newField = fields.getOrDefault(newPosition, new Field());
            } while (!newField.animalEnters(animal));
            fields.putIfAbsent(newPosition, newField);
        }
    }

    public boolean isEmpty(){
        return fields.isEmpty();
    }

    private void growPlant(){
        Random r = new Random(17*fields.size()*width*height);
        int jungleX = r.nextInt();
        int jungleY = r.nextInt();
        int stepX = r.nextInt();
        int stepY = r.nextInt();
        fields.getOrDefault(new Position(jungleX, jungleY), new Field()).addPlant(1);
        fields.getOrDefault(new Position(stepX, stepY), new Field()).addPlant(1);
    }

    public void day(){
        for (Field f : fields.values()) {
            f.animalsDie();
            while (f.getAnimalsOn()>0){
                Animal a = f.getAnimal();
                changeField(a, a.move(),f);
            }
        }
        for (Field f: fields.values()) {
            f.eatPlant();
            this.initialField(f.reproduce());
        }
    }

}
