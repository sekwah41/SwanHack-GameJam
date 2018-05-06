package com.sekwah.battleswans.world;

import com.sekwah.battleswans.entities.Entity;

public class Tile {


    public final float width;
    public final float height;
    public final World world;
    public float posY;
    public float posX;

    public Tile(World world, float width, float height){
        this.world = world;
        this.width = width;
        this.height = height;
    }

    public void doUpdate(long passedTime){

    }

    public void doRender(){
    }

    public void updateCollisions(Entity entity){
        // Rather rough collision system but good for now
        willCollideX(entity);
        willCollideY(entity);
        isOn(entity);
    }

    protected void willCollideX(Entity entity) {
        float nextPosX = entity.posX + entity.velX;
        if (nextPosX - (entity.width / 2F) < this.posX + (this.width / 2F) &&
                nextPosX + (entity.width / 2F) > this.posX - (this.width / 2F) &&
                entity.posY - (entity.height / 2F) < this.posY + (this.height / 2F) &&
                entity.posY + (entity.height / 2F) > this.posY - (this.height / 2F)) {
            if(entity.velX < 0){
                entity.posX = this.posX + (this.width / 2F) + (entity.width / 2F);
            }
            else{
                entity.posX = this.posX - (this.width / 2F) - (entity.width / 2F);
            }
            entity.velX = 0;
        }
    }

    protected void willCollideY(Entity entity) {
        float nextPosY = entity.posY + entity.velY;
        if (entity.posX - (entity.width / 2F) < this.posX + (this.width / 2F) &&
                entity.posX + (entity.width / 2F) > this.posX - (this.width / 2F) &&
                nextPosY - (entity.height / 2F) < this.posY + (this.height / 2F) &&
                nextPosY + (entity.height / 2F) > this.posY - (this.height / 2F)) {
            if(entity.velY < 0){
                entity.posY = this.posY + (this.height / 2F) + (entity.height / 2F);
            }
            else{
                entity.posY = this.posY - (this.height / 2F) - (entity.height / 2F);
            }
            entity.velY = 0;
        }
    }

    protected void isOn(Entity entity) {
        float offsetPosY = entity.posY + 5;
        if (entity.posX - (entity.width / 2F) < this.posX + (this.width / 2F) &&
                entity.posX + (entity.width / 2F) > this.posX - (this.width / 2F) &&
                offsetPosY - (entity.height / 2F) < this.posY + (this.height / 2F) &&
                offsetPosY + (entity.height / 2F) > this.posY - (this.height / 2F)) {
            entity.onGround = true;
        }
    }

    public Tile setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        return this;
    }
}
