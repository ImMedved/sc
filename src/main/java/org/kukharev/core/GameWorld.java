package org.kukharev.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.kukharev.utils.Player;
import org.kukharev.utils.Trigger;

public class GameWorld {
    private OrthographicCamera camera;
    private Player player;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Texture backgroundTexture;
    private Trigger[] triggers;
    private Batch batch;

    public GameWorld() {
        // Инициализация камеры
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        // Загрузка карты уровня и её рендера
        map = new TmxMapLoader().load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // Загрузка фона
        backgroundTexture = new Texture("assets/background.png");

        // Инициализация игрока
        player = new Player("assets/player.gif", 100, 100);

        // Пример триггера
        triggers = new Trigger[]{
                new Trigger(400, 300, 50, 50, "nextLevel")
        };
    }

    public void update(float delta) {
        // Обновление игрока
        player.update(delta);

        // Обновление камеры
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
    }

    public void render() {
        // Рендер фона
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Рендер карты
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Рендер игрока
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();
    }

    public void dispose() {
        // Освобождение ресурсов
        batch.dispose();
        map.dispose();
        mapRenderer.dispose();
        backgroundTexture.dispose();
        player.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }
}
