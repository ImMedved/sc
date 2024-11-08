package org.kukharev.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.screens.LanguageScreen;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.screens.MainMenuScreen;
import org.kukharev.screens.SettingsMenu;
import org.kukharev.screens.gameRun.GameScreen;
import org.kukharev.screens.gameRun.PauseScreen;
import org.kukharev.utils.managers.AssetLoader;
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
        setScreen(new MainMenuScreen(this, batch, assetLoader));
    }

    public void goToSettingsMenu() {
        logger.info("Transitioning to Settings Menu");
        setScreen(new SettingsMenu(this,batch, assetLoader));
    }

    public void goPause() {
        logger.info("Transitioning to Pause Screen");
        setScreen(new PauseScreen(this,batch, assetLoader));
    }

    public void goLanguage() {
        logger.info("Transitioning to Language settings Menu");
        setScreen(new LanguageScreen(this,batch, assetLoader));
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetLoader.dispose();
    }


    public void goToNewGame() {
        logger.info("Transitioning to Game Screen");
        setScreen(new GameScreen(this,batch, assetLoader));
    }
}
