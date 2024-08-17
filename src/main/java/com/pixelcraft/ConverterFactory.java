package com.pixelcraft;

public class ConverterFactory {
    public static Converter getConverter(String converter) {
        switch (converter) {
            case "Grayscale":
                return new Grayscale();
            case "Rotate":
                return new Rotate();
            case "Blur":
                return new Blur();
            case "Vertical Flip":
                return new VerticalFlip();
            case "Pop Art":
                return new PopArt();
            case "Swirl":
                return new Swirl();
            case "Circle Crop":
                return new CircleCrop();
            case "Frame":
                return new Frame();
            case "Pixel":
                return new Pixel();
            case "Diagonal Flip":
                return new DiagonalFlip();
            default:
                throw new IllegalArgumentException("Invalid converter: " + converter);
        }
    }
}
