package org.kukharev.utils.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.kukharev.utils.ResourcePaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetLoader {
    private final AssetManager assetManager;
    private static final Logger logger = LoggerFactory.getLogger(AssetLoader.class);

    public AssetLoader() {
        this.assetManager = new AssetManager();
    }

    public void loadAssets() {
        logger.info("Starting AssetLoader loading assets");
        assetManager.load(ResourcePaths.PLAYER_ATLAS, TextureAtlas.class);
        assetManager.load(ResourcePaths.PLAYER_ACTIONS_ATLAS, TextureAtlas.class);
        assetManager.load("assets/backgrounds/MenuBackground.gif", Texture.class);
        assetManager.load("assets/menus/LoadingBar.gif", Texture.class);
        // Добавьте загрузку фона и других необходимых ресурсов
        assetManager.finishLoading();
        logger.info("Assets loading complete");
    }

    public TextureAtlas getPlayerAtlas() {
        return assetManager.get(ResourcePaths.PLAYER_ATLAS, TextureAtlas.class);
    }

    public TextureAtlas getPlayerActionsAtlas() {
        return assetManager.get(ResourcePaths.PLAYER_ACTIONS_ATLAS, TextureAtlas.class);
    }

    public boolean isFinished() {
        return assetManager.update();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void dispose() {
        assetManager.dispose();
    }
}
