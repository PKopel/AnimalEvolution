package main.parameters;

public class WorldParameters {
    private int initialAnimals = 20;
    private int initialEnergy = 40;
    private int energyFromPlant = 50;
    private int moveEnergy = 1;
    private int width = 20;
    private int height = 20;
    private int jungleWidth = 10;
    private int jungleHeight = 10;
    private static WorldParameters parameters = new WorldParameters();

    private WorldParameters() {
    }

    public static WorldParameters getParameters() {
        return parameters;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public void setJungleSize(double jungleRatio) {
        this.jungleWidth = (int) (this.width * jungleRatio);
        this.jungleHeight = (int) (this.height * jungleRatio);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public int getEnergyFromPlant() {
        return energyFromPlant;
    }

    public void setEnergyFromPlant(int energyFromPlant) {
        this.energyFromPlant = energyFromPlant;
    }

    public int getInitialEnergy() {
        return initialEnergy;
    }

    public void setInitialEnergy(int initialEnergy) {
        this.initialEnergy = initialEnergy;
    }

    public int getInitialAnimals() {
        return initialAnimals;
    }

    public void setInitialAnimals(int initialAnimals) {
        this.initialAnimals = initialAnimals;
    }

    @Override
    public int hashCode() {
        int hash = 13 + initialAnimals;
        hash = hash * 13 + initialEnergy;
        hash = hash * 13 + energyFromPlant;
        hash = hash * 13 + moveEnergy;
        hash = hash * 13 + width;
        hash = hash * 13 + height;
        hash = hash * 13 + jungleHeight;
        hash = hash * 13 + jungleWidth;
        return hash;
    }
}
