package com.sekwah.battleswans.entities;

import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.world.World;
import org.lwjgl.input.Keyboard;

public class Player extends Entity {

    protected int playerDirection = 1;

    protected float playerWalkTime = 0;
    protected int playerWalkStage = 0;

    public Player(Stage stage, World world) {
        super(stage, 45, 69, world);

    }



    public void doUpdate(long passedTime){
        super.doUpdate(passedTime);

        if(this.onGround){
            this.velX *= 0.5    ;
        }

        if(playerWalkTime > 100){
            playerWalkTime -= 100;
            playerWalkStage++;
        }

        if(playerWalkStage > 7){
            playerWalkStage = 0;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.velX = passedTime / 3.5F;
            this.playerDirection = 1;
            playerWalkTime += passedTime;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            this.velX = -passedTime / 3.5F;
            this.playerDirection = -1;
            playerWalkTime += passedTime;
        }
        else{
            playerWalkTime = 0;
            playerWalkStage = 0;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_W) && this.onGround){
            this.velY = -10;
        }

        /*if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            this.velY = -passedTime / 3.5F;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            this.velY = passedTime / 3.5F;
        }*/

    }

    public void doRender(){
        stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
