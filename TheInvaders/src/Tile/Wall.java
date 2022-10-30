package Tile;

import Joc.Game;
import Joc.Handler;
import Joc.Id;

import java.awt.*;

public class Wall extends Tile{
    public Wall(int x, int y, int width, int height,boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }


    public void draw(Graphics g) {
        g.drawImage(Game.grass[0].getBufferedImage(),x,y,width,height,null );
    }


    public void update() {

    }
}
