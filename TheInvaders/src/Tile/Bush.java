package Tile;

import Joc.Game;
import Joc.Handler;
import Joc.Id;

import java.awt.*;

public class Bush extends Tile{
    public Bush(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.grass[5].getBufferedImage(),x,y+10,width,height,null );
    }


    @Override
    public void update() {

    }
}
