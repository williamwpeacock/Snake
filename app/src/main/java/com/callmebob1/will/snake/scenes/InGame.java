package com.callmebob1.will.snake.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.callmebob1.will.snake.snake.Food;
import com.callmebob1.will.snake.snake.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class InGame extends Scene {

    private List<WorldObject> objects;
    private float pixelSize;

    public InGame(SceneManager manager) {
        super(manager, "ingame");

        pixelSize = manager.WIDTH/16f;

        objects = new ArrayList<>();
        objects.add(new Food(10, 20));
    }

    @Override
    public void update(long dt) {

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        renderBackground(canvas, paint, 0xFF000000);

        for(WorldObject o : objects) {
            o.render(canvas, paint, pixelSize);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        super.onTouch(event);

    }
}
