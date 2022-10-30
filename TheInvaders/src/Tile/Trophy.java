package Tile;

import Joc.Game;
import Joc.Handler;
import Joc.Id;

import java.awt.*;

public class Trophy extends Tile{
    public Trophy(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.grass[4].getBufferedImage(),x,y,width,height,null );
    }

    @Override
    public void update() {

    }
}
