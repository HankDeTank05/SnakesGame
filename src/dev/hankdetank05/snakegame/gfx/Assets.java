package dev.hankdetank05.snakegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 8, height = 8, scale = 4;

    public static BufferedImage snakeHead, snakeBody, snakeTail;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/snake_sheet.png"));
        snakeHead = sheet.crop(0, 0, width*scale, height*scale);
        snakeBody = sheet.crop(width*scale, 0, width*scale, height*scale);
        snakeTail = sheet.crop(2*width*scale, 0, width*scale, height*scale);
    }
}
