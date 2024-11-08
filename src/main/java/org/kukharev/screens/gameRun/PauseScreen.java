package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.kukharev.core.GameApplication;
import org.kukharev.screens.MainMenuScreen;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PauseScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture goToMainMenuButtonTexture;

    private final Texture settingsButtonTexture;
    private final Texture exitGameButtonTexture;
    private final Texture backgroundTexture;

    private final Stage stage;
    private final GameApplication game;
    private static final Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);

    public PauseScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Texture loading start");
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");
        this.settingsButtonTexture = new Texture("assets/buttons/SettingsButton.png");
        this.exitGameButtonTexture = new Texture("assets/buttons/ExitGameButton.png");
        this.goToMainMenuButtonTexture = new Texture("assets/buttons/MainMenuButton.png");

        ImageButton settingsButton = createButtonWithSize(settingsButtonTexture);
        ImageButton exitGameButton = createButtonWithSize(exitGameButtonTexture);
        ImageButton goToMainMenuButton = createButtonWithSize(goToMainMenuButtonTexture);

        settingsButton.setPosition(100, 300);
        goToMainMenuButton.setPosition(100, 450);
        exitGameButton.setPosition(100, 150);

        stage.addActor(settingsButton);
        stage.addActor(exitGameButton);
        stage.addActor(goToMainMenuButton);

        addClickListener(settingsButton, "Settings");
        addClickListener(exitGameButton, "Exit");
        addClickListener(goToMainMenuButton, "MainMenu");

        Gdx.input.setInputProcessor(stage);

        logger.info("Texture loading complete");
    }

    private ImageButton createButtonWithSize(Texture texture) {
        ImageButton button = new ImageButton(new TextureRegionDrawable(texture));
        button.setSize((float) 400, (float) 200);
        return button;
    }

    private void addClickListener(ImageButton button, final String action) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleButtonAction(action);
            }
        });
    }

    private void handleButtonAction(String action) {
        switch (action) {
            case "MainMenu":
                System.out.println("Start Game pressed");
                game.goToMainMenu(); // Transition to MainMenuScreen
                dispose();
                break;
            case "Settings":
                System.out.println("Settings pressed");
                game.goToSettingsMenu(); // Transition to MainMenuScreen
                dispose();
                break;
            case "Exit":
                System.out.println("Exit pressed");
                Gdx.app.exit();
                System.exit(0);
                break;
            default:
                System.out.println("Unknown action: " + action);
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        logger.info("Texture render start");
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        settingsButtonTexture.dispose();
        exitGameButtonTexture.dispose();
        goToMainMenuButtonTexture.dispose();
    }
}
