package com.sekwah.battleswans.gui;

import com.sekwah.battleswans.gamestages.Stage;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Button {

    private final float posX;
    private final float posY;
    private final int width;
    private final int height;
    private final float defaultZoom;
    protected final Stage stage;
    private final int textureOffsetX;
    private final int textureOffsetY;

    private float size;

    private boolean mouseOver = false;

    private float sizeVelocity = 0;

    public Button(Stage stage, float posX, float posY, int width, int height, float defaultZoom, int textureOffsetX, int textureOffsetY){
        this.stage = stage;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.defaultZoom = defaultZoom;
        this.size = defaultZoom;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
    }

    public void doUpdate() {
        this.mouseOver = this.isMouseOver();

        if(mouseOver){
            sizeVelocity *= 0.8;
            sizeVelocity += ((defaultZoom * 1.4) - size) * 0.1;
        }
        else{
            sizeVelocity *= 0.8;
            sizeVelocity += ((defaultZoom) - size) * 0.1;
        }
        size += sizeVelocity;
        if(this.mouseOver && Mouse.isButtonDown(0)){
            this.activate();
        }
    }

    public boolean isMouseOver(){
        int mouseX = (int) (((float) Mouse.getX() / this.stage.game.getWidth()) * this.stage.game.getWidth());
        int mouseY = (int) (((float) Mouse.getY() / this.stage.game.getHeight()) * this.stage.game.getHeight());

        int collisionPosY = (int) (this.stage.game.getHeight() - this.posY);
        if (mouseX < this.posX + (this.width / 2F) * size &&
                mouseX > this.posX - (this.width / 2F) * size &&
                mouseY < collisionPosY + (this.height / 2F) * size &&
                mouseY > collisionPosY - (this.height / 2F) * size) {
            return true;
        }
        else{
            return false;
        }
    }

    public void doRender() {
        stage.drawTexture(posX, posY, width, height, size, textureOffsetX, textureOffsetY);
    }

    public void activate(){

    }

}
