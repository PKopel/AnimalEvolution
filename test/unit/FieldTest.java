package unit;

import main.Animal;
import main.Field;
import main.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("AssertWithSideEffects")
class FieldTest {

    @Test
    void addAnimalTest() {
        Field f = new Field();
        assert f.animalEnters(new Animal(0, new Position(0, 0)));
        assert f.getAnimalsOn() == 1;
    }

    @Test
    void removeAnimalTest() {
        Field f = new Field();
        f.animalEnters(new Animal(0, new Position(0, 0)));
        assert f.animalLeaves() != null;
        assert f.getAnimalsOn() == 0;
    }

    @Test
    void animalsDieTest(){
        Field f = new Field();
        f.animalEnters(new Animal(0, new Position(0, 0)));
        f.animalsDie();
        assert f.getAnimalsOn() == 0;
    }

    @Test
    void addPlantTest() {
        Field f = new Field();
        assert f.addPlant(1);
        assert f.hasPlant();
    }

}
