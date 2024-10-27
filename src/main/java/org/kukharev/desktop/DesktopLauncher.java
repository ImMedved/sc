package org.kukharev.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.kukharev.GameApp;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("My Game");
        config.setWindowedMode(1280, 720);
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setResizable(false);

        new Lwjgl3Application(new GameApp(), config);
    }
}
