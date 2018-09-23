package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.audio.AudioPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class StartStage extends Stage {

    //private final AudioPlayer player;
    private int timeAlive = 0;

    public StartStage(BattleSwans game) {
        super(game);
        /*player = new AudioPlayer("/assets/music/Battle Swan.wav");
        player.loop(-1);
        player.setLoopPoints(1411460, 2823080);*/
        this.game.assets.rebindTexture(this.game.textures.sekwahLogo);
    }

    @Override
    public void doRender() {
        this.timeAlive += timePassed / 1.2F;
        //.out.println(timeAlive);
        float scale = (timeAlive / 4300F) * 0.1F - 0.1F;
        if(timeAlive < 400){
            float fade = ((float) timeAlive) / 400F;
            glColor3f(fade,fade,fade);
            drawTexture(game.getHeight() / 2, game.getHeight() / 2, 1280, 524, Display.getWidth() / 1280F + scale);
            glColor3f(1F,1F,1F);
        }
        else if(timeAlive < 3800){
            drawTexture(game.getWidth() / 2, game.getHeight() / 2, 1280, 524, Display.getWidth() / 1280F + scale);
        }
        else if(timeAlive < 4200){
            float fade = 1F - ((float) timeAlive - 3800F) / 400F;
            glColor3f(fade,fade,fade);
            drawTexture(Display.getWidth() / 2, game.getHeight() / 2, 1280, 524, Display.getWidth() / 1280F + scale);
            glColor3f(1F,1F,1F);
        }
        else if(timeAlive > 4300){
            //game.currentStage = new MainMenuStage(game);
            game.currentStage = new TestStage(game);
        }
    }

    public void keyUpdate(int key) {
        if(timeAlive < 4300) {
            //game.currentStage = new MainMenuStage(game);
            game.currentStage = new TestStage(game);
        }
    }

}
