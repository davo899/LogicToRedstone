package com.selfdot;

import java.util.ArrayList;
import java.util.List;

public class Component {

    private final List<int[]> inputs = new ArrayList<>();
    private final List<int[]> outputs = new ArrayList<>();
    protected Schematic schematic;
    public Component(Schematic schematic) {
        this.schematic = schematic;
    }

    // 0 - AIR
    // 1 - CONCRETE
    // 2 - WIRE
    // 3 - TORCH
    // 4 - WALL TORCH
    // 5 - LEVER

    public static final Component AND_GATE;
    public static final Component XOR_GATE;
    static {
        int[] andGate = new int[] {
                1, 0,
                1, 4,
                1, 0,

                3, 0,
                2, 0,
                3, 0
        };
        AND_GATE = new Component(new Schematic("andGate", 2, 3, 2, andGate));
        AND_GATE.addInput(-1, 0, 0);
        AND_GATE.addInput(-1, 0, 2);
        AND_GATE.addOutput(2, 0, 1);

        int[] xorGate = new int[] {
                0, 0, 1, 0, 0,
                0, 0, 0, 0, 1,
                0, 0, 1, 0, 0,

                1, 4, 2, 1, 4,
                1, 1, 4, 0, 2,
                1, 4, 2, 1, 4,

                3, 0, 0, 2, 0,
                2, 2, 0, 0, 0,
                3, 0, 0, 2, 0,
        };
        XOR_GATE = new Component(new Schematic("xorGate", 5, 3, 3, xorGate));
        XOR_GATE.addInput(-1, 1, 0);
        XOR_GATE.addInput(-1, 1, 2);
        XOR_GATE.addOutput(5, 1, 1);
    }

    public Schematic getSchematic() {
        return schematic;
    }

    public void addInput(int x, int y, int z) {
        inputs.add(new int[] {x, y, z});
    }

    public void addOutput(int x, int y, int z) {
        outputs.add(new int[] {x, y, z});
    }

    public List<int[]> getInputs() {
        return inputs;
    }

    public List<int[]> getOutputs() {
        return outputs;
    }

}
