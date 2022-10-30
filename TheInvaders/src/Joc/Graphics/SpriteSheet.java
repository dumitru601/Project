package Joc.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage sheet;
    private int dim;
    public SpriteSheet(String path,int dim) {
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dim=dim;

    }
    public BufferedImage getSprite (int x,int y) {
        return sheet.getSubimage(x*dim-dim,y*dim-dim,dim,dim);
    }
}
