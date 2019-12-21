package main.display;

import main.Observer;
import main.logic.animal.Animal;
import main.logic.map.Field;
import main.logic.map.Position;
import main.logic.map.WorldMap;
import main.parameters.WorldParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapView extends JPanel implements Observer {
    private WorldMap worldMap;
    private float scaleX;
    private float scaleY;

    public MapView(WorldMap worldMap) {
        this.worldMap = worldMap;
        worldMap.addObserver(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AnimalStatsView(Collections.max(worldMap.getAnimals()
                                .stream()
                                .filter(
                                        animal -> animal.getPosition()
                                                .equals(
                                                        worldMap.translate((int) (e.getX() / scaleX),
                                                                (int) (e.getY() / scaleY))))
                                .collect(Collectors.toList()),
                        Comparator.comparingInt(Animal::getEnergy)));
            }
        });
    }

    private void scale() {
        scaleY = ((float) this.getHeight()) / worldMap.getHeight();
        scaleX = ((float) this.getWidth()) / worldMap.getWidth();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        WorldParameters parameters = WorldParameters.getParameters();

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
            g.drawChars(animal.getId().toString().toCharArray(), 0,
                    animal.getId().toString().length(),
                    (int) (worldMap.translate(animal.getPosition()).x * scaleX + scaleX / 2),
                    (int) (worldMap.translate(animal.getPosition()).y * scaleY + scaleY / 2));
        }
    }

    @Override
    public void change() {
        this.scale();
        this.repaint();
    }
}

