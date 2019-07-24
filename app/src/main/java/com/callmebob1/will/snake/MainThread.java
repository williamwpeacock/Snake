package com.callmebob1.will.snake;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    private boolean running;
    public Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    @Override
    public void run() {
        long then = System.nanoTime(), now, dt;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int fps = 0;

        while(running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    now = System.nanoTime();
                    dt = now - then;
                    then = now;

                    gameView.update(dt);

                    frames++;
                    if(System.currentTimeMillis() - timer > 1000){
                        timer += 1000;
                        fps = frames;
                        frames = 0;
                    }

                    gameView.render(canvas, fps);

                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

}
