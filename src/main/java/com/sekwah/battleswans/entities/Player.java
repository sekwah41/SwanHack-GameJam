package com.sekwah.battleswans.entities;

import com.sekwah.battleswans.audio.AudioPlayer;
import com.sekwah.battleswans.entities.anims.SpritePoseInfo;
import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.world.World;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

public class Player extends Entity {

    private final int left;
    private final int right;
    private final int up;
    private final int down;
    private final int attack;
    private final int hiss;

    public int invincibleTicks = 90;

    public int lives = 3;

    protected int playerDirection = 1;

    protected float playerWalkTime = 0;

    protected boolean hissing = false;

    protected float speedBoost = 0;

    protected boolean isRising = false;

    protected int attackProgress = 0;

    /*private static AudioPlayer[] honk = {new AudioPlayer("/assets/sounds/Ducc1.wav"),
            new AudioPlayer("/assets/sounds/Ducc2.wav"), new AudioPlayer("/assets/sounds/Ducc3.wav")};*/

    private int currentPose = 0;

    private SpritePoseInfo[] poseInfo = {new SpritePoseInfo(7,1,39,27),
            new SpritePoseInfo(0,28,40,27, -0.5f, 0),
            new SpritePoseInfo(47,0,49,28, -5f, -0.5f),
            new SpritePoseInfo(96,0,59,28, -10f, -0.5f),
            new SpritePoseInfo(90,28,58,28, -9.5f, -0.5f),
            new SpritePoseInfo(42, 28,48,28, -4.5f, -0.5f),
            new SpritePoseInfo(0, 55,58,26, -9.5f, 3f),
            new SpritePoseInfo(58, 56,59,26, -10f, 3f),
            new SpritePoseInfo(155, 0,55,34, -9.5f, -2f)};
    private AudioPlayer honk = new AudioPlayer("/assets/sounds/honk.wav");

    private boolean gliding;

    public Player enemy;

    public Player(Stage stage, World world, int left, int right, int up, int down, int attack, int hiss) {
        super(stage, 70, 55, world);
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.attack = attack;
        this.hiss = hiss;
    }



    public void doUpdate(long passedTime){
        super.doUpdate(passedTime);

        this.gliding = false;

        if(this.posY > 600) {
            this.kill();
        }

        if(this.onGround){
            this.velX *= 0.5;
        }

        if(this.attackProgress > 0) {
            this.currentPose = this.attackProgress / 2;
            this.attackProgress++;
            if(this.currentPose == 3) {
                if(this.positionInEnemy(this.posX + (this.width / 2f + 20) * this.playerDirection, this.posY)) {
                    this.enemy.kill();
                }
            }
            if(this.currentPose == 6) {
                this.attackProgress = -1;
                this.currentPose = 0;
            }
        }
        else if(Keyboard.isKeyDown(this.attack) && !(this.passDown && !this.onGround) && this.attackProgress == 0) {
            attackProgress = 1;
        }
        else if(attackProgress == -1) {
            attackProgress = 0;
        }

        this.passDown = Keyboard.isKeyDown(this.down);
        if(!this.onGround && this.passDown) {
            this.velY += 1;
        }

        speedBoost *= 0.5;

        if(Keyboard.isKeyDown(this.right)){
            this.velX = this.passDown && this.onGround ? 2 : 8 + speedBoost;
            this.playerDirection = 1;
            playerWalkTime += passedTime;
        }
        else if(Keyboard.isKeyDown(this.left)){
            this.velX = this.passDown && this.onGround ? -2 : -8 - speedBoost;
            this.playerDirection = -1;
            playerWalkTime += passedTime;
        }
        else{
            playerWalkTime = 0;
        }
        boolean hissKeyDown = Keyboard.isKeyDown(this.hiss);
        if(!this.hissing && hissKeyDown) {
            honk.play();
            this.hissing = true;
        }
        else if(this.hissing && !hissKeyDown) {
            this.hissing = false;
        }

        if(Keyboard.isKeyDown(this.up)){
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
                this.speedBoost = 14;
                this.gliding = true;
            }
        }

        if(!this.gliding && this.passDown && !this.onGround) {
            if(this.positionInEnemy(this.posX + (this.width / 2f) * this.playerDirection, this.posY + this.height / 2) &&
                    this.posY < enemy.posY) {
                this.enemy.kill();
            }
        }

    }

    private boolean positionInEnemy(float x, float y) {
        float halfW = enemy.width / 2f;
        float halfH = enemy.height / 2f;
        return (enemy.posX - halfW - 10 < x) && (enemy.posX + halfW + 10 > x) && (enemy.posY - halfH < y) && (enemy.posY + halfH > y);
    }

    public void doRender(){
        if(invincibleTicks > 0 && invincibleTicks-- % 10 > 5) {
            return;
        }
        //GL11.glDisable(GL11.GL_TEXTURE_2D);
        glPushMatrix();

        glTranslatef(posX, posY,0);
        int poseID = currentPose;
        if(poseID == 0) {
            if(!this.gliding && this.passDown && !this.onGround) {
                glRotatef(40 * this.playerDirection, 0,0,1);
                poseID = 8;
            }
            else if(this.hissing && this.gliding) {
                poseID = 7;
            }
            else if(this.hissing) {
                poseID = 1;
            }
            else if(this.gliding) {
                poseID = 6;
            }
        }
        SpritePoseInfo pose = poseInfo[poseID];
        float scale = 2;
        glScalef(scale * -this.playerDirection, scale, scale);
        if(playerWalkTime != 0) {
            stage.drawTexture((float) (0 + Math.cos(playerWalkTime / 50f + Math.PI)), (float) (10 + Math.sin(playerWalkTime / 50f)),7,8);
            stage.drawTexture((float) (7 + Math.cos(playerWalkTime / 50f)), (float) (10 + Math.sin(playerWalkTime / 50f + Math.PI)),7,8);
        }
        else {
            stage.drawTexture(0, 10,7,8,1,0);
            stage.drawTexture(7, 10,7,8,1,0);
        }
        if(Keyboard.isKeyDown(this.down)) {
            stage.drawTexture(pose.xCenterOffset,pose.yCenterOffset, pose.width, pose.height, 1,pose.xTexOffset, pose.yTexOffset);
        }
        else {
            stage.drawTexture(pose.xCenterOffset,pose.yCenterOffset - 4, pose.width, pose.height, 1,pose.xTexOffset, pose.yTexOffset);
        }
        glPopMatrix();
        //glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
    }

    @Override
    public void kill() {
        if(invincibleTicks > 0) {
            return;
        }
        this.posX = 0;
        this.posY = -40;
        this.velX = 0;
        this.velY = 0;
        this.lives--;
        this.invincibleTicks = 90;
    }
}
