package com.pixelcraft;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// ImageProcess class for reading and writing images
public class ImageProcess {
    public static BufferedImage readImage(String inputName) throws IOException {
        System.out.println("Image Reading Checkpoint");
        File inputtedFile = new File(inputName);
        return ImageIO.read(inputtedFile); //Reads given image
    }

    public static void writeImage(BufferedImage image, String outputName) throws IOException {
        System.out.println("Image Writing Checkpoint");
        File outputtedFile = new File(outputName);
        ImageIO.write(image, "png", outputtedFile); //Writes new png image with given output name
    }
}