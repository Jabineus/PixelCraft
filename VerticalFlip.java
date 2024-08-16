import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerticalFlip extends Converter {
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