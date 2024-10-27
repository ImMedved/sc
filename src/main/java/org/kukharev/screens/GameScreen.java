package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kukharev.core.GameWorld;

public class GameScreen extends Screen {
    private final GameWorld gameWorld;

    public GameScreen() {
        gameWorld = new GameWorld();
    }

    @Override
    public void render(float delta) {
        // Очистка экрана
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Обновление и рендеринг игрового мира
        gameWorld.update(delta);
        gameWorld.render();
    }

    @Override
    public void dispose() {
        // Освобождение ресурсов игрового мира
        gameWorld.dispose();
    }
}
