package Tile;

import Joc.Game;
import Joc.Handler;
import Joc.Id;

import java.awt.*;

public class Rock extends Tile{
    public Rock(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }


    public void draw(Graphics g) {
        g.drawImage(Game.grass[1].getBufferedImage(),x,y,width,height,null );
    }


    public void update() {

    }
}
