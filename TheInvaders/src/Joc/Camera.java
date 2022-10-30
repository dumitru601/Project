package Joc;

import Entity.Entity;

public class Camera {
    private int x, y;

    public void update(Entity player) {
        setX(-player.getX()+Game.getFrameWidth()/2);
        setY(-player.getY()+Game.getFrameHeight()/2);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
