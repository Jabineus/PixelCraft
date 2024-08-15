import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

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
class Grayscale extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        BufferedImage grayedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        //Iterates through each pixel and sets the color to grayscale for the corresponding pixel in grayedIamge
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = new Color(image.getRGB(x, y));
                // Averages the red, green and blue components of the pixel color to make it gray
                int rgbGray = (int) (pixel.getRed() * 0.3) + (int) (pixel.getGreen() * 0.59) + (int) (pixel.getBlue() * 0.11);
                Color newPixel = new Color(rgbGray, rgbGray, rgbGray);
                grayedImage.setRGB(x, y, newPixel.getRGB());
            }
        }
        return grayedImage;
    }
}
class Rotate extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        // Iterates through each pixel and sets the new position to be 90 degrees clockwise from original position
        int x = 0; int y = 0;
        while (x < width){
            y = 0;
            while (y < height){
                //RGB value is set for the new pixel position on rotatedImage from the original coordinate color
                rotatedImage.setRGB(height - 1 - y, x, image.getRGB(x, y));
                y += 1;
            }
            x += 1;
        }
        return rotatedImage;
    }
}
class Blur extends Converter {
    @Override
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException {
        //Iterates through each pixel and calculates the average color of the surrounding pixels to set the new color
        int surrounding = 4; // Sets the radius of surrounding pixels taken into account and the blur strength
        BufferedImage blurredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int[] rgba = new int[]{0, 0, 0, 0};//Stores red, green, blue and alpha values respectively
                int total = 0;
                for (int y = -surrounding; y <= surrounding; y++) { //Iterates through surrounding pixels
                    for (int x = -surrounding; x <= surrounding; x++) {
                        int px = w + x;
                        int py = h + y;
                        if (px >= 0 && px < width && py >= 0 && py < height) {
                            int pixel = image.getRGB(px, py);
                            rgba[0] += (pixel >> 16) & 0xFF; //Adds all the red values of surrounding pixels
                            rgba[1] += (pixel >> 8) & 0xFF;  //Same for green, blue and alpha values
                            rgba[2] += pixel & 0xFF;
                            rgba[3] += (pixel >> 24) & 0xFF;
                            total++;
                        }
                    }
                }
                if (total > 0) {
                    int red  = ((rgba[0] / total) << 16) & 0x00FF0000; //Averages all the red values of surrounding
                    int green = ((rgba[1] / total) << 8) & 0x0000FF00;//pixels and same for the green, blue and alpha values
                    int blue = (rgba[2] / total) & 0x000000FF;
                    int alpha = ((rgba[3] / total) << 24) & 0xFF000000;
                    int blurredPixel = alpha | red | green | blue;
                    blurredImage.setRGB(w, h, blurredPixel); //sets blurred pixel color
                }
            }
        }
        return blurredImage;
    }
}
class VerticalFlip extends Converter {
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
        int[] topRowPixels = new int[width]; //Creates arrays to store all pixels in the top & bottom rows
        int[] bottomRowPixels = new int[width];

        originalImg.getRGB(0, top, width, 1, topRowPixels, 0, width); // Copies the pixels from the top and bottom rows
        originalImg.getRGB(0, bottom, width, 1, bottomRowPixels, 0, width);

        originalImg.setRGB(0, top, width, 1, bottomRowPixels, 0, width);//Switches the top & bottom row pixels
        originalImg.setRGB(0, bottom, width, 1, topRowPixels, 0, width);
        flipVertical(originalImg, top + 1, bottom - 1, width);
    }
}
class PopArt extends Converter {
    @Override
    //Gives the image a pop art effect
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        BufferedImage poppedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //Iterates through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = new Color(image.getRGB(x, y));

                int red = popColor(pixel.getRed()); //Calculates each component for rgb
                int green = popColor(pixel.getGreen());
                int blue = popColor(pixel.getBlue());
                Color newPixel = new Color(red, green, blue);
                poppedImage.setRGB(x, y, newPixel.getRGB());
            }
        }
        return poppedImage;
    }
    //If a value is below or above a certain limit it will return a different value to alter the color
    //This limits the variety in colors to give it that pop art effect
    public static int popColor(int color) {
        if (color < 90) {
            return 0;
        } else if (color < 180) {
            return 130;
        } else {
            return 255;
        }
    }
}
class Swirl extends Converter {
    @Override
    //Twists the image to create a spiral like swirl from the center
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        BufferedImage swirledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int middleX = width / 2;
        int middleY = height / 2;
        int radius = Math.min(width, height); //The radius of the swirl (how big it is)
        double turn = 0.02; //Twist size
        //Iterates through each pixel and determines its position/distance from the center of the image
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                double x = w - middleX;
                double y = h - middleY;
                double position = Math.pow((x * x + y * y), (0.5));
                //If the pixel is within the swirl radius it will be twisted at an angle depending on the position
                //and twist size
                if (position < radius) {
                    double angle = position * turn;
                    int moveX = middleX + (int) (x * Math.cos(angle) - y * Math.sin(angle));
                    int moveY = middleY + (int) (x * Math.sin(angle) + y * Math.cos(angle));
                    //The pixel's color is set to the color of the original image with the moved coordinates
                    //if the moved pixel is within the image size
                    if ((moveY >= 0 && moveY < height) && (moveX >= 0 && moveX < width)) {
                        Color pixel = new Color(image.getRGB(moveX, moveY), true);
                        swirledImage.setRGB(w, h, pixel.getRGB());
                    }
                }else{
                    swirledImage.setRGB(w, h, image.getRGB(w, h));
                }
            }
        }
        return swirledImage;
    }
}
class CircleCrop extends Converter {
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
                    croppedImage.setRGB(x, y, image.getRGB(x, y));
                }
            }
        }
        return croppedImage;
    }
}

class Frame extends Converter {
    @Override
    //Puts a brown square/rectanqular frame around the image
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        int frameThickness = 8;
        width = width + 2 * frameThickness;
        height = height + 2 * frameThickness;
        BufferedImage framedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Color frameColor = new Color(140, 65, 20);
        //Itertaes through each pixel and if it falls within the frame area then it is colored brown
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < frameThickness || x >= width - frameThickness || y < frameThickness || y >= height - frameThickness) {
                    framedImage.setRGB(x, y, frameColor.getRGB());
                } else {
                    // Outside the frame area the original image pixels are set to the original colors
                    int originalX = x - frameThickness;
                    int originalY = y - frameThickness;
                    framedImage.setRGB(x, y, image.getRGB(originalX, originalY));
                }
            }
        }
        return framedImage;
    }
}
class Pixel extends Converter {
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
class DiagonalFlip extends Converter {
    @Override
    //Flips the image diagonally recursively
    protected BufferedImage alterImage(BufferedImage image, int width, int height) throws IOException{
        flipRecursive(image, 0, Math.min(width, height) - 1, width, height);
        return image;
    }
    private static BufferedImage flipRecursive(BufferedImage image, int row, int col, int width, int height) {
        if (row >= height || col < 0) {
            return image; //Base case: return image when all the rows and columns have been swapped
        }
        flipRecursiveHelper(image, row, col, 0, width, height);
        return flipRecursive(image, row + 1, col - 1, width, height);
    }
    private static void flipRecursiveHelper(BufferedImage image, int row, int col, int i, int width, int height) {
        if (i >= Math.min(width, height)) {
            return;
        }
        int color = image.getRGB(i, row); //Temporarily stores the color for swapping
        image.setRGB(i, row, image.getRGB(width - col - 1, height - i -1)); //Swaps each row with its corresponding column
        image.setRGB(width - col - 1, height - i - 1, color);
        flipRecursiveHelper(image, row, col, i + 1, width, height);
    }
}

