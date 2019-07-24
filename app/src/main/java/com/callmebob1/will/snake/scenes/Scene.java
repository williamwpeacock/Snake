package com.callmebob1.will.snake.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    public SceneManager manager;
    public String id;

    protected List<Scene> backgroundScenes = new ArrayList<>();
    //protected List<GUIComponent> guiComponents = new ArrayList<>();

    public Scene (SceneManager manager, String id) {
        this.manager = manager;
        this.id = id;
    }

    public abstract void update(long dt);
    public abstract void render(Canvas canvas, Paint paint);

    public void renderBackground(Canvas canvas, Paint paint, int color) {
        for (Scene scene : backgroundScenes) {
            scene.render(canvas, paint);
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRect(0, 0, manager.WIDTH, manager.HEIGHT, paint);
    }

    /*
    public void renderGUI(Canvas canvas, Paint paint) {
        for (GUIComponent component : guiComponents) {
            component.render(canvas, paint, properties);
        }
    }
    */

    public void onTouch(MotionEvent event) {
        /*
        for (GUIComponent component : guiComponents) {
            component.onTouch(event);
        }
        */
    }
}
