package com.sekwah.battleswans.assets;

import com.sekwah.battleswans.BattleSwans;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glDeleteTextures;

public class TextureManager {

    public int sekwahLogo = 0;

    public int mainMenu = 0;

    public int testSpriteSheet = 0;

    public TextureManager(BattleSwans game) {
        sekwahLogo = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/sekwahLogo.png"), GL11.GL_LINEAR);
        mainMenu = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/gui/main menu.png"), GL11.GL_NEAREST);
        testSpriteSheet = game.assets.loadTextureAndGetID(Assets.loadTexture("assets/images/world/testSprites.png"), GL11.GL_NEAREST);
    }
}
