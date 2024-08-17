package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Grayscale extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        BufferedImage grayedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        //Iterates through each pixel and sets the color to grayscale for the corresponding pixel in grayedIamge
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB pixel = new ARGB(image.getRGB(x, y));
                // Averages the red, green and blue components of the pixel color to make it gray
                int rgbGray = (int) (pixel.red * 0.3) + (int) (pixel.green * 0.59) + (int) (pixel.blue * 0.11);
                ARGB newPixel = new ARGB(pixel.alpha, rgbGray, rgbGray, rgbGray);
                grayedImage.setRGB(x, y, newPixel.toInt());
            }
        }
        return grayedImage;
    }
}
