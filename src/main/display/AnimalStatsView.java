package main.display;

import main.Observer;
import main.logic.World;
import main.logic.animal.Animal;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

public class AnimalStatsView extends JFrame implements Observer {
    private Animal animal;
    private JTextField genomeField = new JTextField();
    private JTextField childrenNUmberField = new JTextField();
    private JTextField ageField = new JTextField();
    private JTextField energyField = new JTextField();

    public AnimalStatsView(Animal animal) {
        this.animal = animal;
        animal.addObserver(this);
        this.setLayout(new GridLayout(2,1));

        this.genomeField.setEditable(false);
        this.genomeField.setBorder(new TitledBorder("Genotype:"));

        JPanel otherStats = new JPanel();
        otherStats.setLayout(new GridLayout(1,4));

        this.childrenNUmberField.setEditable(false);
        this.childrenNUmberField
                .setBorder(new TitledBorder("Number of children:"));

        this.ageField.setEditable(false);
        this.ageField.setBorder(new TitledBorder("Age:"));

        this.energyField.setEditable(false);
        this.energyField.setBorder(new TitledBorder("Energy:"));

        this.change();

        this.add(genomeField);
        otherStats.add(childrenNUmberField);
        otherStats.add(ageField);
        otherStats.add(energyField);
        this.add(otherStats);

        this.setTitle("Animal " + animal.getId());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 100);
        this.setVisible(true);
    }

    @Override
    public void change() {
        this.genomeField.setText(Arrays.toString(animal.getGenotype())
                .replace('[', ' ')
                .replace(']', ' ')
                .trim());
        this.childrenNUmberField.setText(animal.getChildrenNumber().toString());
        this.ageField.setText(animal.getAge().toString());
        this.energyField.setText(animal.getEnergy().toString());
    }
}
