package org.kukharev.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.kukharev.objects.Player;
import org.kukharev.objects.Trigger;
import org.kukharev.systems.SystemManager;
import org.kukharev.systems.InputSystem;
import org.kukharev.systems.MovementSystem;
import org.kukharev.systems.CombatSystem;
import org.kukharev.systems.ActionSystem;
import org.kukharev.utils.managers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameWorld {
    private final OrthographicCamera camera;
    private final Player player;
    private final TiledMap map;
    private final Trigger[] triggers;
    private final SystemManager systemManager;
    private static final Logger logger = LoggerFactory.getLogger(GameWorld.class);

    public GameWorld(AssetLoader assetLoader) {
        logger.info("Initializing GameWorld");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Загрузка карты
        map = new TmxMapLoader().load("assets/level1.tmx");

        // Инициализация игрока
        TextureAtlas playerAtlas = assetLoader.getPlayerAtlas();
        TextureAtlas playerActionsAtlas = assetLoader.getPlayerActionsAtlas();
        TextureRegion idleTexture = playerAtlas.findRegion("idle_1");
        TextureRegion[] walkFrontTextures = loadWalkTextures(playerAtlas, "walk_forward_");
        TextureRegion[] walkRightTextures = loadWalkTextures(playerAtlas, "walk_right_");
        TextureRegion[] walkBackTextures = loadWalkTextures(playerAtlas, "walk_back_");
        TextureRegion[] combatTextures = loadCombatTextures(playerAtlas);
        TextureRegion[] actionTextures = loadActionTextures(playerActionsAtlas);

        player = new Player(idleTexture, walkFrontTextures, walkRightTextures, walkBackTextures, combatTextures, actionTextures);

        // Пример триггера
        triggers = new Trigger[]{
                new Trigger(400, 300, 50, 50, "nextLevel")
        };

        // Инициализация систем
        systemManager = new SystemManager();
        systemManager.addSystem(new InputSystem());
        systemManager.addSystem(new MovementSystem(player));
        systemManager.addSystem(new CombatSystem(player, player.getCombatTextures()));
        systemManager.addSystem(new ActionSystem(player, player.getActionTextures()));
    }

    private TextureRegion[] loadWalkTextures(TextureAtlas atlas, String prefix) {
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

    public void update(float delta) {
        // Обновление систем (ввод, движение, бой, действия)
        systemManager.updateSystems(delta);

        // Обновление камеры по позиции игрока
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // Проверка триггеров
        for (Trigger trigger : triggers) {
            if (trigger.getArea().overlaps(player.getBounds())) {
                // Активировать триггер, например, загрузить следующий уровень
                // TODO: Реализовать логику перехода уровней
            }
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getMap() {
        return map;
    }

    public void dispose() {
        map.dispose();
        player.dispose();
    }
}
