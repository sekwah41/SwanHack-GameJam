package com.sekwah.battleswans.world;

public class CollisionTile extends Tile {

    public CollisionTile(World world, float width, float height) {
        super(world, width, height);
    }

    public void doUpdate(long passedTime){

    }

    public void doRender(){
        world.stage.game.assets.rebindTexture(world.stage.game.textures.brick);
        world.stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
