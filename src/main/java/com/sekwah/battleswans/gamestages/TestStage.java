package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.audio.AudioPlayer;
import com.sekwah.battleswans.entities.Entity;
import com.sekwah.battleswans.entities.Player;
import com.sekwah.battleswans.world.TestWorld;
import com.sekwah.battleswans.world.World;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class TestStage extends Stage {

    private final AudioPlayer audioPlayer;
    float currentRotation = 0;

    private float cameraX = 0;
    private float cameraY = 0;

    private Player player;

    private Entity[] entities;

    private World world;

    public TestStage(BattleSwans game) {
        super(game);
        audioPlayer = new AudioPlayer("/assets/music/unloved.wav");
        audioPlayer.loop(-1);
        //glClearColor(0.408F, 0.639F, 0.835F, 1F);
        world = new TestWorld(this, 500,500);
        player = new Player(this, world);
    }

    public void setupStage(){
        super.setupStage();
    }

    public void doUpdate() {
        super.doUpdate();

        cameraX += (float) (-player.posX - cameraX) / 20F;
        cameraY += (float) (-player.posY - cameraY) / 20F;

        world.doUpdate();

        player.doUpdate(timePassed);
    }

    public void doRender() {
        super.doRender();

        GL11.glPushMatrix();
        game.assets.rebindTexture(game.textures.background);
        drawTexture(game.getWidth() / 2f, game.getHeight() / 2f + 40, game.assets.currentTextureSize.width,game.assets.currentTextureSize.height,
                game.getHeight() / game.assets.currentTextureSize.height + 0.93f);
        GL11.glTranslatef(cameraX + game.getWidth() / 2,cameraY + game.getHeight() / 2F,0);
        game.assets.rebindTexture(game.textures.testSpriteSheet);
        /*for(Entity entity: renderEntities){
            entity.doRender();
        }*/
        world.doRender();
        player.doRender();

        GL11.glPopMatrix();

    }




}
