package com.sekwah.battleswans.world;

import com.sekwah.battleswans.entities.Entity;

public class PlatformTile extends Tile {

    public PlatformTile(World world, float width, float height) {
        super(world, width, height);
    }

    public void doUpdate(long passedTime){

    }

    public void updateCollisions(Entity entity){
        // Rather rough collision system but good for now
        //willCollideX(entity);
        if(entity.posY + (entity.height / 2F) <= this.posY - (this.height / 2F)) {
            willCollideY(entity);
        }
        isOn(entity);
    }

    public void doRender(){
        world.stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
