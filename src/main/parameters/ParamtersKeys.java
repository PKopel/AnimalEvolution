package main.parameters;

import com.github.cliftonlabs.json_simple.JsonKey;

public enum ParamtersKeys implements JsonKey {
    WIDTH("width", 20),
    HEIGHT("height", 20),
    START_ENERGY("startEnergy", 40),
    MOVE_ENERGY("moveEnergy", 1),
    PLANT_ENERGY("plantEnergy", 20),
    JUNGLE_RATIO("jungleRatio", 0.5);

    private String key;
    private double value;

    ParamtersKeys(String key, double value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
