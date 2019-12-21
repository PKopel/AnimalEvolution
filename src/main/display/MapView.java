package main.display;

import main.WorldObserver;
import main.parameters.WorldParameters;
import main.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MapView extends JPanel implements WorldObserver {
    private WorldMap worldMap;

    public MapView(WorldMap worldMap) {
        this.worldMap = worldMap;
        worldMap.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        WorldParameters parameters = WorldParameters.getParameters();

        float scaleY = ((float) this.getHeight()) / worldMap.getHeight();
        float scaleX = ((float) this.getWidth()) / worldMap.getWidth();

        g.setColor(Color.decode("#bbdd00"));
        g.fillRect(0, 0, (int) (worldMap.getWidth() * scaleX), (int) (worldMap.getHeight() * scaleY));

        g.setColor(Color.decode("#888800"));
        Position jungle = worldMap.translate(new Position(0, 0));
        g.fillRect((int) (jungle.x * scaleX), (int) (jungle.y * scaleY),
                (int) (parameters.getJungleWidth() * scaleX),
                (int) (parameters.getJungleHeight() * scaleY));

        HashMap<Position, Field> fields = worldMap.getFields();
        for (Map.Entry<Position, Field> field : fields.entrySet()) {
            if (field.getValue().hasPlant()) {
                g.setColor(Color.decode("#008800"));
                g.fillRect((int) (worldMap.translate(field.getKey()).x * scaleX),
                        (int) (worldMap.translate(field.getKey()).y * scaleY),
                        (int) scaleX,
                        (int) scaleY);
            }
        }
        for (Animal animal : worldMap.getAnimals()) {
            g.setColor(new Color(animal.getEnergy() * 2000));
            g.fillOval((int) (worldMap.translate(animal.getPosition()).x * scaleX),
                    (int) (worldMap.translate(animal.getPosition()).y * scaleY),
                    (int) scaleX,
                    (int) scaleY);
            g.setColor(Color.white);
            g.drawChars(((Integer) animal.getId()).toString().toCharArray(), 0,
                    ((Integer) animal.getId()).toString().length(),
                    (int) (worldMap.translate(animal.getPosition()).x * scaleX + scaleX / 2),
                    (int) (worldMap.translate(animal.getPosition()).y * scaleY + scaleY / 2));
        }
    }

    @Override
    public void worldChanged() {
        this.repaint();
    }
}

