package com.callmebob1.will.snake;

import android.view.MotionEvent;
import android.view.View;

public class TouchInput implements View.OnTouchListener {

    public GameView gameView;

    public TouchInput(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gameView.onTouch(event);

        return true;
    }
}
