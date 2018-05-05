package com.sekwah.battleswans.assets;

import com.sekwah.battleswans.BattleSwans;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Assets {

    public Dimension currentTextureSize = new Dimension(0,0);

    private static BufferedImage[] images = new BufferedImage[0];

    private final BattleSwans game;

    public Assets(BattleSwans game){
        this.game = game;
    }

    public static BufferedImage loadTexture(String texture){
        try {
            return ImageIO.read(Assets.class.getClassLoader().getResource(texture));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load image: " + texture);
            return null;
        }
    }

    public int loadTextureAndGetID(BufferedImage image, int scaleType) {
        //glEnable(GL_TEXTURE_2D);

        this.currentTextureSize = new Dimension(image.getWidth(),image.getHeight());

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        this.currentTextureSize = new Dimension(image.getWidth(),image.getHeight());

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip();

        int textureID = glGenTextures();
        BufferedImage[] oldImages = images;
        images = new BufferedImage[images.length + 1];
        for(int i = 0; i < oldImages.length; i++){
            images[i] = oldImages[i];
        }
        images[textureID - 1] = image;

        glBindTexture(GL_TEXTURE_2D, textureID);

        // Setup the wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_TEXTURE_WRAP_R);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_TEXTURE_WRAP_R);

        // Set the scale filters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, scaleType);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, scaleType);


        glTexImage2D(GL_TEXTURE_2D,0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return textureID;
    }

    public void rebindTexture(int textureID) {
        //glEnable(GL_TEXTURE_2D);
        this.currentTextureSize = new Dimension(images[textureID - 1].getWidth(),images[textureID - 1].getHeight());

        glBindTexture(GL_TEXTURE_2D, textureID);
    }

}
