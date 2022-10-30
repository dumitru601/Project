package Joc.Img;

import java.awt.image.BufferedImage;

public class EditImage {
    public static BufferedImage mirror(BufferedImage image) {
        int width= image.getWidth();
        int height = image.getHeight();
        BufferedImage ming = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0, rx=width-1; j <width; rx--, j++) {
                int p=image.getRGB(j,i);
                ming.setRGB(rx,i,p);
            }

        }
        return ming;
    }
}
