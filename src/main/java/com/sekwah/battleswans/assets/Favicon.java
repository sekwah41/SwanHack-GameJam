package com.sekwah.battleswans.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

public class Favicon {

    public static ByteBuffer[] load(){
        BufferedImage image16 = null;
        BufferedImage image32 = null;

        try {
            image16 = ImageIO.read(Objects.requireNonNull(Favicon.class.getClassLoader().getResource("assets/images/favicon/favicon16.png")));
            image32 = ImageIO.read(Objects.requireNonNull(Favicon.class.getClassLoader().getResource("assets/images/favicon/favicon32.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        assert image16 != null;
        byteBuffers[0] = convertToByteBuffer(image16);
        assert image32 != null;
        byteBuffers[1] = convertToByteBuffer(image32);

        return byteBuffers;
    }

    public static ByteBuffer convertToByteBuffer(BufferedImage image) {
        byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];

        int counter = 0;
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int colorRGB = image.getRGB(x,y);
                buffer[counter]     = (byte) ((colorRGB << 8) >> 24);
                buffer[counter + 1] = (byte) ((colorRGB << 16) >> 24);
                buffer[counter + 2] = (byte) ((colorRGB << 24) >> 24);
                buffer[counter + 3] = (byte) (colorRGB >> 24);
                counter += 4;

            }
        }
        return ByteBuffer.wrap(buffer);
    }


}