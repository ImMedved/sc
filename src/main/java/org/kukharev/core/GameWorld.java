package org.kukharev.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.kukharev.objects.Player;
import org.kukharev.objects.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameWorld {
    private final TextureAtlas atlas;

    private final OrthographicCamera camera;
    private final Player player;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final Texture backgroundTexture;
    private final Batch batch;
    private final TextureRegion playerTexture;
    private static final Logger logger = LoggerFactory.getLogger(GameWorld.class);

    public GameWorld(TextureAtlas atlas, Player player) {
        logger.info("Starting GameWorld init");
        // Initializing the camera
        this.atlas = atlas;
        this.player = player;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        // Loading a level map and rendering it
        map = new TmxMapLoader().load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // Loading background
        backgroundTexture = new Texture("assets/background.png");
        playerTexture = new TextureRegion();
        // Player initialization
        player = new Player(
                atlas.findRegion("idle_1"),
                loadWalkTextures(atlas, "walk_forward_"),
                loadWalkTextures(atlas, "walk_right_"),
                loadWalkTextures(atlas, "walk_back_"),
                loadCombatTextures(atlas),
                loadActionTextures(atlas)
        );
        // Example of a trigger
        Trigger[] triggers = new Trigger[]{
                new Trigger(400, 300, 50, 50, "nextLevel")
        };
        logger.info("GameWorld inited");
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
        // Player update
        player.update(delta);

        // Camera update
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        //TODO:
        //  Add triggers
        //  Example:
        //  for (Trigger trigger : triggers) {
        //      if (trigger.isPlayerInRange(player)) {
        //          trigger.activate();
        //      }
        //  }

    }

    public void render() {
        // Background render
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Map render
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Player render
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render((SpriteBatch) batch);
        batch.end();
        logger.info("GameWorld render complete");
    }

    public void dispose() {
        // Resource release
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
