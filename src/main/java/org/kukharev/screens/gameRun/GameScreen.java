package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import org.kukharev.core.GameApplication;
import org.kukharev.core.GameWorld;
import org.kukharev.core.GameRenderer;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen implements Screen {
    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);
    private final GameApplication game;
    private final GameWorld gameWorld;
    private final GameRenderer gameRenderer;

    public GameScreen(GameApplication game, com.badlogic.gdx.graphics.g2d.SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("GameScreen init");
        this.game = game;
        this.gameWorld = new GameWorld(assetLoader);
        this.gameRenderer = new GameRenderer(gameWorld);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        handleInput();
        gameWorld.update(delta);
        gameRenderer.render(gameWorld);
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.goPause();
        }
        // Можно добавить прочий ввод
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.getCamera().setToOrtho(false, width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        gameWorld.dispose();
        gameRenderer.dispose();
    }
}
