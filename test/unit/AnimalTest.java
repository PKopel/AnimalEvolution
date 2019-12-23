package unit;

import main.logic.animal.Animal;
import main.logic.map.Position;
import org.junit.jupiter.api.Test;

class AnimalTest {
    @Test
    void animalMatingTest() {
        Animal a1 = new Animal(10);
        Animal a2 = new Animal(10);
        a1.setPosition(new Position(0, 0));
        a2.setPosition(new Position(0, 0));
        System.out.println(a1);
        System.out.println(a2);
        Animal a3 = a1.mate(a2);
        System.out.println(a3);
    }

    @Test
    void eatTest() {
        Animal a = new Animal(0);
        a.setPosition(new Position(0, 0));
        a.eat();
        assert a.getEnergy() == 1;
    }

    /*
    @Test
    void moveTest() {
        Animal a = new Animal(0, new Position(0, 0));

        assert a.move(Genes.N).equals(new Position(0, 1));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.NE).equals(new Position(-1, -1));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.SW).equals(new Position(-1, 0));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.E).equals(new Position(0, -1));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.W).equals(new Position(-1, 0));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.NW).equals(new Position(1, 1));
        assert a.move(Genes.S).equals(new Position(0, 0));
        assert a.move(Genes.SE).equals(new Position(0, 1));
    }

     */
}
