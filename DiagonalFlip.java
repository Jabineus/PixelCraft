
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DiagonalFlip extends Converter {
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
