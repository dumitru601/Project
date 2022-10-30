package Tile;

import Joc.Handler;
import Joc.Id;

import java.awt.*;

public abstract class Tile {
    public int x,y;
    public int width, height;
    boolean death=false;


    public boolean solid;

    public Id id;

    public Handler handler;
    public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        this.x= x;
        this.y=y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id=id;
        this.handler = handler;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public abstract void draw(Graphics g);
    public  abstract  void update();

    public void die () {
        handler.removeTile(this);
    }

    public Id getId() {
        return id;
    }



    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public  Rectangle getBounds() {
        return new Rectangle(getX(),getY(),width,height);
    }


}
