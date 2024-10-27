package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.GameApp;
import org.kukharev.managers.AssetLoader;

public class LoadingScreen implements Screen {
    private final SpriteBatch batch;
    private final AssetLoader assetLoader;
    private final Texture background;
    private final Texture loadingBar;

    public LoadingScreen(SpriteBatch batch, AssetLoader assetLoader) {
        this.batch = batch;
        this.assetLoader = assetLoader;
        this.background = new Texture("MenuBackground.gif");
        this.loadingBar = new Texture("LoadingBar.png");
    }

    @Override
    public void show() {
        assetLoader.loadAssets();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (!assetLoader.isFinished()) {
            float loadingProgress = assetLoader.getProgress();
            batch.draw(loadingBar, Gdx.graphics.getWidth() / 2 - loadingBar.getWidth() / 2, Gdx.graphics.getHeight() / 4);
        } else {
            /**TODO:
            *   Go to the next screen (main menu) when loading is complete
            *   GameApp.setScreen(new MainMenuScreen(batch, assetLoader));
            **/
        }
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
        background.dispose();
        loadingBar.dispose();
    }
}
