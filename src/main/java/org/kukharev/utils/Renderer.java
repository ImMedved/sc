package org.kukharev.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Renderer {
    private static final float FRAME_RATE = 1 / 60f;

    public static void render(Runnable renderFunction) {
        float accumulator = 0;
        float deltaTime = Gdx.graphics.getDeltaTime();

        accumulator += deltaTime;

        while (accumulator >= FRAME_RATE) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderFunction.run();
            accumulator -= FRAME_RATE;
        }
    }
}
