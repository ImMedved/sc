package org.kukharev;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.managers.AssetLoader;
import org.kukharev.screens.MainMenuScreen;

public class GameApp extends Game {
    private SpriteBatch batch;
    private AssetLoader assetLoader;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetLoader = new AssetLoader();
        setScreen(new LoadingScreen(batch, assetLoader));
    }

        @Override
    public void dispose() {
        batch.dispose();
        assetLoader.dispose();
    }
}
