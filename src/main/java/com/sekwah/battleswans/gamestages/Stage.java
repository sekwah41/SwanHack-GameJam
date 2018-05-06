package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Stage {

    public final BattleSwans game;
    protected long timePassed;
    private long lastTime = -1;

    public Stage(BattleSwans game){
        this.game = game;
        this.lastTime = ((Sys.getTime() * 1000) / Sys.getTimerResolution());
        glClearColor(0F,0F,0F,1F);
    }

    public void setupStage(){

    }

    /**
     * Timekeeping for positions, animations and other logic, the system updates at about 920 a second if you add them up so
     *  if the last tick took 1 second the number would be 920
     *  @return time passed
     */
    private long timePassed(){
        long timeDifference = ((Sys.getTime() * 1000) / Sys.getTimerResolution()) - lastTime;
        lastTime = ((Sys.getTime() * 1000) / Sys.getTimerResolution());
        if(timeDifference > 120){
            return 120;
        }
        return timeDifference;
    }

    public void doUpdate() {
        timePassed = timePassed();

    }

    public void doRender() {

    }

    public void drawTexture(float x, float y, int width, int height) {
        drawTexture(x, y, width, height, 1);
    }

    public void drawTexture(float x, float y, int width, int height, float scale) {
        drawTexture(x,y,width,height,scale,0);
    }

    public void drawTexture(float x, float y, int width, int height, float scale, float rotation) {
        drawTexture(x,y,width,height,scale,rotation,width,height,0,0);
    }

    public void drawTexture(float x, float y, int width, int height, float scale, float rotation, int textureOffsetX, int textureOffsetY) {
        drawTexture(x,y,width,height,scale,rotation,width,height,textureOffsetX, textureOffsetY);
    }


    public void drawTexture(float x, float y, int width, int height, float scaleX, float scaleY, float rotation, int textureOffsetX, int textureOffsetY) {
        drawTexture(x,y,width,height,scaleX, scaleY,rotation,width,height,textureOffsetX, textureOffsetY);
    }

    public void drawTexture(float x, float y, int width, int height, float scale, int textureOffsetX, int textureOffsetY) {
        drawTexture(x,y,width,height,scale,0,width,height,textureOffsetX, textureOffsetY);
    }

    public void drawTexture(float x, float y, int width, int height, float scale, int textureWidth, int textureHeight, int textureOffsetX, int textureOffsetY) {
        drawTexture(x,y,width,height,scale,0,textureWidth,textureHeight,textureOffsetX, textureOffsetY);
    }

    public void drawTexture(float x, float y, int width, int height, float scale, float rotation, int textureWidth, int textureHeight, int textureOffsetX, int textureOffsetY){
        drawTexture(x,y,width,height,scale, scale, rotation,textureWidth,textureHeight,textureOffsetX, textureOffsetY);
    }

    public void drawTexture(float x, float y, int width, int height, float scaleX, float scaleY, float rotation, int textureWidth, int textureHeight, int textureOffsetX, int textureOffsetY){
        Dimension textureSize = game.assets.currentTextureSize;

        //glEnable(GL_TEXTURE_2D);

        glPushMatrix();

        glTranslatef(x,y,0F);
        glRotatef(rotation,0,0,1);
        glScalef(scaleX,scaleY,1);
        glTranslatef(-(width/2F),-(height/2F),0);

        float textureOffsetXf = (float) textureOffsetX + 0.01F;
        float textureOffsetYf = (float) textureOffsetY + 0.01F;
        float textureWidthf = (float) textureWidth - 0.02F;
        float textureHeightf = (float) textureHeight - 0.02F;

        glBegin(GL_QUADS);
        glTexCoord2f(textureOffsetXf / (float) textureSize.getWidth(), (textureOffsetYf + textureHeightf) / (float) textureSize.getHeight());
        glVertex2d(0, height);
        glTexCoord2f((textureOffsetXf + textureWidthf) / (float) textureSize.getWidth(), (textureOffsetYf + textureHeightf) / (float) textureSize.getHeight());
        glVertex2d(width, height);
        glTexCoord2f((textureOffsetXf + textureWidthf) / (float) textureSize.getWidth(), textureOffsetYf / (float) textureSize.getHeight());
        glVertex2d(width, 0);
        glTexCoord2f(textureOffsetXf / (float) textureSize.getWidth(), textureOffsetYf / (float) textureSize.getHeight());
        glVertex2d(0, 0);
        glEnd();


        glPopMatrix();
    }


    public void drawSquare(float x, float y, int width, int height) {
        drawSquare(x, y, width, height, 0);
    }

    // Probably will never be used again unless you really need a single colour square for some reason...
    public void drawSquare(float x, float y, int width, int height, float rotation) {

        glPushMatrix();

        glTranslatef(x, y, 0);
        glRotatef(rotation, 0, 0, 1);
        glTranslatef(-(width / 2F), -(height / 2F), 0);

        glBegin(GL_QUADS);
        glVertex2d(0, height);
        glVertex2d(width, height);
        glVertex2d(width, 0);
        glVertex2d(0, 0);
        glEnd();

        glPopMatrix();
    }


    public void destroy() {

    }

    public void keyUpdate(int key) {
        switch(key) {
            case Keyboard.KEY_ESCAPE:
                game.setIsRunning(false);
                break;
        }
    }
}