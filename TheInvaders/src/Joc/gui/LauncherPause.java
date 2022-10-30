package Joc.gui;

import Joc.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class LauncherPause implements ILauncher {
    public Button []buttons;
    public LauncherPause() throws IOException {
        buttons= new Button[4];
        buttons[0]=new Button(Game.getFrameWidth()/2-200,200,450,150,"Resume",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[1]=new Button(Game.getFrameWidth()/2-200,360,450,150,"New Game",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[2]=new Button(Game.getFrameWidth()/2-200,520,450,150,"Instruction",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
        buttons[3]=new Button(Game.getFrameWidth()/2-200,680,450,150,"Exit",
                ImageIO.read(getClass().getResourceAsStream("/buttons.png")));
    }
    public void draw(Graphics g)  {
        for (int i = 0; i <buttons.length ; i++) {
            buttons[i].draw(g);
        }

    }
}
