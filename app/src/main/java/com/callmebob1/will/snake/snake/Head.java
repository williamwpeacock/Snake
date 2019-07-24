package com.callmebob1.will.snake.snake;

public class Head extends Body {

    public enum Facing {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Facing direction = Facing.UP;

    public Head(int x, int y) {
        super(null);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        switch (direction) {
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
    }
}
