package Entity;

import Exceptions.InvalidCoordinateException;
import Joc.Game;
import Joc.Handler;
import Joc.Id;
import Tile.Health;

import java.awt.*;
import java.io.IOException;

public abstract class Entity {

    private int x,y;
    public int width, height;
    public int facing = 0;
    public boolean attack=false;
    private int velX, velY;
    public Id id;
    boolean death=false;
    public Handler handler;
    public int health ;
    public boolean solid;
    public  boolean jumping = false;
    public boolean falling = true;
    Health health1;
    public double gravity = 0.0;

    public Entity(int x, int y, int width, int height, boolean solid,Id id, Handler handler) {
        try {
            if(x<0 || y<0)
                throw new InvalidCoordinateException("Coordonata invalida");
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.solid = solid;
            this.id = id;
            this.handler = handler;
        }
        catch (InvalidCoordinateException e){
            System.out.println(e.getMessage());
        }
    }
    public abstract void draw(Graphics g);
    public  abstract  void update() throws IOException;

    public void die() {
        handler.removeEntity(this);
        if(this.getId()==Id.player)
            Game.showDeathScreen=true;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }
    public  Rectangle getBounds() {
        return new Rectangle(getX(),getY(),width,height);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(getX()+10,getY(),width-20,5);
    }
    public Rectangle getBoundsBotton() {
        return new Rectangle(getX()+10,getY()+height,width-20,5);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle(getX(),getY()+10,5,height-20);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle(getX()+width,getY()+10,5,height-20);
    }
}
