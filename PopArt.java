import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

public class PopArt extends Converter {
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