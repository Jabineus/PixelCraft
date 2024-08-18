package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Converter {
    protected abstract BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException;
    // Convert method to read image, convert it and write new image
    public BufferedImage convert(BufferedImage image) throws IOException {
        return alterImage(image, image.getWidth(), image.getHeight());
    }
}


