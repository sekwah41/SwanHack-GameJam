package com.sekwah.battleswans.world;

public class CollisionTestTile extends Tile {

    public CollisionTestTile(World world, float width, float height) {
        super(world, width, height);
    }

    public void doUpdate(long passedTime){

    }

    public void doRender(){
        world.stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
