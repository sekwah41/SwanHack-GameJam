package com.sekwah.battleswans.entities;


import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.world.World;

public class Entity {

    public final float width;
    public final float height;
    protected final Stage stage;
    private final World world;
    public float posY = -40;
    public float posX;

    public float velX;
    public float velY;
    public boolean onGround = false;
    public boolean hasJumped = false;
    public boolean passDown = false;

    public Entity(Stage stage, float width, float height, World world){
        this.stage = stage;
        this.width = width;
        this.height = height;
        this.world = world;
    }

    public void setPos(float x, float y) {
        this.posX = x;
        this.posY = y;
    }

    public void doUpdate(long passedTime){
        this.onGround = false;
        this.hasJumped = false;
        world.updateCollisions(this);
        this.posX += velX;
        this.posY += velY;

        this.velX *= 0.9;
        this.velY += 2;
        this.velY *= 0.9;

    }

    public void doRender(){

    }

    public void kill() {

    }


}
