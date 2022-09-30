package com.selfdot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogicCircuit {

    private final Layer outputLayer = new Layer();
    private final List<Layer> layers = new ArrayList<>();

    public LogicCircuit() { }

    public void readCircuitFile(String filename) throws IllegalArgumentException {
        List<NamedComponent> components = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");

                Component component = switch (line[0]) {
                    case "INPUT"  -> Component.INPUT;
                    case "OUTPUT" -> Component.OUTPUT;
                    case "AND"    -> Component.AND_GATE;
                    case "XOR"    -> Component.XOR_GATE;
                    default -> throw new IllegalArgumentException("Unknown component: " + line[0]);
                };

                NamedComponent namedComponent = new NamedComponent(line[1], component);
                for (int i = 2; i < line.length; i++) {
                    namedComponent.addInputName(line[i]);
                }
                if (line[0].equals("OUTPUT")) {
                    outputLayer.addComponent(namedComponent);
                } else {
                    components.add(namedComponent);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (NamedComponent namedComponent : components) {
            int layer = 0;
            for (String input : namedComponent.getInputNames()) {
                for (int i = 0; i < layers.size(); i++) {
                    if (layers.get(i).containsComponent(input)) {
                        layer = i;
                    }
                }
            }

            if (layer > layers.size()) {
                layers.add(new Layer());
            }

            layers.get(layer).addComponent(namedComponent);
        }
    }

}
