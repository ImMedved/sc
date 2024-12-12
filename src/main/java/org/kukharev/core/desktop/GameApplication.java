package org.kukharev.core.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.core.game.LevelManager;
import org.kukharev.screens.menus.LoadingScreen;
import org.kukharev.screens.menus.MainMenuScreen;
import org.kukharev.screens.menus.SettingsMenu;
import org.kukharev.screens.menus.LanguageScreen;
import org.kukharev.screens.gameRun.PauseScreen;
import org.kukharev.screens.gameRun.GameScreen;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameApplication extends Game {
    private SpriteBatch batch;
    private AssetLoader assetLoader;
    private LevelManager levelManager;

    private static final Logger logger = LoggerFactory.getLogger(GameApplication.class);

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetLoader = new AssetLoader();
        levelManager = new LevelManager(this, assetLoader);

        setScreen(new LoadingScreen(this, batch, assetLoader));
    }

    public void startGameAtLevel(String levelName) {
        levelManager.loadLevel(levelName);
        setScreen(new GameScreen(this, batch, assetLoader, levelManager));
    }

    public LevelManager getLevelManager() {
        return levelManager;
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

    public void goPause(GameScreen gameScreen) {
    }
}
