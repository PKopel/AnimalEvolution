package unit;

import main.Map;
import org.junit.jupiter.api.Test;

public class MapTest {
    Map map1 = new Map();
    Map map2 = new Map();
    @Test
    void dayTest(){
        map1.day();
        map2.day();
    }
}
