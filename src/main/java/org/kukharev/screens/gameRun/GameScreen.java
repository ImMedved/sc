package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.core.game.GameWorld;
import org.kukharev.core.game.GameRenderer;
import org.kukharev.core.game.LevelManager;
import org.kukharev.utils.managers.AssetLoader;

public class GameScreen implements Screen {
    private final GameApplication game;
    private final GameWorld gameWorld;
    private final GameRenderer gameRenderer;
    private final HUD hud;
    private boolean paused;

    public GameScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader, LevelManager levelManager) {
        this.game = game;
        this.gameWorld = new GameWorld(levelManager);
        this.gameRenderer = new GameRenderer(gameWorld);
        this.hud = new HUD(gameWorld.getPlayer(), gameWorld.getTimeSystem(), gameWorld.getCamera(), batch);
        this.paused = false;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        handleInput();
        if (!paused) {
            gameWorld.update(delta);
        }
        gameRenderer.render(gameWorld);
        hud.render();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = true;
            game.goPause(this); // Передаем текущий экран, чтобы вернуться к нему позже
        }
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
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
