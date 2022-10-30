package Tile;

import Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health {
    public BufferedImage[] health;
    Entity entity;
    public Health(int n, Entity entity) {
        health=new BufferedImage[n];
        this.entity=entity;
    }
    public void draw(Graphics g) {
        if(entity.health-1>=0)
            g.drawImage(health[entity.health-1],entity.getX()-10,entity.getY()-10,entity.height,10 ,null);

    }
}
