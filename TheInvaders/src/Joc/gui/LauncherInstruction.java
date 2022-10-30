package Joc.gui;

import Joc.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LauncherInstruction implements ILauncher {
    public Button b;
    BufferedImage instruction;
    public LauncherInstruction(){
        try{
            b=new Button(Game.getFrameWidth()/2-200,700,450,150,"Return",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
            instruction=ImageIO.read(getClass().getResourceAsStream("/instructions.png"));
            }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void draw(Graphics g){
        g.drawImage(instruction,0,0,Game.getFrameWidth(),Game.getFrameHeight(),null);
        b.draw(g);
    }

}
