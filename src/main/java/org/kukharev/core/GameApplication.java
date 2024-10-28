package org.kukharev.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.screens.MainMenuScreen;
import org.kukharev.managers.AssetLoader;
import org.kukharev.screens.SettingsMenu;
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
        setScreen(new LoadingScreen(batch, assetLoader)); // We pass both parameters
    }

    public void goToMainMenu() {
        logger.info("Running goToMainMenu method of the GameApplication");
        setScreen(new MainMenuScreen(batch, assetLoader)); // We pass the necessary parameters
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetLoader.dispose();
    }
}
