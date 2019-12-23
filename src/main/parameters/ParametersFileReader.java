package main.parameters;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileReader;

public class ParametersFileReader {
    public static void readConfig() {
        try {
            FileReader file = new FileReader("parameters.json");
            JsonObject configs = (JsonObject) Jsoner.deserialize(file);

            WorldParameters parameters = WorldParameters.getParameters();
            parameters.setInitialEnergy(configs.getDouble(ParamtersKeys.START_ENERGY).intValue());
            parameters.setEnergyFromPlant(configs.getDouble(ParamtersKeys.PLANT_ENERGY).intValue());
            parameters.setMoveEnergy(configs.getDouble(ParamtersKeys.MOVE_ENERGY).intValue());
            parameters.setWidth(configs.getDouble(ParamtersKeys.WIDTH).intValue());
            parameters.setHeight(configs.getDouble(ParamtersKeys.HEIGHT).intValue());
            parameters.setJungleSize(configs.getDouble(ParamtersKeys.JUNGLE_RATIO));
            parameters.setInitialAnimals(configs.getInteger(ParamtersKeys.INITIAL_ANIMALS));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
