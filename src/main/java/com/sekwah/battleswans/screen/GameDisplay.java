package com.sekwah.battleswans.screen;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.assets.Favicon;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Dimension;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class GameDisplay {

    private final BattleSwans game;

    private Dimension[] screenSizes = {new Dimension(1920,1080),new Dimension(1680,1050), new Dimension(1366,768),
            new Dimension(1280,1024), new Dimension(1280,800), new Dimension(1024,768), new Dimension(800,500)};

    public GameDisplay(BattleSwans game) {
        this.game = game;
    }

    public void init() {
        try{
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            for(Dimension size : screenSizes){
                if(width > size.getWidth() && height > size.getHeight()){
                    Display.setDisplayMode(new DisplayMode(size.getWidth(), size.getHeight()));
                    System.out.println("Width: " + size.getWidth() + " Height: " + size.getHeight());
                    break;
                }
            }

            Display.setTitle("Battle Swans");

            Display.setIcon(Favicon.load());

            Display.create();

            Display.setLocation(Display.getX(), Display.getDesktopDisplayMode().getHeight() / 2 - (Display.getHeight() + 30) / 2);

            //Mouse.setGrabbed(true);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity(); // rests the projection matrix(use in other places if needed)
            glOrtho(0, 1280, 720, 0, 1, -1);
            glMatrixMode(GL_MODELVIEW);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glEnable(GL_TEXTURE_2D);


            // TODO change back to black once got the main stuff sorted
            glClearColor(0.1F, 0.1F, 0.1F, 0F);

        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

}
