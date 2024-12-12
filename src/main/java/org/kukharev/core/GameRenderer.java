package org.kukharev.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.Texture;
import org.kukharev.objects.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameRenderer {
    private final SpriteBatch batch;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final Texture backgroundTexture;
    private static final Logger logger = LoggerFactory.getLogger(GameRenderer.class);

    public GameRenderer(GameWorld gameWorld) {
        batch = new SpriteBatch();
        mapRenderer = new OrthogonalTiledMapRenderer(gameWorld.getMap());

        // Загрузка фона (нужен статический фон, соответствующий миру)
        backgroundTexture = new Texture("assets/background.png");
    }

    public void render(GameWorld gameWorld) {
        // Очистка экрана
        Gdx.gl.glClear(com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT | com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT);

        // Рендер фона
        batch.setProjectionMatrix(gameWorld.getCamera().combined);
        batch.begin();
        batch.draw(backgroundTexture,
                gameWorld.getCamera().position.x - Gdx.graphics.getWidth()/2,
                gameWorld.getCamera().position.y - Gdx.graphics.getHeight()/2,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        batch.end();

        // Рендер карты
        mapRenderer.setView(gameWorld.getCamera());
        mapRenderer.render();

        // Рендер игрока
        Player player = gameWorld.getPlayer();
        batch.setProjectionMatrix(gameWorld.getCamera().combined);
        batch.begin();
        player.render(batch);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        mapRenderer.dispose();
    }
}
