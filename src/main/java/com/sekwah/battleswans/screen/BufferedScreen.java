package com.sekwah.battleswans.screen;

import com.sekwah.battleswans.BattleSwans;
import org.lwjgl.opengl.ARBFramebufferObject;

import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public abstract class BufferedScreen {

    private final int width;

    private final int height;

    private final BattleSwans game;

    public int screenFBO;

    // Will only really use the texture as the screen wont really have a depth buffer
    private int screenTex;
    private int screenDepth;

    public BufferedScreen(BattleSwans game, int width, int height){
        this.game = game;
        this.width = width;
        this.height = height;
        this.createFBO();
    }

    private void setupPerspective() {
        glViewport(0, 0, this.width, this.height);
        glOrtho(0, this.width, 0, this.height, 1, -1);
    }

    /**
     * Creates a FrameBufferObject in opengl
     */
    private void createFBO() {
        this.screenFBO = ARBFramebufferObject.glGenFramebuffers();
        switchToBuffer();

        this.screenTex = glGenTextures();
        this.screenDepth = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, this.screenTex);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, this.width, this.height, 0, GL_RGBA, GL_INT, (IntBuffer) null);

        glBindTexture(GL_TEXTURE_2D, this.screenDepth);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, this.width, this.height, 0, GL_DEPTH_COMPONENT, GL_INT, (IntBuffer) null);

        glBindTexture(GL_TEXTURE_2D, 0);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_TEXTURE_2D);

        switchFromBuffer();
    }

    public int getScreenTex(){
        return screenTex;
    }

    public void bindScreenTex(){
        glEnable(GL_TEXTURE_2D);
        game.assets.currentTextureSize = new Dimension(this.width, this.height);
        glBindTexture(GL_TEXTURE_2D, this.screenTex);
    }

    public void destroyScreen(){
        ARBFramebufferObject.glDeleteFramebuffers(screenFBO);
        glDeleteTextures(screenTex);
        glDeleteTextures(screenDepth);
    }

    public void switchToBuffer(){
        glBindTexture(GL_TEXTURE_2D, 0);// Reset the currently bound texture
        ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER, this.screenFBO);
        ARBFramebufferObject.glFramebufferTexture2D(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER,
                ARBFramebufferObject.GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, this.screenTex, 0);
        ARBFramebufferObject.glFramebufferTexture2D(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER,
                ARBFramebufferObject.GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, this.screenDepth, 0);
        glViewport(0, 0, this.width, this.height);
        //ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER, this.screenFBO);
    }

    /**
     * Could be used in future frame buffers but not needed here, would bind to a specified width and height.
     * but instead the screens have specified sizes.
     */
    /*private void bindFrameBuffer(int frameBuffer, int width, int height){
        glBindTexture(GL_TEXTURE_2D, 0);//To make sure the texture isn't bound
        ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_FRAMEBUFFER, frameBuffer);
        glViewport(0, 0, width, height);
    }*/

    public void switchFromBuffer(){
        // Should set the buffer back to the normal game
        ARBFramebufferObject.glBindFramebuffer(ARBFramebufferObject.GL_DRAW_FRAMEBUFFER, 0);
    }


    public void clearColour(){
        glClearColor(0.0F, 0.0F, 0.0F, 1F);
    }

    public void processScreen(){
        // Clear the screen for a rerender
        // TODO add stage mode or some sort of window manager for graphics on the screens.
        this.switchToBuffer();

        this.clearColour();
        glClear(GL_COLOR_BUFFER_BIT);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        this.setupPerspective();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        this.renderScreen();

        this.switchFromBuffer();

    }

    protected abstract void renderScreen();


    // A load of code to sort basic rendering of squares and textured squares
    // TODO remake this for graphics

    public void drawTexture(int width, int height) {
        drawTexture(0, 0, width, height, 1);
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

    public void drawSquare(int width, int height) {
        drawSquare(0, 0, width, height, 0);
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
}
