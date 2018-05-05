package com.sekwah.battleswans.screen;

import com.sekwah.battleswans.BattleSwans;
import com.sekwah.battleswans.assets.Favicon;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Dimension;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class GameDisplay {

    private final BattleSwans game;

    private boolean fullscreen = false;

/*    private Dimension[] screenSizes = {new Dimension(1920, 1080), new Dimension(1680, 1050), new Dimension(1366, 768),
            new Dimension(1280, 1024), new Dimension(1280, 800), new Dimension(1024, 768), new Dimension(800, 500)};*/

    public GameDisplay(BattleSwans game) {
        this.game = game;
    }

    public void init() {
        try {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            Display.setDisplayMode(new DisplayMode(width - 100, height - 100));
            /*for (Dimension size : screenSizes) {
                if (width > size.getWidth() && height - 100 > size.getHeight()) {
                    Display.setDisplayMode(new DisplayMode(size.getWidth(), size.getHeight()));
                    System.out.println("Width: " + size.getWidth() + " Height: " + size.getHeight());
                    break;
                }
            }*/

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

            glClearColor(0.0F, 0.0F, 0.0F, 0F);

        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        glClear(GL_COLOR_BUFFER_BIT);

        if(game.currentStage == null) {
            game.setIsRunning(false);
            System.out.println("Stage is null");
            return;
        }
        game.currentStage.doUpdate();

        game.currentStage.doRender();

        keyUpdate();

        Display.update();
        Display.sync(60);

    }

    private void keyUpdate() {
        while(Keyboard.next()){
            switch(Keyboard.getEventKey()){
                case Keyboard.KEY_F11:
                    try{
                        if(fullscreen){
                            setWindowed();
                        }
                        else{
                            setFullscreen();
                        }
                    } catch (LWJGLException e) {
                        e.printStackTrace();
                    }
                    break;
                case Keyboard.KEY_ESCAPE:
                    game.setIsRunning(false);
                    break;
            }
        }

    }

    private void setFullscreen() throws LWJGLException {
        fullscreen = true;

        Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());

        glMatrixMode(GL_MATRIX_MODE);
        //screenSize = new Dimension(Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight());
        glViewport(0, 0, Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight());

    }

    private void setWindowed() throws LWJGLException {
        fullscreen = false;

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Display.setDisplayMode(new DisplayMode(width - 100, height - 100));
        glViewport(0, 0, width - 100, height - 100);
        /*for(Dimension size : screenSizes){
            if(width > size.getWidth() && height > size.getHeight()){
                Display.setDisplayMode(new DisplayMode(size.getWidth(), size.getHeight()));
                glViewport(0, 0, size.getWidth(), size.getHeight());
                //screenSize = size;
                break;
            }
        }*/
        Display.setFullscreen(false);
    }

    public void destroy() {
        Display.destroy();
    }
}
