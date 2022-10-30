package Joc;

import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


import Entity.Player;
import Entity.Troll;
import Entity.Troll2;
import Entity.Troll3;
import Entity.Troll4;

import Joc.Graphics.Sprite;
import Joc.Graphics.SpriteSheet;
import Joc.Input.KeyInput;
import Joc.Input.MouseInput;
import Joc.gui.Launcher;
import Joc.gui.LauncherEnd;
import Joc.gui.LauncherInstruction;
import Joc.gui.LauncherPause;


public class Game extends Canvas implements  Runnable{
    public static  final int WIDTH = 420;
    public static  final int HEIGHT = 240;
    public static final int SCALE = 4;
    public static final String TITLE  = "The Invaders";
    private static Game instance;
    static BufferedImage bag=null;
    static BufferedImage coin_img=null;
    private Thread thread;

    private boolean running = false;
    public static boolean playing;
    public static boolean pause;
    public static boolean newGame;
    public static boolean showDeathScreen= false;

    public static Launcher launcher;
    public static LauncherPause launcherPause;
    public static LauncherEnd launcherEnd;
    public static LauncherInstruction launcherInstruction;
    public static Handler handler;

    public  static SpriteSheet sheet;
    public  static SpriteSheet spriteSheet_troll1;
    public  static SpriteSheet spriteSheet_troll2;
    public  static SpriteSheet spriteSheet_troll3;
    public  static SpriteSheet spriteSheet_troll4;

    public  static Sprite[] grass;
    public static Sprite[] player= new Sprite[52];
    public static Sprite[] troll1= new Sprite[30];
    public static Sprite[] troll2= new Sprite[30];
    public static Sprite[] troll3= new Sprite[30];
    public static Sprite[] troll4= new Sprite[21];
    public static boolean win=false;
    public static boolean end=false;
    public static boolean instruction=false;

    public static int num_coin;
    public static int deathScreenTime;
    public static Camera cam;
    public static MouseInput mouseInput;
    public static  int level=1;
    public int cont_win;

    public static void  level2()  {

       try {
           bag = ImageIO.read(Game.class.getResourceAsStream("/fundal.png"));
           handler.addEntity(new Player(50, 550, 50, 50, true, Id.player, handler));
           handler.addEntity(new Troll(1000, 345, 64, 64, true, Id.troll, handler, 885,
                   358, 1120, 445));
           handler.addEntity(new Troll2(790, 39, 64, 64, true, Id.troll, handler, 790,
                   52, 950, 139));
           handler.addEntity(new Troll3(1830, 0, 64, 64, true, Id.troll, handler, 2160,
                   50, 2455, 137));
           handler.addEntity(new Troll4(1835, 388, 64, 64, true, Id.troll, handler, 1830,
                   401, 2070, 488));

       }
       catch (IOException e){
           System.out.println(e.getMessage());
       }
    }
    public static void switchLevel(){
        if(level==1){
            level=2;
        }
        handler.clearLevel();
        handler.createLevel("/map2.txt");

            level2();

    }

    public synchronized void start() {
       if(running) return ;
       running = true;
       thread = new Thread(this,"Thread");
       thread.start();
    }
    private synchronized void stop() {
        if(!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        requestFocus();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (running)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /// Deseneaza elementele grafica in fereastra.
                draw();
                oldTime = curentTime;
            }
        }
    }

    public void resetGame() {
        level=1;
        newGame=false;
        num_coin=0;
        init();
    }
    public void draw(){
        BufferStrategy bs = getBufferStrategy();

        if(bs==null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(bag,0,0,getFrameWidth(),getFrameHeight(),null);
        if(!showDeathScreen) {
            g.setFont(new Font("Courier",Font.BOLD,50));
            g.drawString(String.valueOf(num_coin),160,120);
            g.drawImage(coin_img, 50, 50, 100, 100, null);
        }
        if(showDeathScreen){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier",Font.BOLD,100));
            g.drawImage(player[0].getBufferedImage(),getFrameWidth()/2-100,getFrameHeight()/2-100,120,120,null);
            g.drawString("x "+num_coin,getFrameWidth()/2,getFrameHeight()/2);
            g.drawString("Game Over",getFrameWidth()/2-190,getFrameHeight()/2+100);
        }


        if(newGame){
            resetGame();
        }
        if(instruction){
            launcherInstruction.draw(g);
        }
        else if(playing==true) {

            g.translate(cam.getX(),cam.getY());
            handler.draw(g);
        }
        else if(pause) {
//            System.out.println("pause");
            launcherPause.draw(g);
        }
        else if(win==true){
//            System.out.println("win");
            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier",Font.BOLD,100));
            g.drawImage(player[0].getBufferedImage(),getFrameWidth()/2-100,getFrameHeight()/2-100,120,120,null);
            g.drawString("x "+num_coin,getFrameWidth()/2,getFrameHeight()/2);
            g.drawString("Win Game",getFrameWidth()/2-190,getFrameHeight()/2+100);
        }
        else if (win==false&& end==true){
//            System.out.println("end");
            launcherEnd.draw(g);
        }
        else if(!playing) launcher.draw(g);

        g.dispose();
        bs.show();

    }
    public void update() throws IOException {
        if(instruction==false) {
            if (playing && end == false && win == false)
                handler.update();
            for (Entity e : handler.entity) {
                if (e.getId() == Id.player)
                    cam.update(e);

            }
            if (showDeathScreen) {
                handler.clearLevel();
                deathScreenTime++;
            }
            if (win) {
                cont_win++;
                if (cont_win >= 120) {
                    win = false;
                    end = true;
                    cont_win = 0;
                }
            }

            if (deathScreenTime >= 120) {
                showDeathScreen = false;
                deathScreenTime = 0;
                handler.createLevel("/map1.txt");
                resetGame();
            }
        }
    }
    private Game() {
        Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public static Game getInstance() {
        if(instance==null){
            instance=new Game();
        }
        return instance;
    }
public void reset(){
        instance=null;
}
    private void init()  {
        try {
            bag = ImageIO.read(getClass().getResourceAsStream("/Battleground4.png"));
            coin_img = ImageIO.read(getClass().getResourceAsStream("/coin.png"));

            handler = new Handler();
            sheet = new SpriteSheet("/spritesheet.png", 50);
            spriteSheet_troll1 = new SpriteSheet("/troll1.png", 100);
            spriteSheet_troll2 = new SpriteSheet("/troll2.png", 100);
            spriteSheet_troll3 = new SpriteSheet("/troll3.png", 100);
            spriteSheet_troll4 = new SpriteSheet("/troll4.png", 64);

            cam = new Camera();
            launcher = new Launcher();
            launcherPause = new LauncherPause();
            launcherEnd = new LauncherEnd();
            launcherInstruction=new LauncherInstruction();
            mouseInput = new MouseInput();


            addKeyListener(new KeyInput());
            addMouseListener(mouseInput);
            addMouseMotionListener(mouseInput);

            grass = new Sprite[6];
            grass[0] = new Sprite(sheet, 2, 3);
            grass[1] = new Sprite(sheet, 4, 3);
            grass[2] = new Sprite();
            grass[3] = new Sprite();
            grass[2].setImage(ImageIO.read(getClass().getResourceAsStream("/coin.png")));
            grass[3].setImage(ImageIO.read(getClass().getResourceAsStream("/heart.png")));
            grass[4] = new Sprite();
            grass[4].setImage(ImageIO.read(getClass().getResourceAsStream("/Trophy.png")));
            grass[5] = new Sprite(sheet, 1, 3);
            for (int i = 0; i < 52; i++) {
                if (i < 13)
                    player[i] = new Sprite(sheet, i + 1, 1);
                else if (i < 26) {
                    player[i] = new Sprite(sheet, i + 1 - 13, 2);
                } else if (i < 39) {
                    player[i] = new Sprite(sheet, i + 1 - 26, 4);

                } else {
                    player[i] = new Sprite(sheet, i + 1 - 39, 5);
                }

            }
            for (int i = 0; i < 30; i++) {
                if (i < 10) {
                    troll1[i] = new Sprite(spriteSheet_troll1, i + 1, 1);
                    troll2[i] = new Sprite(spriteSheet_troll2, i + 1, 1);
                    troll3[i] = new Sprite(spriteSheet_troll3, i + 1, 1);
                } else if (i < 20) {
                    troll1[i] = new Sprite(spriteSheet_troll1, i + 1 - 10, 2);
                    troll2[i] = new Sprite(spriteSheet_troll2, i + 1 - 10, 2);
                    troll3[i] = new Sprite(spriteSheet_troll3, i + 1 - 10, 2);
                } else {
                    troll1[i] = new Sprite(spriteSheet_troll1, i + 1 - 20, 3);
                    troll2[i] = new Sprite(spriteSheet_troll2, i + 1 - 20, 3);
                    troll3[i] = new Sprite(spriteSheet_troll3, i + 1 - 20, 3);
                }

            }
            for (int i = 0; i < 21; i++) {
                if (i < 7) {
                    troll4[i] = new Sprite(spriteSheet_troll4, i + 1, 1);
                } else if (i < 14) {
                    troll4[i] = new Sprite(spriteSheet_troll4, i + 1 - 7, 2);
                } else {
                    troll4[i] = new Sprite(spriteSheet_troll4, i + 1 - 14, 3);
                }
            }

            handler.addEntity(new Player(50, 550, 50, 50, true, Id.player, handler));
            handler.addEntity(new Troll4(1000, 340, 64, 64, true, Id.troll, handler, 900,
                    380, 1330, 490));
            handler.addEntity(new Troll2(1800, 170, 64, 64, true, Id.troll, handler, 1786,
                    183, 2066, 500));
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    public static int getFrameWidth() {
        return WIDTH*SCALE;
    }

    public static int  getFrameHeight() {
        return HEIGHT*SCALE;
    }


}
