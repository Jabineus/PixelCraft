import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

public class Grayscale extends Converter {
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
