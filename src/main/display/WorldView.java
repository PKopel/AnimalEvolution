package main.display;

import main.Launcher;
import main.WorldObserver;
import main.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class WorldView extends JPanel implements WorldObserver {
    private World world;

    public WorldView(World world) {
        this.world = world;
        world.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        float scale = Math.min(((float) this.getHeight()) / world.getHeight(),
                ((float) this.getWidth()) / world.getWidth());
        this.setSize((int) (700 * scale), this.getHeight());

        g.setColor(Color.decode("#bbdd00"));
        g.fillRect(0, 0, (int)(world.getWidth()*scale), (int)(world.getHeight()*scale));

        g.setColor(Color.decode("#888800"));
        Position jungle = world.translate(new Position(0, 0));
        g.fillRect((int) (jungle.x * scale), (int) (jungle.y * scale),
                (int) (Launcher.jungleWidth * scale),
                (int) (Launcher.jungleHeight * scale));

        HashMap<Position, Field> fields = world.getFields();
        for (java.util.Map.Entry<Position, Field> field : fields.entrySet()) {
            if (field.getValue().hasPlant()) {
                g.setColor(Color.decode("#008800"));
                g.fillRect((int) (world.translate(field.getKey()).x * scale), (int) (world.translate(field.getKey()).y * scale),
                        (int) scale,
                        (int) scale);
            }
        }
        for (Animal animal : world.getAnimals()) {
            g.setColor(new Color(animal.getEnergy() * 2000));
            g.fillOval((int) (world.translate(animal.getPosition()).x * scale), (int) (world.translate(animal.getPosition()).y * scale),
                    (int) scale, (int) scale);
            g.setColor(Color.white);
            g.drawChars(((Integer) animal.getId()).toString().toCharArray(), 0,
                    ((Integer) animal.getId()).toString().length(),
                    (int) (world.translate(animal.getPosition()).x * scale + scale / 2),
                    (int) (world.translate(animal.getPosition()).y * scale + scale / 2));
        }
    }

    @Override
    public void worldChanged() {
        this.repaint();
    }
}

