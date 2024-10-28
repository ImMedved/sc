package org.kukharev.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLoader {
    private static final Logger logger = LoggerFactory.getLogger(ResourceLoader.class);
    private static final AssetManager assetManager = new AssetManager();

    public static void loadAllResources() {
        assetManager.clear();
        logger.info("Loading textures...");
        assetManager.load("assets/background.jpg", Texture.class);
        assetManager.load("assets/loading.gif", Texture.class);
        // Add other resources as needed
        assetManager.finishLoading();
        logger.info("All resources loaded.");
    }

    public static <T> T getResource(String resource, Class<T> type) {
        return assetManager.get(resource, type);
    }

    public static void dispose() {
        assetManager.dispose();
        logger.info("Resources disposed.");
    }
}
