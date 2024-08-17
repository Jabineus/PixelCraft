package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerticalFlip extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        BufferedImage verticallyFlippedImage = image;
        flipVertical(verticallyFlippedImage, 0, height-1, width);
        return verticallyFlippedImage;
    }
    //Vertically flips image through recursion
    public void flipVertical(BufferedImage originalImg, int top, int bottom, int width) {
        if (top >= bottom) { //Base case: stopping swapping the rows when the top and bottom ones meet
            return;
        }
        for (int x = 0; x < width; x++) {
            // Get the top and bottom row pixels
            ARGB topPixel = new ARGB(originalImg.getRGB(x, top));
            ARGB bottomPixel = new ARGB(originalImg.getRGB(x, bottom));

            // Switch the pixels
            originalImg.setRGB(x, top, bottomPixel.toInt());
            originalImg.setRGB(x, bottom, topPixel.toInt());
        }
        flipVertical(originalImg, top + 1, bottom - 1, width);
    }
}