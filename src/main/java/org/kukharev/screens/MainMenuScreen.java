package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture menuButtonsBackground;
    private final Texture startNewGameButton;
    private final Texture settingsButton;
    private final Texture multiplayerButton;
    private final Texture exitGameButton;
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);

    public MainMenuScreen(SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Texture loading start");
        this.batch = batch;
        this.menuButtonsBackground = new Texture("assets/backgrounds/MenuButtonsBackground.png");
        this.startNewGameButton = new Texture("assets/buttons/StartNewGameButton.png");
        this.settingsButton = new Texture("assets/buttons/SettingsButton.png");
        this.multiplayerButton = new Texture("assets/buttons/MultiplayerButton.png");
        this.exitGameButton = new Texture("assets/buttons/ExitGameButton.png");
        logger.info("Texture loading complete");
    }

    @Override
    public void show() {
        // TODO: Configure buttons and add event listeners
    }

    @Override
    public void render(float delta) {
        logger.info("Texture render start");
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(menuButtonsBackground, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        batch.draw(startNewGameButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 + 100);
        batch.draw(settingsButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        batch.draw(multiplayerButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - 100);
        batch.draw(exitGameButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - 200);
        batch.end();
        logger.info("Texture render complete");
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        menuButtonsBackground.dispose();
        startNewGameButton.dispose();
        settingsButton.dispose();
        multiplayerButton.dispose();
        exitGameButton.dispose();
        logger.info("Texture dispose complete");
    }
}
