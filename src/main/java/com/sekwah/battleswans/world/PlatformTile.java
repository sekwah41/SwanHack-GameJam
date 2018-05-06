package com.sekwah.battleswans.world;

import com.sekwah.battleswans.entities.Entity;

public class PlatformTile extends Tile {

    public PlatformTile(World world, float blocksWidth) {
        super(world, blocksWidth * 14, 12);
    }

    public void doUpdate(long passedTime){

    }

    public void updateCollisions(Entity entity){
        // Rather rough collision system but good for now
        //willCollideX(entity);
        if(entity.posY + (entity.height / 2F) <= this.posY - (this.height / 2F) && !entity.passDown) {
            willCollideY(entity);
        }
        isOn(entity);
    }

    public void doRender(){
        world.stage.game.assets.rebindTexture(world.stage.game.textures.platform);
        world.stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
