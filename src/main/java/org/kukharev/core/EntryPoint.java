package org.kukharev.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class EntryPoint {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Game Project");
        config.setWindowedMode(1280, 720);
        config.setResizable(true);

        new Lwjgl3Application(new GameApplication(), config);
    }
}
