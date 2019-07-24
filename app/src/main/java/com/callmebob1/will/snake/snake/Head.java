package com.callmebob1.will.snake.snake;

public class Head extends Body {

    enum Facing {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Facing direction = Facing.UP;

    public Head(Body next) {
        super(null);
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
