package org.kukharev.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.kukharev.screens.LoadingScreen;
import org.kukharev.screens.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameApplication extends ApplicationAdapter {
    private static final Logger logger = LoggerFactory.getLogger(GameApplication.class);
    private Stage stage;
    private Screen currentScreen;

    @Override
    public void create() {
        logger.info("Game initialization started.");
        stage = new Stage(new ScreenViewport());

        // Set initial screen to LoadingScreen
        setScreen(new LoadingScreen(this));
        Gdx.input.setInputProcessor(stage);
        logger.info("Game initialization completed.");
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (currentScreen != null) {
            currentScreen.render(Gdx.graphics.getDeltaTime());
        }
        stage.act();
        stage.draw();
    }

    public void setScreen(Screen screen) {
        if (currentScreen != null) {
            currentScreen.dispose();
        }
        currentScreen = screen;
        stage.clear();
        stage.addActor(screen);
        logger.info("Screen switched to: " + screen.getClass().getSimpleName());
    }

    @Override
    public void dispose() {
        if (currentScreen != null) {
            currentScreen.dispose();
        }
        stage.dispose();
        logger.info("Game disposed.");
    }
}
