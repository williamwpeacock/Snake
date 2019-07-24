package com.callmebob1.will.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.callmebob1.will.snake.scenes.InGame;
import com.callmebob1.will.snake.scenes.SceneManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public final int WIDTH, HEIGHT;

    private MainThread thread;

    private TouchInput touchInput;
    public SceneManager sceneManager;

    public GameView(Context context, int w, int h) {
        super(context);

        this.WIDTH = w;
        this.HEIGHT = h;

        setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY | SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getHolder().addCallback(this);
        setFocusable(true);

        touchInput = new TouchInput(this);
        setOnTouchListener(touchInput);

        sceneManager = new SceneManager(w, h);
        sceneManager.addScene(new InGame(sceneManager));

        sceneManager.setScene("ingame");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(long dt) {
        sceneManager.updateScene(dt);
    }

    public void render(Canvas canvas, int fps) {
        this.draw(canvas);

        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        canvas.drawText("FPS: "+fps, WIDTH-180, 100, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);

        sceneManager.renderScene(canvas, paint);
    }

    public void onTouch(MotionEvent event) {
        sceneManager.onTouch(event);
    }
}
