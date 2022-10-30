package Entity;

import Joc.Game;
import Joc.Handler;
import Joc.Id;
import Joc.Img.EditImage;
import Tile.Tile;
import Tile.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Troll extends Entity {
    private int frame = 20;
    private int frameDelay = 0;
    boolean right=true;
    boolean left=false;
    boolean attack=false;
    private int x_min, y_min,x_max,y_max;
    public Troll(int x, int y, int width, int height, boolean solid, Id id, Handler handler,
                 int x_min, int y_min,int x_max,int y_max ) throws IOException {

        super(x, y, width, height, solid, id, handler);
        health1 = new Health(3,this);
        health=3;
        image_health();
        this.x_min=x_min;
        this.y_min=y_min;
        this.x_max=x_max;
        this.y_max=y_max;
    }

    public void image_health() {
        try{
        BufferedImage image= ImageIO.read(getClass().getResourceAsStream("/health.png"));
        health1.health[2]=image.getSubimage(5*630,0,630,150);
        health1.health[1]=image.getSubimage(1*630,0,630,150);
        health1.health[0]=image.getSubimage(0,0,630,150);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void draw(Graphics g) {

        health1.draw(g);
        if(isDeath()) {

            g.drawImage(EditImage.mirror(Game.troll1[frame-10].getBufferedImage()), getX(), getY(), width, height, null);
        }
        else if(attack) {
            g.drawImage(EditImage.mirror(Game.troll1[frame-20].getBufferedImage()), getX(), getY(), width, height, null);
        }
        else if(right==true)
            g.drawImage(Game.troll1[frame].getBufferedImage(), getX(), getY(), width, height, null);
        else
            g.drawImage(EditImage.mirror(Game.troll1[frame].getBufferedImage()), getX(), getY(), width, height, null);
    }

    @Override
    public void update() {
        if(handler.entity.get(0).getX()<=x_min || handler.entity.get(0).getY()<y_min ||handler.entity.get(0).getY()>y_max||handler.entity.get(0).getX()>x_max  ) {
            attack=false;
            if (right == true) {
                setX(getX() + 3);
            } else if (left == true) {
                setX(getX() - 3);
            }

            if (getX() > x_max-3) {
                right = false;
                left = true;
            }
            if (getX() < x_min+3) {
                right = true;
                left = false;
            }
        }
        else{
            attack=true;
            left=true;
            right=false;

        }
        setY(getY()+getVelY());
        for( Tile t: handler.tile) {
            if(!t.solid)
                break;
            if(t.getId()==Id.wall || t.getId()==Id.rock) {
                if(getBoundsTop().intersects(t.getBounds())) {
                    setVelY(0);
                    if(jumping) {
                        jumping = false;
                        gravity = 0.9;
                        falling = true;
                    }
                }
                if(getBoundsBotton().intersects(t.getBounds())) {
                    setVelY(0);
                    if(falling)
                        falling = false;
                }
                else {
                    if(!falling && !jumping) {
                        gravity=0.8;
                        falling=true;
                    }
                }
                if(getBoundsLeft().intersects(t.getBounds())) {
                    setVelX(0);
                    setX(t.getX()+t.width);
                }
                if(getBoundsRight().intersects(t.getBounds())) {
                    setVelX(0);
                    setX(t.getX()-t.width);
                }
            }
        }

        if(falling) {
            gravity+=0.1;
            setVelY((int)gravity);
        }
        frameDelay++;
        if(frameDelay>=5){
            frame++;
            if(frame>29) {
                frame =20;
            }
            frameDelay=0;
        }

        if( isDeath()&&frame-10==19)
            die();


    }


}
