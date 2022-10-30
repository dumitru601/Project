package Joc.Input;

import Joc.Game;
import Joc.gui.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    public int x,y;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < Game.launcher.buttons.length; i++) {
            Button button = Game.launcher.buttons[i];

            if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {

                    button.click=true;
                    button.triggerEvent();


            }
        }
        if(Game.instruction){
            Button button = Game.launcherInstruction.b;
            if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {

                button.click=true;
                button.triggerEvent();


            }
        }
         else if(Game.pause)
        for (int i = 0; i < Game.launcherPause.buttons.length; i++) {
            Button button = Game.launcherPause.buttons[i];

            if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {

                button.click=true;
                button.triggerEvent();


            }
        }
        if(Game.end){
            for (int i = 0; i < Game.launcherEnd.buttons.length; i++) {
                Button button = Game.launcherEnd.buttons[i];

                if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {

                    button.click=true;
                    button.triggerEvent();


                }
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if(Game.pause)
        for (int i = 0; i < Game.launcherPause.buttons.length; i++) {
            Button button = Game.launcherPause.buttons[i];

            if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {
                button.hoover=true;
            }
            else{
                button.hoover=false;
            }
        }
        for (int i = 0; i < Game.launcher.buttons.length; i++) {
            Button button = Game.launcher.buttons[i];

            if(x>=button.getX() && y>=button.getY() &&x<=button.getX()+button.getWidth() && y<=button.getY()+button.getHeight()) {
                button.hoover=true;
            }
            else{
                button.hoover=false;
            }
        }
    }
}
