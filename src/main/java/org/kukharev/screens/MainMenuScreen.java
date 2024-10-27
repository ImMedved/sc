package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.managers.AssetLoader;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture menuButtonsBackground;
    private final Texture startNewGameButton;
    private final Texture settingsButton;
    private final Texture multiplayerButton;
    private final Texture exitGameButton;

    public MainMenuScreen(SpriteBatch batch, AssetLoader assetLoader) {
        this.batch = batch;
        this.menuButtonsBackground = new Texture("backgrounds/MenuButtonsBackground.png)");
        this.startNewGameButton = new Texture("buttons/StartNewGameButton.png)");
        this.settingsButton = new Texture("buttons/SettingsButton.png)");
        this.multiplayerButton = new Texture("buttons/MultiplayerButton.png)");
        this.exitGameButton = new Texture("buttons/ExitGameButton.png)");
    }

    @Override
    public void show() {
        // TODO: Configure buttons and add event listeners
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(menuButtonsBackground, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        batch.draw(startNewGameButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 + 100);
        batch.draw(settingsButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        batch.draw(multiplayerButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - 100);
        batch.draw(exitGameButton, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - 200);
        batch.end();
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
    }
}
