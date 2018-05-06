package com.sekwah.battleswans.entities;

import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.world.World;
import org.lwjgl.input.Keyboard;

public class Player extends Entity {

    protected int playerDirection = 1;

    protected float playerWalkTime = 0;
    protected int playerWalkStage = 0;

    protected boolean hasDoubleJumped = false;

    protected float speedBoost = 0;

    protected boolean isRising = false;

    public Player(Stage stage, World world) {
        super(stage, 45, 69, world);

    }



    public void doUpdate(long passedTime){
        super.doUpdate(passedTime);

        if(this.posY > 600) {
            this.posX = 0;
            this.posY = 0;
            this.velX = 0;
            this.velY = 0;
        }

        if(this.onGround){
            this.velX *= 0.5;
        }

        if(playerWalkTime > 100){
            playerWalkTime -= 100;
            playerWalkStage++;
        }

        if(playerWalkStage > 7){
            playerWalkStage = 0;
        }

        speedBoost *= 0.5;

        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.velX = 8 + speedBoost;
            this.playerDirection = 1;
            playerWalkTime += passedTime;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            this.velX = -8 - speedBoost;
            this.playerDirection = -1;
            playerWalkTime += passedTime;
        }
        else{
            playerWalkTime = 0;
            playerWalkStage = 0;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(this.onGround) {
                this.velY = -20;
                speedBoost = 3;
                this.isRising = true;
            }
            else if(this.isRising && this.velY < 0) {
                this.velY -= 1.8;
            }
            else if(this.velY > 4.2f) {
                this.velY = 4.2f;
            }
        }

    }

    public void doRender(){
        stage.drawTexture(posX, posY, (int)this.width, (int)this.height, 1, 0, 0);
    }
}
