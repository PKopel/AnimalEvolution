package unit;

import main.Animal;
import main.Field;
import main.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("AssertWithSideEffects")
class FieldTest {

    @Test
    void addAnimalTest() {
        Field f = new Field(0, 0);
        assert f.animalEnters(new Animal(0, new Position(0, 0)));
        assert f.getAnimalsOn() == 1;
    }

    @Test
    void removeAnimalTest() {
        Field f = new Field(0, 0);
        f.animalEnters(new Animal(0, new Position(0, 0)));
        assert f.animalLeaves() != null;
        assert f.getAnimalsOn() == 0;
    }

    @Test
    void animalsDieTest(){
        Field f = new Field(0, 0);
        f.animalEnters(new Animal(0, new Position(0, 0)));
        f.animalsDie();
        assert f.getAnimalsOn() == 0;
    }

    @Test
    void addPlantTest() {
        Field f = new Field(0, 0);
        f.addPlant();
        assert f.hasPlant();
    }

    @Test
    void removePlantTest() {
        Field f = new Field(0, 0);
        assert !f.removePlant();
        f.addPlant();
        assert f.removePlant();
    }
}
