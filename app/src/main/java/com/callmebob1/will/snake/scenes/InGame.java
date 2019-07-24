package com.callmebob1.will.snake.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class InGame extends Scene {

    public InGame(SceneManager manager) {
        super(manager, "ingame");
    }

    @Override
    public void update(long dt) {

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        renderBackground(canvas, paint, 0xFF000000);

    }

    @Override
    public void onTouch(MotionEvent event) {
        super.onTouch(event);

    }
}
