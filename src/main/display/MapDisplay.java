package main.display;

import main.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MapDisplay extends JPanel {
    private Map map;

    public MapDisplay(Map map) {
        this.map = map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        float scale = ((float) this.getHeight()) / World.height;
        this.setSize((int) (700 * scale), this.getHeight());

        g.setColor(Color.decode("#bbdd00"));
        g.fillRect(0,0,this.getWidth(), this.getHeight());

        g.setColor(Color.decode("#888800"));
        g.fillRect(0,0, (int)(World.jungleWidth*scale), (int)(World.jungleHeight*scale));

        HashMap<Position,Field> fields =  map.getFields();
        for (java.util.Map.Entry<Position, Field> field : fields.entrySet()) {
            if (field.getValue().hasPlant()) {
                g.setColor(Color.decode("#008800"));
                g.fillRect((int) (field.getKey().x * scale), (int) (field.getKey().y * scale), (int) scale,
                        (int) scale);
            }
        }
        for (Animal animal : map.getAnimals()) {
            g.setColor(new Color(animal.getEnergy()*2000));
            g.fillOval((int) (animal.getPosition().x * scale), (int) (animal.getPosition().y * scale),
                    (int) scale, (int) scale);
            g.setColor(Color.white);
            g.drawChars(((Integer)animal.getId()).toString().toCharArray(),0,
                    ((Integer)animal.getId()).toString().length(),
                    (int)(animal.getPosition().x * scale + scale/2),
                    (int) (animal.getPosition().y * scale + scale/2));
        }
    }
}

