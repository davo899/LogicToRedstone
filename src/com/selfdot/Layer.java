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
        Schematic newSchematic = new Schematic(
                "Layer",
                Math.max(schematic.getWidth(), toAdd.getComponent().schematic.getWidth()),
                schematic.getLength() + toAdd.getComponent().schematic.getLength() + (schematic.getLength() == 0 ? 0 : 1),
                Math.max(schematic.getHeight(), toAdd.getComponent().schematic.getHeight())
        );

        newSchematic.placeSubSchematic(schematic, 0, 0, 0);
        newSchematic.placeSubSchematic(
                toAdd.getComponent().schematic,
                0, 0, schematic.getLength() + (schematic.getLength() == 0 ? 0 : 1)
        );

        this.schematic = newSchematic;
    }

    public boolean containsComponent(String componentName) {
        return components.stream().anyMatch(component -> component.getName().equals(componentName));
    }

}
