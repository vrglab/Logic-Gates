package org.Vrglab.LogicGates.World.Blocks.Simple;

import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BlockStateProperties extends net.minecraft.world.level.block.state.properties.BlockStateProperties {
    public static final BooleanProperty LEFT_INPUT;
    public static final BooleanProperty RIGHT_INPUT;
    public static final BooleanProperty OUTPUT;

    static {
        LEFT_INPUT = BooleanProperty.create("left_input");
        RIGHT_INPUT = BooleanProperty.create("right_input");
        OUTPUT = BooleanProperty.create("output");
    }
}
