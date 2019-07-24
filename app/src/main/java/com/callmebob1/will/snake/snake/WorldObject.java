package com.callmebob1.will.snake.snake;

import android.graphics.Canvas;
import android.graphics.Paint;

public class WorldObject {

    public int x, y;
    protected int color = 0xFF000000;

    public void render(Canvas canvas, Paint paint, float size) {
        paint.setColor(color);
        canvas.drawRect(x*size, y*size, (x+1)*size, (y+1)*size, paint);
    }
}
