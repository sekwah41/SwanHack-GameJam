package com.sekwah.battleswans;

import com.sekwah.battleswans.assets.Favicon;
import com.sekwah.battleswans.screen.GameDisplay;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Dimension;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class BattleSwans {

    private final GameDisplay display;

    public BattleSwans() {
        display = new GameDisplay(this);

    }
}
