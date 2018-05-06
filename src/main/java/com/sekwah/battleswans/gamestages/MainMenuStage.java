package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.audio.AudioPlayer;
import com.sekwah.battleswans.gui.Button;
import com.sekwah.battleswans.gui.PlayButton;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class MainMenuStage extends Stage {

    private int timeAlive = 0;

    float levelFade = 800;

    public List<Button> buttons = new ArrayList<Button>();

    public MainMenuStage(BattleSwans game) {
        super(game);
        AudioPlayer player = new AudioPlayer("/assets/music/intermission.wav");
        player.loop(-1);
        glClearColor(0.408F, 0.639F, 0.835F, 1F);
        buttons.add(new PlayButton(this, Display.getWidth() / 2, 350, 75, 25, 2.5F, 0,0));
    }

    @Override
    public void doUpdate() {
        super.doUpdate();
        if(levelFade > 0){
            levelFade -= timePassed;
        }
        for(Button button: buttons){
            button.doUpdate();
        }
    }

    @Override
    public void doRender() {
        //drawSquare();

        //glDisable();

        game.assets.rebindTexture(game.textures.mainMenu);
        for(Button button: buttons){
            button.doRender();
        }

        if(levelFade > 0){
            glDisable(GL_TEXTURE_2D);
            float fade = (levelFade / 800F);
            glColor4f(0F, 0F, 0F, fade);
            drawTexture(Display.getWidth()/ 2, Display.getHeight() / 2, 5, 5, 1000);
            glColor4f(1F, 1F, 1F, 1F);
            glEnable(GL_TEXTURE_2D);
        }
    }

}
