package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CircleCrop extends Converter {
    @Override
    //Crops the image in a circular shape
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        BufferedImage croppedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int middleX = width / 2;
        int middleY = height / 2;
        double radius = (Math.min(width, height))/2.5; //The radius of the image that will be not be cropped
        //Iterates through each pixel and if it outside the radius then it is cropped
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double position = Math.pow((x - middleX) * (x - middleX) + (y - middleY) * (y - middleY), (0.5));
                if (position > radius) {
                    croppedImage.setRGB(x, y, 0); // Pixel is set to transparent (cropped)
                } else {
                    ARGB pixel = new ARGB(image.getRGB(x, y));
                    croppedImage.setRGB(x, y, pixel.toInt());
                }
            }
        }
        return croppedImage;
    }
}


