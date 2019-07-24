package com.callmebob1.will.snake.snake;

public class Body extends WorldObject {

    protected Body next;
    public boolean end = true;

    public Body(Body next) {
        this.next = next;

        color = 0xFFFFFFFF;
    }

    public void update() {
        this.x = next.x;
        this.y = next.y;
    }

}
