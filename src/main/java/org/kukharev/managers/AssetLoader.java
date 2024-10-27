package org.kukharev.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader {
    private final AssetManager assetManager;

    public AssetLoader() {
        this.assetManager = new AssetManager();
    }

    public void loadAssets() {
        assetManager.load("GameAssets.atlas", TextureAtlas.class);
        assetManager.load("MenuBackground.gif", Texture.class);
        assetManager.load("LoadingBar.png", Texture.class);
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
