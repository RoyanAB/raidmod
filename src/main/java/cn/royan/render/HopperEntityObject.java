package cn.royan.render;

import net.minecraft.util.math.BlockPos;

public class HopperEntityObject {
    private final BlockPos pos;
    private final int coolDown;

    HopperEntityObject(BlockPos pos, int coolDown){
        this.pos = pos;
        this.coolDown=coolDown;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getCoolDown() {
        return coolDown;
    }
}
