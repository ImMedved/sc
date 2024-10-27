package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import org.kukharev.core.GameApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenu extends Screen {
    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private final GameApplication gameApp;
    private final Skin skin;

    public MainMenu(GameApplication gameApp) {
        this.gameApp = gameApp;
        this.skin = new Skin(Gdx.files.internal("uiskin.json")); // Загружаем Skin

        // Creating buttons
        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Adding buttons to screen
        addActor(newGameButton);
        addActor(settingsButton);
        addActor(exitButton);

        // Set positions
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        newGameButton.setPosition(centerX - newGameButton.getWidth() / 2, centerY + 60);
        settingsButton.setPosition(centerX - settingsButton.getWidth() / 2, centerY);
        exitButton.setPosition(centerX - exitButton.getWidth() / 2, centerY - 60);

        // Button click listeners
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logger.info("Exit button clicked.");
                Gdx.app.exit();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logger.info("Settings button clicked.");
                gameApp.setScreen(new SettingsMenu(gameApp));
            }
        });
    }

    @Override
    public void render(float delta) {
        // Update and draw the stage
        act(delta);
        Batch batch = getStage().getBatch();
        draw(batch, 1.0f);
    }

    @Override
    public void dispose() {
        logger.info("MainMenu disposed.");
    }
}
