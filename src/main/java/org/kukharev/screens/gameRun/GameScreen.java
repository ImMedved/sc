package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kukharev.core.GameApplication;
import org.kukharev.objects.Player;
import org.kukharev.systems.*;
import org.kukharev.utils.Renderer;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameScreen implements Screen {
    private final Texture backgroundTexture;
    private final OrthographicCamera camera;
    private final Player player;
    private final TextureAtlas playerAtlas;
    private final TextureAtlas playerActionsAtlas;
    private final SystemManager systemManager;
    private final SpriteBatch batch;
    private final GameApplication game;
    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);

    public GameScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Game init start");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerAtlas = assetLoader.getPlayerAtlas();
        playerActionsAtlas = assetLoader.getPlayerActionsAtlas();

        // Инициализируем текстуры для игрока
        TextureRegion idleTexture = playerAtlas.findRegion("idle_1");
        TextureRegion[] walkFrontTextures = loadWalkTextures(playerAtlas, "walk_forward_");
        TextureRegion[] walkRightTextures = loadWalkTextures(playerAtlas, "walk_right_");
        TextureRegion[] walkBackTextures = loadWalkTextures(playerAtlas, "walk_back_");
        TextureRegion[] combatTextures = loadCombatTextures(playerAtlas);
        TextureRegion[] actionTextures = loadActionTextures(playerActionsAtlas);

        player = new Player(idleTexture, walkFrontTextures, walkRightTextures, walkBackTextures, combatTextures, actionTextures);

        // Инициализация SystemManager и добавление систем
        systemManager = new SystemManager();
        initializeSystems();

        this.batch = batch;
        this.game = game;
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");

        logger.info("Game init finished");
    }

    private TextureRegion[] loadWalkTextures(TextureAtlas atlas, String prefix) {
        // Загрузить последовательность кадров для движения (например, walk_forward_1, walk_forward_2 и т.д.)
        TextureRegion[] textures = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            textures[i] = atlas.findRegion(prefix + (i + 1));
        }
        return textures;
    }

    private TextureRegion[] loadCombatTextures(TextureAtlas atlas) {
        TextureRegion[] textures = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            textures[i] = atlas.findRegion("sword_forward_" + (i + 1));
        }
        return textures;
    }

    private TextureRegion[] loadActionTextures(TextureAtlas atlas) {
        TextureRegion[] textures = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            textures[i] = atlas.findRegion("hoe_front_" + (i + 1));
        }
        return textures;
    }

    private void initializeSystems() {
        systemManager.addSystem(new InputSystem());
        systemManager.addSystem(new MovementSystem(player));
        systemManager.addSystem(new CombatSystem(player, player.getCombatTextures()));
        systemManager.addSystem(new ActionSystem(player, player.getActionTextures()));
    }

    @Override
    public void show() {
        initializeLayers();
    }

    private void initializeLayers() {
        // Загружаем ресурсы, атласы, текстуры
    }

    @Override
    public void render(float delta) {
        logger.info("Game render (GameScreen) starts");
        handleInput();

        ScreenUtils.clear(0, 0, 0, 1);

        // Обновляем все системы
        systemManager.updateSystems(delta);

        // Обновляем позицию камеры на игроке
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Рендеринг всех элементов
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

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.goPause();
        }
        // Дополнительные клавиши можно добавить здесь
    }

    private void renderBackground() {
        logger.info("Game BG render starts");
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        player.render(batch);  // Рендеринг игрока с использованием анимации
        batch.end();
    }

    private void renderEffects() {
        logger.info("Game Effects render starts");

        batch.begin();
        // Отрисовка эффектов поверх персонажа
        batch.end();
    }

    private void renderHUD() {
        logger.info("Hud render starts");

        batch.begin();
        // Отрисовка HUD
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {
        // Логика приостановки игры
    }

    @Override
    public void resume() {
        // Логика возобновления игры
    }

    @Override
    public void hide() {
        // Логика скрытия экрана
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        playerActionsAtlas.dispose();
        playerAtlas.dispose();
    }
}
