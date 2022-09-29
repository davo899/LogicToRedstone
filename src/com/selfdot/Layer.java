package com.selfdot;

public class Layer extends Component {

    public Layer() {
        super(new Schematic("Layer", 0, 0, 0));
    }

    public void addComponent(Component toAdd) {
        Schematic newSchematic = new Schematic(
                "Layer",
                Math.max(schematic.getWidth(), toAdd.schematic.getWidth()),
                schematic.getLength() + toAdd.schematic.getLength() + (schematic.getLength() == 0 ? 0 : 1),
                Math.max(schematic.getHeight(), toAdd.schematic.getHeight())
        );

        newSchematic.placeSubSchematic(schematic, 0, 0, 0);
        newSchematic.placeSubSchematic(
                toAdd.schematic,
                0, 0, schematic.getLength() + (schematic.getLength() == 0 ? 0 : 1)
        );

        this.schematic = newSchematic;
    }

}
