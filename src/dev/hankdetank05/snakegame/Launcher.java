package dev.hankdetank05.snakegame;

public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Snake Game!", 640, 480);
        game.start();
    }

}
