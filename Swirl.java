import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

public class Swirl extends Converter {
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
