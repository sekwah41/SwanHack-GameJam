package com.sekwah.battleswans.assets;

import com.sekwah.battleswans.BattleSwans;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glDeleteTextures;

public class TextureManager {

    public int sekwahLogo = 0;

    public int mainMenu = 0;

    public int testSpriteSheet = 0;
    public int background = 0;
    public int brick = 0;
    public int platform = 0;
    public int swanSpriteSheet = 0;
    public int lifeCounter = 0;

    public TextureManager(BattleSwans game) {
        sekwahLogo = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/sekwahLogo.png"), GL11.GL_LINEAR);
        mainMenu = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/gui/main menu.png"), GL11.GL_NEAREST);
        testSpriteSheet = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/world/testSprites.png"), GL11.GL_NEAREST);
        brick = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/world/bricks.png"), GL11.GL_NEAREST);
        platform = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/world/platform.png"), GL11.GL_NEAREST);
        background = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/world/background.png"), GL11.GL_NEAREST);
        swanSpriteSheet = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/swans/swansprite.png"), GL11.GL_NEAREST);
        lifeCounter = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/gui/life.png"), GL11.GL_NEAREST);
    }
}
