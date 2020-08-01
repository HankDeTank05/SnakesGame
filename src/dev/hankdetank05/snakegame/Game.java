package dev.hankdetank05.snakegame;

import dev.hankdetank05.snakegame.display.Display;
import dev.hankdetank05.snakegame.gfx.Assets;
import dev.hankdetank05.snakegame.gfx.ImageLoader;
import dev.hankdetank05.snakegame.gfx.SpriteSheet;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        // initialize graphics and setup the game
        display = new Display(title, width, height);
        Assets.init();
    }

    private void update(){

    }

    private void draw(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // clear screen
        g.clearRect(0, 0, width, height);

        //////////////////
        /*  draw here!  */

        g.drawImage(Assets.snakeHead, 0, 0, null);
        g.drawImage(Assets.snakeBody, 1*32, 0, null);
        g.drawImage(Assets.snakeBody, 2*32, 0, null);
        g.drawImage(Assets.snakeBody, 3*32, 0, null);
        g.drawImage(Assets.snakeBody, 4*32, 0, null);
        g.drawImage(Assets.snakeBody, 5*32, 0, null);
        g.drawImage(Assets.snakeTail, 6*32, 0, null);



        /* end drawing! */
        //////////////////
        bs.show();
        g.dispose();
    }

    public void run(){

        init();

        int fps = 120;

        long lastTime = System.nanoTime();
        long lag = 0;
        long NS_PER_UPDATE = 1000000000 / fps;

        // main game loop
        while(running){
            long currentTime = System.nanoTime();
            long elapsed = currentTime - lastTime;
            lastTime = currentTime;
            lag += elapsed;
            // process input here

            while(lag >= NS_PER_UPDATE){
                update();
                lag -= NS_PER_UPDATE;
            }
            draw();
        }

        stop();
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
