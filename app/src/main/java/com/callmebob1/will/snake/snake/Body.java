package com.callmebob1.will.snake.snake;

public class Body extends WorldObject {

    protected Body next;

    public Body(Body next) {
        this.next = next;
        this.x = -10;
        this.y = -10;

        color = 0xFFFFFFFF;
    }

    public void update() {
        this.x = next.x;
        this.y = next.y;
    }

}
