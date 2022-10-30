package Entity;

import Exceptions.InvalidCoordinateException;
import Joc.Game;
import Joc.Handler;
import Joc.Id;
import Joc.Img.EditImage;
import Tile.Tile;
import Tile.Health;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

public class Player extends Entity{
    private static Player instance;
    private int frame = 0;
    private int frameDelay = 0;
    private  int frameHealth=0;
    private int framePlayer;
    private boolean animate = false;
    public static boolean win=false;


    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler){


        super(x, y, width, height, solid, id, handler);
        health1 = new Health(6,this);
        health=6;
        image_health();

    }


    public void image_health() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/health.png"));
            health1.health[5] = image.getSubimage(5 * 630, 0, 630, 150);
            health1.health[4] = image.getSubimage(4 * 630, 0, 630, 150);
            health1.health[3] = image.getSubimage(3 * 630, 0, 630, 150);
            health1.health[2] = image.getSubimage(2 * 630, 0, 630, 150);
            health1.health[1] = image.getSubimage(630, 0, 630, 150);
            health1.health[0] = image.getSubimage(0, 0, 630, 150);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void draw(Graphics g) {
        System.out.println("x="+getX()+"   y="+getY());
        health1.draw(g);
        if(facing==0&& attack==false) {
            g.drawImage(Game.player[frame + 13].getBufferedImage(), getX(), getY(), width, height, null);
        }
        else if(facing==1 && attack==false) {
            g.drawImage(Game.player[frame].getBufferedImage(), getX(), getY(), width, height, null);
        }

        else if(attack==true&& facing==0){
            g.drawImage(EditImage.mirror(Game.player[frame + 39].getBufferedImage()), getX(), getY(), width, height, null);
        }
        else if(attack==true&& facing==1){
            g.drawImage(Game.player[frame + 39].getBufferedImage(), getX(), getY(), width, height, null);
        }


    }

    @Override
    public void update(){
        setX(getX()+getVelX());
        setY(getY()+getVelY());
        if(health<=0){
            die();
        }

        animate = getVelX() != 0;
        for (int i = 0; i <handler.entity.size() ; i++)
        {
            Entity en=handler.entity.get(i);
            if(en.getId()==Id.troll){
                 if(getBoundsLeft().intersects(en.getBounds())) {
                        setVelX(0);
                        setX(en.getX()+en.width);

                    }

                else if(getBoundsRight().intersects(en.getBounds())) {
                    setVelX(0);
                    setX(en.getX()-en.width);
                }
                if(((((en.getX()-getX()<90) &&(en.getX()-getX()>0)) || ((getX()-en.getX()<70)&&(getX()-en.getX()>0)))&&
                        getY()-en.getY()<17)&&(attack)){
                    frameHealth++;
                    if(en.health==0)
                    {
                        en.setDeath(true);
                    }
                    if(frameHealth>30) {
                        en.health--;
                        frameHealth=0;
                    }framePlayer++;
                    if(framePlayer>30){
                        health--;
                        framePlayer=0;
                    }

                }
                else if(((((en.getX()-getX()<90) &&(en.getX()-getX()>0)) || ((getX()-en.getX()<70)&&(getX()-en.getX()>0)))&&
                        getY()-en.getY()<17)) {
                    framePlayer++;
                    if(framePlayer>30){
                        health--;
                        framePlayer=0;
                    }
                }
            }
        }
        for (int i = 0; i <handler.tile.size() ; i++)

        {
            Tile t= handler.tile.get(i);
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
            else if(t.getId()==Id.coin) {

                if(getBoundsTop().intersects(t.getBounds())) {
                    Game.num_coin+=10;
                    t.die();

                }
                else if(getBoundsBotton().intersects(t.getBounds())) {
                    Game.num_coin+=10;
                    t.die();
                }

                else if(getBoundsLeft().intersects(t.getBounds())) {
                    Game.num_coin+=10;
                    t.die();
                }
                else if(getBoundsRight().intersects(t.getBounds())) {
                    Game.num_coin+=10;
                    t.die();
                }
            }
            else if(t.getId()==Id.heart) {

                if(getBoundsTop().intersects(t.getBounds())) {
                    health=6;
                    t.die();

                }
                else if(getBoundsBotton().intersects(t.getBounds())) {
                    health=6;
                    t.die();
                }

                else if(getBoundsLeft().intersects(t.getBounds())) {
                    health=6;
                    t.die();
                }
                else if(getBoundsRight().intersects(t.getBounds())) {
                    health=6;
                    t.die();
                }
            }
            else if(t.getId()==Id.trophy) {
                if(getBounds().intersects(t.getBounds())&& Game.num_coin==100){
                    win=false;
                        Game.switchLevel();
                }
                else if(getBounds().intersects(t.getBounds())&& Game.num_coin==230 && win==false){
                    Game.win=true;
                    win=true;
                    Game.playing=false;
                    System.out.println("Aici");
                }
            }
        }
        if(jumping) {
            gravity-=0.1;
            setVelY((int)-gravity);
            if(gravity<=0.0) {
                jumping=false;
                falling = true;
            }
        }
        if(falling) {
            gravity+=0.1;
            setVelY((int)gravity);
        }
        if(animate || attack==true) {
            frameDelay++;
            if(frameDelay>=5){
                frame++;
                if(frame>12) {
                    frame =0;
                }
                frameDelay=0;
            }

        }

    }
}
