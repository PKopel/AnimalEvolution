package main.parameters;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileReader;

public class ParametersFileReader {
    public static void readConfig() {
        try {
            FileReader file = new FileReader("parameters.json");
            JsonObject configs = (JsonObject) Jsoner.deserialize(file);

            WorldParameters.initialEnergy = configs.getDouble(ParamtersKeys.START_ENERGY).intValue();
            WorldParameters.energyFromPlant = configs.getDouble(ParamtersKeys.PLANT_ENERGY).intValue();
            WorldParameters.moveEnergy = configs.getDouble(ParamtersKeys.MOVE_ENERGY).intValue();
            WorldParameters.width = configs.getDouble(ParamtersKeys.WIDTH).intValue();
            WorldParameters.height = configs.getDouble(ParamtersKeys.HEIGHT).intValue();

            Double jungleRatio = configs.getDouble(ParamtersKeys.JUNGLE_RATIO);

            WorldParameters.jungleWidth = (int)(WorldParameters.width * jungleRatio);
            WorldParameters.jungleHeight = (int)(WorldParameters.height * jungleRatio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
