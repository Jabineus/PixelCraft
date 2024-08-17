package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rotate extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        // Iterates through each pixel and sets the new position to be 90 degrees clockwise from original position
        int x = 0; int y = 0;
        while (x < width){
            y = 0;
            while (y < height){
                //RGB value is set for the new pixel position on rotatedImage from the original coordinate color
                ARGB pixel = new ARGB(image.getRGB(x, y));
                rotatedImage.setRGB(height - 1 - y, x, pixel.toInt());
                y += 1;
            }
            x += 1;
        }
        return rotatedImage;
    }
}