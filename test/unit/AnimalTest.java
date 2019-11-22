package unit;

import main.Animal;
import main.Position;
import org.junit.jupiter.api.Test;

class AnimalTest {
    @Test
    void animalMatingTest() {
        Animal a1 = new Animal(10, new Position(1, 1));
        Animal a2 = new Animal(10, new Position(1, 1));
        System.out.println(a1);
        System.out.println(a2);
        Animal a3 = a1.mate(a2);
        System.out.println(a3);
    }

    @Test
    void eatTest() {
        Animal a = new Animal(0, new Position(0, 0));
        a.eat();
        assert a.getEnergy() == 1;
    }
}
