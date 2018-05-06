package com.sekwah.battleswans;

import com.sekwah.battleswans.assets.Assets;
import com.sekwah.battleswans.assets.TextureManager;
import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.gamestages.StartStage;
import com.sekwah.battleswans.screen.GameDisplay;
import com.sun.prism.Texture;
import org.lwjgl.opengl.Display;

public class BattleSwans {

    private final GameDisplay display;
    public final TextureManager textures;
    private boolean isRunning;

    public Stage currentStage;

    public final Assets assets;

    public Stage changeToStage = null;

    public BattleSwans() {
        isRunning = true;
        display = new GameDisplay(this);
        display.init();

        assets = new Assets(this);
        textures = new TextureManager(this);

        currentStage = new StartStage(this);

        start();
    }

    private void start() {
        while(!Display.isCloseRequested() && isRunning){
            if(changeToStage != null){
                currentStage.destroy();
                currentStage = changeToStage;
                changeToStage = null;
            }
            display.update();
        }
        display.destroy();
        System.out.println("GAME WAS CLOSED *ANGRY HISSING*");
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public float getWidth() {
        return this.display.screenSize.getWidth();
    }

    public float getHeight() {
        return this.display.screenSize.getHeight();
    }


}
