package unit;

import main.logic.World;
import main.logic.map.WorldMap;
import org.junit.jupiter.api.Test;

public class WorldTest {
    WorldMap worldMap1 = new WorldMap();
    WorldMap worldMap2 = new WorldMap();

    @Test
    void dayTest() {
        worldMap1.day();
        worldMap2.day();
    }
}
