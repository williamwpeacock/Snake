package com.callmebob1.will.snake.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.callmebob1.will.snake.snake.Body;
import com.callmebob1.will.snake.snake.Food;
import com.callmebob1.will.snake.snake.Head;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InGame extends Scene {

    private Head head;
    private List<Body> snake;
    private boolean dead = false;

    private Food food;
    Random rand = new Random();

    private float gameWidth = 32f, gameHeight;
    private float pixelSize;
    private int pixels;

    private int ticks = 5;
    private int timePerTick = 1000000000/ticks;
    private long timePassed = 0;

    private float touchX, touchY;

    public InGame(SceneManager manager) {
        super(manager, "ingame");

        pixelSize = manager.WIDTH/gameWidth;
        gameHeight = manager.HEIGHT/pixelSize;
        pixels = (int) (gameWidth*gameHeight);

        setup();
    }

    private void setup() {
        head = new Head((int) (gameWidth/2), (int) (gameHeight/2));
        snake = new ArrayList<>();
        snake.add(head);

        int r = rand.nextInt(pixels);
        food = new Food((int) (r%gameWidth), (int) (r/gameWidth));

        dead = false;
    }

    @Override
    public void update(long dt) {
        if (dead) {
            setup();
        } else {
            timePassed += dt;

            while (timePassed > timePerTick) {
                timePassed -= timePerTick;
                for (int i = snake.size() - 1; i >= 0; i--) {
                    snake.get(i).update();
                }

                if (head.x < 0 || head.x > gameWidth-1 || head.y < 0 || head.y > gameHeight-1) dead = true;

                for (int i = 1; i < snake.size(); i++) {
                    if (head.x == snake.get(i).x && head.y == snake.get(i).y) dead = true;
                }

                if (food.update(snake)) {
                    snake.add(new Body(snake.get(snake.size() - 1)));
                    pixels -= 1;
                    int[] f = genFood();
                    food = new Food(f[0], f[1]);
                }
            }
        }
    }

    private int[] genFood() {
        int r = rand.nextInt(pixels);
        int x = (int) (r%gameWidth), y =(int) (r/gameWidth);
        while (!posAvailable(x, y)) {
            r = (r+1)%(pixels-1);
            x = (int) (r%gameWidth);
            y =(int) (r/gameWidth);
        }
        int[] pos = {x, y};
        return pos;
    }

    private boolean posAvailable(int x, int y) {
        for (Body b : snake) {
            if (b.x == x && b.y == y) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        renderBackground(canvas, paint, 0xFF000000);

        food.render(canvas, paint, pixelSize);

        for(Body b : snake) {
            b.render(canvas, paint, pixelSize);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        super.onTouch(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                pressed(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pressed(event);
                break;
            case MotionEvent.ACTION_UP:
                released(event);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                released(event);
                break;
        }
    }

    private void pressed(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
    }

    private void released(MotionEvent event) {
        float dx = event.getX()-touchX, dy = event.getY()-touchY;

        if (Math.abs(dx) < Math.abs(dy)) {
            if (dy < 0) {
                if (head.direction != Head.Facing.DOWN) head.direction = Head.Facing.UP;
            } else {
                if (head.direction != Head.Facing.UP) head.direction = Head.Facing.DOWN;
            }
        } else {
            if (dx < 0) {
                if (head.direction != Head.Facing.RIGHT) head.direction = Head.Facing.LEFT;
            } else {
                if (head.direction != Head.Facing.LEFT) head.direction = Head.Facing.RIGHT;
            }
        }
    }
}
