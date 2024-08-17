package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frame extends Converter {
    @Override
    //Puts a brown square/rectanqular frame around the image
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        int frameThickness = 8;
        width = width + 2 * frameThickness;
        height = height + 2 * frameThickness;
        BufferedImage framedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ARGB frameColor = new ARGB(255,140, 65, 20);
        //Itertaes through each pixel and if it falls within the frame area then it is colored brown
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < frameThickness || x >= width - frameThickness || y < frameThickness || y >= height - frameThickness) {
                    framedImage.setRGB(x, y, frameColor.toInt());
                } else {
                    // Outside the frame area the original image pixels are set to the original colors
                    int originalX = x - frameThickness;
                    int originalY = y - frameThickness;
                    ARGB pixel = new ARGB(image.getRGB(originalX, originalY));
                    framedImage.setRGB(x, y, pixel.toInt());
                }
            }
        }
        return framedImage;
    }
}