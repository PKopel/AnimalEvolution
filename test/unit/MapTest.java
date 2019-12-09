package unit;

import main.Map;
import org.junit.jupiter.api.Test;

public class MapTest {
    Map map1 = new Map(0,0,1,2,2,1,1);
    Map map2 = new Map(1,1,1,2,2,1,1);
    @Test
    void dayTest(){
        map1.day();
        map2.day();
    }
}
