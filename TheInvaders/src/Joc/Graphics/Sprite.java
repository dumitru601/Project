package Joc.Graphics;

import java.awt.image.BufferedImage;

public class Sprite {

    public BufferedImage image;
    public Sprite(SpriteSheet sheet, int x, int y) {
        image = sheet.getSprite(x,y);
    }
    public BufferedImage getBufferedImage() {
        return image;
    }

    public Sprite() {
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
