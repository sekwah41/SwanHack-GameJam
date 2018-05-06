package com.sekwah.battleswans.gamestages;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.entities.Entity;
import com.sekwah.battleswans.entities.Player;
import com.sekwah.battleswans.world.TestWorld;
import com.sekwah.battleswans.world.World;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class TestStage extends Stage {

    float currentRotation = 0;

    private float cameraX = 0;
    private float cameraY = 0;

    private Player player;

    private Entity[] entities;

    private World world;

    public TestStage(BattleSwans game) {
        super(game);
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
        GL11.glTranslatef(cameraX + Display.getWidth() / 2,cameraY + Display.getHeight() / 2F,0);
        game.assets.rebindTexture(game.textures.testSpriteSheet);
        /*for(Entity entity: renderEntities){
            entity.doRender();
        }*/
        world.doRender();
        player.doRender();

        GL11.glPopMatrix();

    }




}
