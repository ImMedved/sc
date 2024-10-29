package org.kukharev.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Renderer {
    private static final float FRAME_RATE = 1 / 60f;

    public static void render(Runnable renderFunction) {
        // Убираем логику накопителя для упрощения
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Запуск рендеринга
        renderFunction.run();
    }
}
