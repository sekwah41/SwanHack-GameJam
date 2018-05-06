package com.sekwah.battleswans.gui;

import com.sekwah.battleswans.gamestages.MainMenuStage;
import com.sekwah.battleswans.gamestages.Stage;
import com.sekwah.battleswans.gamestages.TestStage;

public class PlayButton extends Button {
    public PlayButton(Stage stage, float posX, float posY, int width, int height, float defaultZoom, int textureOffsetX, int textureOffsetY) {
        super(stage, posX, posY, width, height, defaultZoom, textureOffsetX, textureOffsetY);
    }

    public void activate(){
        stage.game.changeToStage = new TestStage(stage.game);
    }
}
