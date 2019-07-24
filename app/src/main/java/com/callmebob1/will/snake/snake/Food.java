package com.callmebob1.will.snake.snake;

import java.util.List;

public class Food extends WorldObject {

    public Food(int x, int y) {
        this.x = x;
        this.y = y;

        this.color = 0xFFFF0000;
    }

    public boolean update(List<Body> snake) {
        Body head = snake.get(0);
        if (head.x == x && head.y == y) {
            return true;
        } else {
            return false;
        }
    }

}
