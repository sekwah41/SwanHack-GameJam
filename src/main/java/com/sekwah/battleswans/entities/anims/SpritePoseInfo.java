package com.sekwah.battleswans.entities.anims;

public class SpritePoseInfo {

    public final int xTexOffset;
    public final int yTexOffset;
    public final int width;
    public final int height;
    public final float xCenterOffset;
    public final float yCenterOffset;

    public SpritePoseInfo(int xTexOffset, int yTexOffset, int width, int height, float xCenterOffset, float yCenterOffset) {

        this.xTexOffset = xTexOffset;
        this.yTexOffset = yTexOffset;
        this.width = width;
        this.height = height;
        this.xCenterOffset = xCenterOffset;
        this.yCenterOffset = yCenterOffset;
    }

    public SpritePoseInfo(int xTexOffset, int yTexOffset, int width, int height) {
        this(xTexOffset, yTexOffset, width, height, 0,0);
    }

}
