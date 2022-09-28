package com.selfdot;

public class Component {

    private final Schematic schematic;
    public Component(Schematic schematic) {
        this.schematic = schematic;
    }

    // 0 - AIR
    // 1 - CONCRETE
    // 2 - WIRE
    // 3 - TORCH
    // 4 - WALL TORCH
    // 5 - LEVER

    private static final Component AND_GATE;
    private static final Component XOR_GATE;
    static {
        int[] andGate = new int[] {
                1, 0,
                1, 4,
                1, 0,

                3, 0,
                2, 0,
                3, 0
        };
        AND_GATE = new Component(new Schematic("andGate", (short) 2, (short) 3, (short) 2, andGate));

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
        XOR_GATE = new Component(new Schematic("andGate", (short) 5, (short) 3, (short) 3, xorGate));
    }

    public Schematic getSchematic() {
        return schematic;
    }

}
