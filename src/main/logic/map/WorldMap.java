package main.logic.map;

import main.logic.animal.Animal;
import main.logic.animal.Genes;
import main.parameters.WorldParameters;
import main.logic.Observer;

import java.util.*;

public class WorldMap {
    private HashMap<Position, Field> fields = new HashMap<>();
    private LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<Observer> observers = new LinkedList<>();
    private int day = 1;
    private double avgDeathAge = 0;
    private int deathCount = 0;
    private int height;
    private int width;
    private int jungleWidth;
    private int jungleHeight;

    public WorldMap() {
        WorldParameters parameters = WorldParameters.getParameters();
        this.height = parameters.getHeight();
        this.width = parameters.getWidth();
        this.jungleHeight = parameters.getJungleHeight();
        this.jungleWidth = parameters.getJungleWidth();
        for (int i = 0; i < parameters.getInitialAnimals(); i++) {
            Random r = new Random(i + parameters.hashCode() + Calendar.getInstance().getTimeInMillis());
            Position newPosition;
            Animal animal = new Animal(parameters.getInitialEnergy());
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

    public int getDay() {
        return this.day;
    }

    public double getAvgDeathAge() {
        return this.avgDeathAge;
    }

    public HashMap<Position, Field> getFields() {
        return this.fields;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public Position translate(Position original) {
        return new Position((original.x + (width - jungleWidth) / 2) % width,
                (original.y + (height - jungleHeight) / 2) % height);
    }

    public Position translate(int x, int y) {
        return new Position((width + x - (width - jungleWidth) / 2) % width,
                (height + y - (height - jungleHeight) / 2) % height);
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
        fields.values().forEach(f -> {
            List<Animal> dead = f.animalsDie();
            if (dead.size() > 0) {
                animals.removeAll(dead);
                dead.forEach(a -> a.setDeathDay(day));
                double tmp = avgDeathAge * deathCount;
                tmp += dead.stream()
                        .mapToDouble(Animal::getAge)
                        .average()
                        .orElse(0d);
                deathCount += dead.size();
                avgDeathAge = tmp / deathCount;
            }
        });

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
        observers.forEach(Observer::change);
        day++;
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
