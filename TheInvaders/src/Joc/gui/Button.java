package Joc.gui;
import Joc.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import DataBase.DataBase;


public class Button {
    public int contor;
    public int count_img;
    public int x,y;
    public int width,height;
    public String label;
    public BufferedImage button[];
    public  boolean hoover;
    public  boolean click;
    public Button(int x, int y, int width, int height, String label,BufferedImage button) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.button=new BufferedImage[3];
        for (int i = 0; i <this.button.length ; i++) {
            this.button[i]=button.getSubimage(0,64*i,291,64);
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,50));
        FontMetrics fm = g.getFontMetrics();
        int stringX = (getWidth() - fm.stringWidth(getLabel())) / 2;
        int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);

        switch (label) {
            case "Start Game","Resume":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60) {
                            contor=0;
                            Game.playing = true;
                            Game.pause=false;
                            click=false;

                        }

                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);
                break;
            case "Exit":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60){
                            DataBase.saveDataBase();
                            System.exit(0);
                    }
                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);
                break;
            case "Instruction":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60){
                            count_img=0;
                            click=false;
                            contor=0;
                            Game.instruction=true;
                        }
                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);

                break;
            case "New Game":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60) {
                            DataBase.loadDataBase();
                            Game.newGame=true;
                            Game.playing=true;
                            Game.end=false;
                            Game.win=false;
                            Game.pause=false;
                        }
                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);

                break;
            case "Return":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60){
                            Game.instruction=false;
                            count_img=0;
                            contor=0;
                            click=false;
                        }
                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);

                break;
            case "Load":
                if(hoover){
                    g.drawImage(button[0], getX(), getY(), width, height, null);

                }
                if(click)
                    if (count_img == 2) {
                        contor++;

                        g.drawImage(button[2], getX(), getY(), width, height, null);
                        if (contor == 60){
                            DataBase.loadDataBase();
                            Game.playing=true;
                            Game.end=false;
                            Game.win=false;
                            Game.pause=false;
                            if(Game.level==2){

                                Game.switchLevel();
                                Game.num_coin=100;

                            }
                            DataBase.loadDataBase();

                        }
                    }
                if(hoover==false && click==false)
                    g.drawImage(button[1], getX(), getY(), width, height, null);

                break;


        }
        g.drawString(getLabel(), getX() + stringX, getY() + stringY);
    }
    public void triggerEvent(){
        count_img=2;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
