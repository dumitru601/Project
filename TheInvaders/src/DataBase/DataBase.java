package DataBase;

import Joc.Game;

import java.sql.*;
public class DataBase {
    public static Connection c=null;
    public static Statement stmt= null;

    public static void saveDataBase(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TheInvaders.db");
            stmt=c.createStatement();
            String sql = "UPDATE Joc set x =\"" + Game.handler.entity.get(0).getX() + "\", " +
                    "y = \"" + Game.handler.entity.get(0).getY() + "\", " +
                    "score = \"" + Game.num_coin + "\", " +
                    "'viata' = \"" + Game.handler.entity.get(0).health + "\"," +
                    "level = \"" + Game.level + "\" WHERE rowid=1";
            stmt.execute(sql);
            stmt.close();
            c.close();
            System.out.println("Am salvat");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void loadDataBase(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TheInvaders.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Joc");
            Game.handler.entity.get(0).setX(rs.getInt("x"));
            Game.handler.entity.get(0).setY(rs.getInt("y"));
            Game.handler.entity.get(0).health=rs.getInt("viata");
            Game.level=rs.getInt("level");
            rs.close();
            stmt.close();
            c.close();
    }
        catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

}
}
