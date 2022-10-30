package Joc;

import Entity.Entity;
import Exceptions.InvalidPointMap;
import Tile.Tile;
import Tile.Wall;
import Tile.Rock;
import Tile.Coin;
import Tile.Heart;
import Tile.Trophy;
import Tile.*;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Handler {
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();

    public  Handler() {
        createLevel("/map1.txt");
    }
    public void draw (Graphics g) {
        for (Entity en:entity) {
            en.draw(g);
        }
        for (Tile ti :tile) {
            ti.draw(g);
        }
    }

    public void update () throws IOException {
        for (int i = 0; i <entity.size() ; i++) {
            Entity en = entity.get(i);
            en.update();
        }

        for (Tile ti :tile) {
            ti.update();
        }
    }
    public void addEntity(Entity en) {
        entity.add(en);
    }
    public void removeEntity(Entity en) {
        entity.remove(en);
    }

    public void addTile(Tile en) {
        tile.add(en);
    }
    public void removeTile(Tile en) {
        tile.remove(en);
    }
    
    public  void createLevel(String source) {
        try{int mat[][]=new int [14][50];
        String []v=null;
        Scanner input = new Scanner(Handler.class.getResourceAsStream(source));
        for (int i = 0; i < 14; i++) {
            if(input.hasNextLine()) {
                v=input.nextLine().split(" ");
            }
            for (int j = 0; j <50 ; j++) {
                mat[i][j]=Integer.parseInt(v[j]);
            }
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j <50 ; j++) {
                if(i<0 && i>6){
                    throw new InvalidPointMap("Punct invalid pe mapa");
                }
                if(mat[i][j]==1) {
                    addTile(new Wall(j*50,i*50,50,50,true,Id.wall,this));
                }
                if(mat[i][j]==2) {
                    addTile(new Rock(j*50,i*50,50,50,true,Id.rock,this));

                }
                if(mat[i][j]==3)
                {
                    addTile(new Coin(j*50,i*50,50,50,true,Id.coin,this));
                }
                if(mat[i][j]==4)
                {
                    addTile(new Heart(j*50,i*50,50,50,true,Id.heart,this));
                }
                if(mat[i][j]==5)
                {
                    addTile(new Trophy(j*50,i*50,50,50,true,Id.trophy,this));
                }
                if(mat[i][j]==6)
                {
                    addTile(new Bush(j*50,i*50,50,50,true,Id.bush,this));
                }
            }

        }}
        catch (InvalidPointMap e) {
            System.out.println(e.getMessage());
        }
    }
    public void clearLevel() {
        entity.clear();
        tile.clear();
    }

}
