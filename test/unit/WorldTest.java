package unit;

import main.logic.World;
import org.junit.jupiter.api.Test;

public class WorldTest {
    World world1 = new World();
    World world2 = new World();
    @Test
    void dayTest(){
        world1.day();
        world2.day();
    }
}
