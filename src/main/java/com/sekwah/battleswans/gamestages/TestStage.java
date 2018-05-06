package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.audio.AudioPlayer;
import com.sekwah.battleswans.entities.Entity;
import com.sekwah.battleswans.entities.Player;
import com.sekwah.battleswans.world.TestWorld;
import com.sekwah.battleswans.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class TestStage extends Stage {

    private final AudioPlayer audioPlayer;
    private final Player player2;
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
        player = new Player(this, world, Keyboard.KEY_A, Keyboard.KEY_D, Keyboard.KEY_W, Keyboard.KEY_S, Keyboard.KEY_SPACE, Keyboard.KEY_LMENU);
        player2 = new Player(this, world, Keyboard.KEY_NUMPAD4, Keyboard.KEY_NUMPAD6, Keyboard.KEY_NUMPAD8, Keyboard.KEY_NUMPAD5, Keyboard.KEY_NUMPAD0, Keyboard.KEY_NUMPADENTER);
        player.enemy = player2;
        player2.enemy = player;
    }

    public void setupStage(){
        super.setupStage();
    }

    public void doUpdate() {
        super.doUpdate();

        cameraX += (-((player.posX + player2.posX) / 2f) - cameraX) / 10F;
        cameraY += (-((player.posY + player2.posY) / 2f) - cameraY) / 10F;

        world.doUpdate();

        player.doUpdate(timePassed);
        player2.doUpdate(timePassed);
    }

    public void doRender() {
        super.doRender();

        GL11.glPushMatrix();
        game.assets.rebindTexture(game.textures.background);
        drawTexture(game.getWidth() / 2f, game.getHeight() / 2f + 40, game.assets.currentTextureSize.width,game.assets.currentTextureSize.height,
                game.getHeight() / game.assets.currentTextureSize.height + 0.43f);
        float playerDistanceX = Math.abs(player.posX - player2.posX);
        float playerDistanceY = Math.abs(player.posY - player2.posY);
        if(playerDistanceX > game.getWidth() - 500 || playerDistanceY > game.getHeight() - 300) {
            float scale = Math.min((game.getWidth() - 500) / playerDistanceX, (game.getHeight() - 300) / playerDistanceY);
            GL11.glTranslatef(cameraX * scale + game.getWidth() / 2,cameraY * scale + game.getHeight() / 2F,0);
            GL11.glScalef(scale,scale,scale);
        }
        else {
            GL11.glTranslatef(cameraX + game.getWidth() / 2,cameraY + game.getHeight() / 2F,0);
        }
        game.assets.rebindTexture(game.textures.testSpriteSheet);
        /*for(Entity entity: renderEntities){
            entity.doRender();
        }*/
        world.doRender();
        game.assets.rebindTexture(game.textures.swanSpriteSheet);
        player.doRender();
        player2.doRender();
        GL11.glPopMatrix();
        game.assets.rebindTexture(game.textures.lifeCounter);
        for(int i = 0; i < player.lives; i++) {
            drawTexture(40 + i * 64,40, 16,16, -4, 4, 0, 16, 16, 0,  0);
        }
        for(int i = 0; i < player2.lives; i++) {
            drawTexture(game.getWidth() - 128 - i * 64,40, 16,16, 4, 4, 0, 16, 16, 0,  0);
        }

        if(this.player.lives == 0 || this.player2.lives == 0) {
            // TODO add a proper winning screen
            audioPlayer.stop();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.currentStage = new TestStage(game);
        }
    }




}
