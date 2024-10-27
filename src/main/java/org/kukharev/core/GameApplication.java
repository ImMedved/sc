package org.kukharev.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.screens.MainMenuScreen;
import org.kukharev.managers.AssetLoader;

public class GameApplication extends Game {
    private SpriteBatch batch;
    private AssetLoader assetLoader;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetLoader = new AssetLoader();
        setScreen(new LoadingScreen(batch, assetLoader)); // We pass both parameters
    }

    public void goToMainMenu() {
        setScreen(new MainMenuScreen(batch, assetLoader)); // We pass the necessary parameters
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetLoader.dispose();
    }
}
