import Joc.Game;
import java.sql.*;
import javax.swing.*;
import java.io.IOException;

import static Joc.Game.TITLE;


public class Main {
    public static void main(String[] args) throws IOException {
       // DataBase.DataBase.saveDataBase();
        Game game = Game.getInstance();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }


}
