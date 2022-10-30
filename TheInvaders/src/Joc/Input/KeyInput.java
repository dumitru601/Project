package Joc.Input;

import Entity.Entity;
import Joc.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for( Entity en: Game.handler.entity)
        {
            switch (key) {
                case KeyEvent.VK_W:
                    if(!en.jumping) {
                        en.jumping=true;
                        en.gravity = 5.0;
                        en.attack=false;
                    }
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(-5);
                    en.facing = 0;
                    en.attack=false;
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(5);
                    en.facing= 1;
                    en.attack=false;
                    break;
                case KeyEvent.VK_SPACE:
                    en.attack=true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    Game.pause=true;
                    Game.playing=false;
                    break;
            }
        }

    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for( Entity en: Game.handler.entity)
        {
            switch (key) {
                case KeyEvent.VK_W:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_S:
                    en.setVelY(0);
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(0);
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(0);
                    break;
                    case KeyEvent.VK_SPACE:
                    en.setVelX(0);
                    en.attack=false;
                    break;

            }
        }
    }
    public void keyTyped(KeyEvent e) {

    }
}
