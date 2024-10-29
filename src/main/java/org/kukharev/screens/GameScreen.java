package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.core.GameApplication;
import org.kukharev.core.GameWorld;
import org.kukharev.managers.AssetLoader;
import org.kukharev.systems.SystemManager;
import org.kukharev.utils.Renderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen implements Screen {
    private final Texture backgroundTexture;

    private SystemManager systemManager;
    private final SpriteBatch batch;
    private final GameApplication game;
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);

    public GameScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Game init start");
        this.batch = batch;
        this.game = game;
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");

        this.systemManager = new SystemManager();
        initializeSystems();
        logger.info("Game init finished");
    }

    private void initializeSystems() {
        // Добавляем нужные системы в менеджер
        // systemManager.addSystem(new MovementSystem());
        // systemManager.addSystem(new CombatSystem());
        // и так далее...
    }

    private void initializeLayers() {
        // Здесь загружаем все необходимые ресурсы (спрайты, текстуры, атласы)
    }

    @Override
    public void show() {
        initializeLayers();
    }

    @Override
    public void render(float delta) {
        logger.info("Game render starts");
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        systemManager.updateSystems(delta);
        Renderer.render(() -> {
            renderBackground();
            renderLevel();
            renderCharacter();
            renderEffects();
            renderHUD();
        });
        systemManager.renderSystems();
        logger.info("Game render finished");
    }

    private void renderBackground() {
        logger.info("Game BG render starts");
        batch.begin();
        // Отрисовка статичного фона
        batch.end();
    }

    private void renderLevel() {
        logger.info("Game Level render starts");

        batch.begin();
        // Отрисовка уровня
        batch.end();
    }

    private void renderCharacter() {
        logger.info("Game Char render starts");

        batch.begin();
        // Отрисовка персонажа с анимацией из атласа
        batch.end();
    }

    private void renderEffects() {
        logger.info("Game Effects render starts");

        batch.begin();
        // Отрисовка эффектов поверх персонажа
        batch.end();
    }

    private void renderHUD() {
        logger.info("Game render starts");

        batch.begin();
        // Отрисовка HUD
        batch.end();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}
