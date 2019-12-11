package main;


import java.util.*;
import static main.World.*;

public class Map {
    private HashMap<Position, Field> fields = new HashMap<>();
    private LinkedList<Animal> animals = new LinkedList<>();
    private int height;
    private int width;
    private int jungleWidth;
    private int jungleHeight;
    private int energyFromPlant;

    public Map() {
        this.height = World.height;
        this.width = World.width;
        this.jungleHeight = World.jungleHeight;
        this.jungleWidth = World.jungleWidth;
        this.energyFromPlant = World.energyFromPlant;
        for (int i = 0; i < initialAnimals; i++) {
            Random r = new Random(i + 17 + initialAnimals + initialEnergy + energyFromPlant * Calendar.getInstance().getTimeInMillis());
            Position newPosition;
            Animal animal = new Animal(initialEnergy);
            do {
                newPosition = new Position(r.nextInt(width), r.nextInt(height));
                animal.setPosition(newPosition);
            } while (!changeField(animal, newPosition, new Field()));
            animals.add(animal);
        }
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    private boolean changeField(Animal animal, Position position, Field current) {
        Field newField = fields.getOrDefault(position, new Field());
        if (newField.animalEnters(animal)) {
            current.animalLeaves(animal);
            fields.putIfAbsent(position, newField);
            animal.setPosition(position);
            return true;
        } else return false;
    }

    private void addAnimal(Animal animal) {
        if (animal != null) {
            Random r = new Random(17 * Calendar.getInstance().getTimeInMillis());
            Position newPosition;
            Field newField;
            do {
                newPosition = Genes.values()[r.nextInt(8)].nextPosition(animal.getPosition());
                newField = fields.getOrDefault(newPosition, new Field());
            } while (!newField.animalEnters(animal) );
            fields.putIfAbsent(newPosition, newField);
            animals.add(animal);
        }
    }

    private void growPlant() {
        Random r = new Random(17 * fields.size() * width * height * Calendar.getInstance().getTimeInMillis());
        Position jungle;
        Field jungleField = new Field();
        jungleField.addPlant(energyFromPlant);
        int limiter = jungleHeight * jungleWidth;
        do {
            int jungleX = r.nextInt(jungleWidth);
            int jungleY = r.nextInt(jungleHeight);
            jungle = new Position(jungleX, jungleY);
        } while (fields.containsKey(jungle) && limiter-- > 0);
        fields.put(jungle, jungleField);

        Position step;
        Field stepField = new Field();
        stepField.addPlant(energyFromPlant);
        limiter = width * height;
        do {
            int stepX = r.nextInt(width - jungleWidth) + jungleWidth;
            int stepY = r.nextInt(height - jungleHeight) + jungleHeight;
            step = new Position(stepX, stepY);
        } while (limiter-- > 0 && fields.containsKey(step));
        fields.put(step, stepField);


    }

    public void day() {
        fields.values().forEach(f -> animals.removeAll(f.animalsDie()));

        animals.forEach(a -> this.changeField(a, a.move(), fields.get(a.getPosition())));

        new LinkedList<>(fields.values()).forEach(f -> {
            f.eatPlant();
            if (f.getAnimalsOn() > 1) this.addAnimal(f.reproduce());
        });

        this.growPlant();

        List<Position> empty = new LinkedList<>();
        for (Position p : fields.keySet()) {
            if (fields.get(p).isEmpty() && !fields.get(p).hasPlant()) empty.add(p);
        }
        empty.forEach(fields::remove);

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Animal a : animals) {
            result.append(a.toString()).append("\n");
        }
        return result.toString();
    }
}
