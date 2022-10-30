package Joc.gui;

import Joc.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Launcher implements ILauncher {
    public BufferedImage menu;

    public Button []buttons;

    public Launcher() throws IOException {
        menu= ImageIO.read(getClass().getResourceAsStream("/meniu.png"));
        buttons= new Button[4];
        buttons[0]=new Button(Game.getFrameWidth()/2-200,200,450,150,"Start Game",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[1]=new Button(Game.getFrameWidth()/2-200,360,450,150,"Load",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[2]=new Button(Game.getFrameWidth()/2-200,520,450,150,"Instruction",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[3]=new Button(Game.getFrameWidth()/2-200,680,450,150,"Exit",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));

    }
    public void draw(Graphics g)  {
        g.drawImage(menu,0,0,Game.getFrameWidth(),Game.getFrameHeight(),null);
        g.drawString("The Invaders",Game.getFrameWidth()/2-140,100);
        for (int i = 0; i <buttons.length ; i++) {
            buttons[i].draw(g);

        }

    }
}
