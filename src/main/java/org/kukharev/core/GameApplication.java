package org.kukharev.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.screens.MainMenuScreen;
import org.kukharev.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameApplication extends Game {
    private SpriteBatch batch;
    private AssetLoader assetLoader;
    private static final Logger logger = LoggerFactory.getLogger(GameApplication.class);

    @Override
    public void create() {
        logger.info("Starting GameApplication");
        batch = new SpriteBatch();
        assetLoader = new AssetLoader();
        setScreen(new LoadingScreen(this, batch, assetLoader)); // Pass `this` as the GameApplication instance
    }

    public void goToMainMenu() {
        logger.info("Transitioning to MainMenuScreen");
        setScreen(new MainMenuScreen(batch, assetLoader));
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetLoader.dispose();
    }
}
