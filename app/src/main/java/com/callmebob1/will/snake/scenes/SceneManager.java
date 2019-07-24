package com.callmebob1.will.snake.scenes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    public final int WIDTH, HEIGHT;

    private List<Scene> scenes = new ArrayList<>();
    private Scene currentScene, nextScene;

    public SceneManager(int w, int h) {
        this.WIDTH = w;
        this.HEIGHT = h;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setScene(String id) {
        currentScene = sceneFromID(id);
    }

    public Scene sceneFromID(String id) {
        for (Scene scene : scenes) {
            if (scene.id.equals((id))) {
                return scene;
            }
        }
        return null;
    }

    public void updateScene(long dt) {
        currentScene.update(dt);
    }

    public void renderScene(Canvas canvas, Paint paint) {
        currentScene.render(canvas, paint);
    }

    public void onTouch(MotionEvent event) {
        currentScene.onTouch(event);
    }
}
