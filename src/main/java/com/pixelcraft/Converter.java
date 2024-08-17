package com.pixelcraft;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Converter {
    protected abstract BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException;
    // Convert method to read image, convert it and write new image
    public void convert(String inputFileName, String outputFileName) throws IOException {
        try {
            BufferedImage inputImage = ImageProcess.readImage(inputFileName);
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();
            BufferedImage outputImage = alterImage(inputImage, width, height);
            ImageProcess.writeImage(outputImage, outputFileName);
            System.out.println("Image converted successfully! You file is " + outputFileName);
        } catch (IOException e) {
            System.out.println("Error processing image: " + e.getMessage());
        }
    }
}


