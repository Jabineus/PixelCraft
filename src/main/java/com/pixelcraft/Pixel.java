package com.pixelcraft;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pixel extends Converter {
    @Override
    //Pixelates the image
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        BufferedImage pixeledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int pixelSize = 8; //Determines how big the pixel blocks will be
        //Iterates through each pixel block in the image
        for (int h = 0; h < height; h += pixelSize) {
            for (int w = 0; w < width; w += pixelSize) {
                int[] rgba = new int[]{0, 0, 0, 0}; //Stores red, green, blue and alpha values respectively
                int total = 0;
                // Iterates through each pixel in the pixel block
                for (int y = 0; y < pixelSize && h + y < height; y++) {
                    for (int x = 0; x < pixelSize && w + x < width; x++) {
                        int pixel = image.getRGB(w + x, h + y);
                        rgba[0] += (pixel >> 16) & 0xFF; //Adds up the individual color values
                        rgba[1] += (pixel >> 8) & 0xFF;
                        rgba[2] += pixel & 0xFF;
                        rgba[3] += (pixel >> 24) & 0xFF;
                        total++;
                    }
                }
                int red  = ((rgba[0] / total) << 16) & 0x00FF0000; //Calculates the average color
                int green = ((rgba[1] / total) << 8) & 0x0000FF00;
                int blue = (rgba[2] / total) & 0x000000FF;
                int alpha = ((rgba[3] / total) << 24) & 0xFF000000;
                // Sets all the pixels in the pixel block to the average color
                for (int y = 0; y < pixelSize && h + y < height; y++) {
                    for (int x = 0; x < pixelSize && w + x < width; x++) {
                        int averageColor = alpha | red | green | blue;
                        pixeledImage.setRGB(w + x, h + y, averageColor);
                    }
                }
            }
        }
        return pixeledImage;
    }
}
