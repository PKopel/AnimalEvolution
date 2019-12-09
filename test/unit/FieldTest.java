package unit;

import main.Animal;
import main.Field;
import main.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("AssertWithSideEffects")
class FieldTest {
    Animal a = new Animal(0);
    FieldTest() { a.setPosition(new Position(0,0));}
    @Test
    void addAnimalTest() {
        Field f = new Field();
        assert f.animalEnters(a);
        assert f.getAnimalsOn() == 1;
    }

    @Test
    void removeAnimalTest() {
        Field f = new Field();
        f.animalEnters(a);
        assert f.getAnimalsOn() == 0;
    }

    @Test
    void animalsDieTest(){
        Field f = new Field();
        f.animalEnters(a);
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
