package com.selfdot;

import net.darkhax.opennbt.NBTHelper;
import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.IntTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schematic {

    // 0 - AIR
    // 1 - CONCRETE
    // 2 - WIRE
    // 3 - TORCH
    // 4 - WALL_TORCH
    private static final List<IntTag> REDSTONE_PALETTE = new ArrayList<>();
    static {
        REDSTONE_PALETTE.add(new IntTag("minecraft:air", 0));
        REDSTONE_PALETTE.add(new IntTag("minecraft:white_concrete", 1));
        REDSTONE_PALETTE.add(
                new IntTag("minecraft:redstone_wire[power=0,north=side,east=side,south=side,west=side]", 2)
        );
        REDSTONE_PALETTE.add(new IntTag("minecraft:redstone_torch[lit=false]", 3));
        REDSTONE_PALETTE.add(new IntTag("minecraft:redstone_wall_torch[facing=east,lit=false]", 4));
    }

    private final byte[] blocks;
    private final String name;
    private final short width;
    private final short length;
    private final short height;

    public Schematic(String name, short w, short l, short h) {
        this.name = name;
        this.width = w;
        this.length = l;
        this.height = h;
        this.blocks = new byte[w * l * h];
        Arrays.fill(blocks, (byte) 0);
    }

    public Schematic(String name, short w, short l, short h, int[] b) {
        this(name, w, l, h);

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = (byte) b[i];
        }
    }

    public void setBlock(int block, int x, int y, int z) {
        blocks[x + (z * width) + (y * width * length)] = (byte) block;
    }

    public void placeSubSchematic(Schematic sub, int x, int y, int z) {

    }

    public void generateFile() {
        CompoundTag file = new CompoundTag("Schematic");

        CompoundTag palette = new CompoundTag("Palette");
        REDSTONE_PALETTE.forEach(tag -> palette.setInt(tag.getName(), tag.getValue()));
        file.setCompoundTag("Palette", palette);
        file.setInt("PaletteMax", REDSTONE_PALETTE.size());

        file.setInt("Version", 2);
        file.setInt("DataVersion", 3120);

        file.setShort("Width", width);
        file.setShort("Length", length);
        file.setShort("Height", height);

        file.setTagList("BlockEntities", new ArrayList<>());
        file.setByteArray("BlockData", blocks);

        file.setIntArray("Offset", new int[] {0, 0, 0});

        NBTHelper.writeFile(file, name + ".schem");
    }

}