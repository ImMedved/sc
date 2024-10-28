package org.kukharev.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.kukharev.core.GameApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesktopLauncher {
    private static final Logger logger = LoggerFactory.getLogger(DesktopLauncher.class);

    public static void main(String[] args) {
        logger.info("Starting app");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("My Game");
        config.setWindowedMode(1280, 720);
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setResizable(false);
        logger.info("App config complete");

        new Lwjgl3Application(new GameApplication(), config);
    }
}
