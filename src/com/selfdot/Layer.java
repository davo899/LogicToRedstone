package com.selfdot;

import java.util.ArrayList;
import java.util.List;

public class Layer extends Component {

    private final List<NamedComponent> components = new ArrayList<>();

    public Layer() {
        super(new Schematic("Layer", 0, 0, 0));
    }

    public void addComponent(NamedComponent toAdd) {
        components.add(toAdd);

        int zLocation = schematic.getLength() + (schematic.getLength() == 0 ? 0 : 1);

        for (int[] input : toAdd.getComponent().inputs) {
            inputs.add(new int[] {input[0], input[1], zLocation + input[2]});
        }
        for (int[] output : toAdd.getComponent().outputs) {
            outputs.add(new int[] {output[0], output[1], zLocation + output[2]});
        }

        Schematic newSchematic = new Schematic(
                "Layer",
                Math.max(schematic.getWidth(), toAdd.getComponent().schematic.getWidth()),
                zLocation + toAdd.getComponent().schematic.getLength(),
                Math.max(schematic.getHeight(), toAdd.getComponent().schematic.getHeight())
        );

        newSchematic.placeSubSchematic(schematic, 0, 0, 0);
        newSchematic.placeSubSchematic(
                toAdd.getComponent().schematic,
                0, 0, zLocation
        );

        this.schematic = newSchematic;
    }

    public boolean containsComponent(String componentName) {
        return components.stream().anyMatch(component -> component.getName().equals(componentName));
    }
}
