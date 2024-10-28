package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.GameApp;
import org.kukharev.core.GameApplication;
import org.kukharev.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadingScreen implements Screen {
    private final SpriteBatch batch;
    private final AssetLoader assetLoader;
    private final Texture background;
    private final Texture loadingBar;
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);

    public LoadingScreen(SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Loading menu assets load start");
        this.batch = batch;
        this.assetLoader = assetLoader;
        this.background = new Texture("assets/backgrounds/MenuBackground.gif");
        this.loadingBar = new Texture("assets/menus/LoadingBar.gif");
        logger.info("Asset loading complete");
    }

    @Override
    public void show() {
        assetLoader.loadAssets();
    }

    @Override
    public void render(float delta) {
        logger.info("Loading menu render start");
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (!assetLoader.isFinished()) {
            float loadingProgress = assetLoader.getProgress();
            batch.draw(loadingBar, Gdx.graphics.getWidth() / 2 - loadingBar.getWidth() / 2, Gdx.graphics.getHeight() / 4);
        } else {
            /**TODO:
            *   Go to the next screen (main menu) when loading is complete
            **/
        }
        batch.end();
        logger.info("Loading menu render complete");
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
