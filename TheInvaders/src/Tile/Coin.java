package Tile;

import Joc.Game;
import Joc.Handler;
import Joc.Id;

import java.awt.*;

public class Coin extends Tile{
    public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.grass[2].getBufferedImage(),x,y,width-10,height-10,null );
    }

    @Override
    public void update() {

    }
}
