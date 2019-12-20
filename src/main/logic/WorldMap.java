package main.logic;


import main.parameters.WorldParameters;
import main.WorldObserver;

import java.util.*;

public class WorldMap {
    private HashMap<Position, Field> fields = new HashMap<>();
    private LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<WorldObserver> observers = new LinkedList<>();
    private int height;
    private int width;
    private int jungleWidth;
    private int jungleHeight;

    public WorldMap() {
        this.height = WorldParameters.height;
        this.width = WorldParameters.width;
        this.jungleHeight = WorldParameters.jungleHeight;
        this.jungleWidth = WorldParameters.jungleWidth;
        for (int i = 0; i < WorldParameters.initialAnimals; i++) {
            Random r = new Random(i + 17 + WorldParameters.initialAnimals + WorldParameters.initialEnergy * Calendar.getInstance().getTimeInMillis());
            Position newPosition;
            Animal animal = new Animal(WorldParameters.initialEnergy);
            do {
                newPosition = new Position(r.nextInt(width), r.nextInt(height));
                animal.setPosition(newPosition);
            } while (!changeField(animal, newPosition, new Field()));
            animals.add(animal);
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public HashMap<Position, Field> getFields() {
        return this.fields;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean addObserver(WorldObserver observer) {
        return observers.add(observer);
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public Position translate(Position original) {
        return new Position((original.x + (width - jungleWidth) / 2) % width,
                (original.y + (height - jungleHeight) / 2) % height);
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
            } while (!newField.animalEnters(animal));
            fields.putIfAbsent(newPosition, newField);
            animals.add(animal);
        }
    }

    private void growPlant() {
        Random r = new Random(17 * fields.size() * width * height * Calendar.getInstance().getTimeInMillis());
        Position jungle;
        Field jungleField = new Field();
        jungleField.addPlant();
        int limiter = jungleHeight * jungleWidth;
        do {
            int jungleX = r.nextInt(jungleWidth);
            int jungleY = r.nextInt(jungleHeight);
            jungle = new Position(jungleX, jungleY);
        } while (fields.containsKey(jungle) && limiter-- > 0);
        fields.put(jungle, jungleField);

        Position step;
        Field stepField = new Field();
        stepField.addPlant();
        limiter = width * height;
        do {
            int stepX = r.nextInt(width);
            int stepY;
            if (stepX < jungleWidth)
                stepY = r.nextInt(height - jungleHeight) + jungleHeight;
            else stepY = r.nextInt(height);
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
        observers.forEach(WorldObserver::worldChanged);
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
