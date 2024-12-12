package org.kukharev.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadingScreen implements Screen {
    private final SpriteBatch batch;
    private final AssetLoader assetLoader;
    private final Texture background;
    private final Texture loadingBar;
    private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
    private final GameApplication game;

    public LoadingScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Loading menu assets start");
        this.batch = batch;
        this.game = game;
        this.assetLoader = assetLoader;
        this.background = new Texture("assets/backgrounds/MenuBackground.gif");
        this.loadingBar = new Texture("assets/menus/LoadingBar.gif");
        logger.info("Asset loading request done");
    }

    @Override
    public void show() {
        assetLoader.loadAssets();
    }

    @Override
    public void render(float delta) {
        logger.info("Loading menu render");
        com.badlogic.gdx.utils.ScreenUtils.clear(0,0,0,1);
        batch.begin();
        batch.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (!assetLoader.isFinished()) {
            batch.draw(loadingBar, Gdx.graphics.getWidth()/2f - loadingBar.getWidth()/2f,
                    Gdx.graphics.getHeight()/4f);
        } else {
            game.goToMainMenu();
            dispose();
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
