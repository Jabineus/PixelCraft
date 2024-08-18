package com.pixelcraft.Model;

import com.pixelcraft.*;
import javafx.scene.image.WritableImage;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModel implements Subject{
     private List<Observer> observers;
     private BufferedImage originalImage;
     private BufferedImage modifiedImage;
    
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(BufferedImage originalImage) {
        this.originalImage = originalImage;
        notifyObservers();
    }

    public BufferedImage getModifiedImage() {
        return modifiedImage;
    }

    public void setModifiedImage(BufferedImage modifiedImage) {
        this.modifiedImage = modifiedImage;
        notifyObservers();
    }

    public void loadImage(BufferedImage image) {
        this.originalImage = image;
        this.modifiedImage = image;
        notifyObservers();
    }

    public void saveImage(BufferedImage image, String filePath) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage writableImage = new WritableImage(width, height);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, image.getRGB(x, y));
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", new File(filePath));
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    public void applyConverter(Converter converter) {
        if (originalImage != null) {
            try {
                this.modifiedImage = converter.convert(originalImage);
                notifyObservers();
            }catch (Exception e) {
            e.printStackTrace();
        }
            
        }
    }
}

