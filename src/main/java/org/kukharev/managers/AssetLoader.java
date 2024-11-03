package org.kukharev.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.kukharev.screens.SettingsMenu;
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
        assetManager.load("assets/GameAssets.atlas", TextureAtlas.class);
        assetManager.load("assets/backgrounds/MenuBackground.gif", Texture.class);
        assetManager.load("assets/menus/LoadingBar.gif", Texture.class);
        assetManager.finishLoading();
        logger.info("Assets loading complete");
    }

    public TextureAtlas getAtlas(String path) {
        return assetManager.get(path, TextureAtlas.class);
    }

    public boolean isFinished() {
        return assetManager.update();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public <T> T get(String assetName, Class<T> type) {
        return assetManager.get(assetName, type);
    }

    public void dispose() {
        assetManager.dispose();
    }
}
