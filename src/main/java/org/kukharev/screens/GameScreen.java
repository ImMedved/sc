package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kukharev.core.GameWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen extends Screen {
    private final GameWorld gameWorld;
    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);


    public GameScreen() {
        gameWorld = new GameWorld();
    }

    @Override
    public void render(float delta) {
        // Screen cleaning
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Updating and rendering the game world
        gameWorld.update(delta);
        gameWorld.render();
        logger.info("GameWorld loading complete");
    }

    @Override
    public void dispose() {
        // Freeing up resources in the game world
        gameWorld.dispose();
    }
}
