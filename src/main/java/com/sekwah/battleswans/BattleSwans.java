package com.sekwah.battleswans;

import com.sekwah.battleswans.assets.Assets;
import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.screen.GameDisplay;
import org.lwjgl.opengl.Display;

public class BattleSwans {

    private final GameDisplay display;
    private boolean isRunning;

    public Stage currentStage;

    public final Assets assets;

    public Stage changeToStage = null;

    public BattleSwans() {
        isRunning = true;
        display = new GameDisplay(this);

        assets = new Assets(this);

        display.init();

        start();
    }

    private void start() {
        while(!Display.isCloseRequested() && isRunning){
            if(changeToStage != null){
                currentStage = changeToStage;
                changeToStage = null;
            }
            display.update();
        }
        display.destroy();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
