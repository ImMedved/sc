package org.kukharev.screens;

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
import org.kukharev.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture startNewGameButtonTexture;
    private final Texture settingsButtonTexture;
    private final Texture multiplayerButtonTexture;
    private final Texture exitGameButtonTexture;
    private final Texture backgroundTexture;

    private final Stage stage;
    private final GameApplication game;
    private static final Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);

    public MainMenuScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Texture loading start");
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");
        this.startNewGameButtonTexture = new Texture("assets/buttons/StartNewGameButton.png");
        this.settingsButtonTexture = new Texture("assets/buttons/SettingsButton.png");
        this.multiplayerButtonTexture = new Texture("assets/buttons/MultiplayerButton.png");
        this.exitGameButtonTexture = new Texture("assets/buttons/ExitGameButton.png");

        ImageButton startButton = createButtonWithSize(startNewGameButtonTexture, 400, 200);
        ImageButton settingsButton = createButtonWithSize(settingsButtonTexture, 400, 200);
        ImageButton multiplayerButton = createButtonWithSize(multiplayerButtonTexture, 400, 200);
        ImageButton exitGameButton = createButtonWithSize(exitGameButtonTexture, 400, 200);

        startButton.setPosition(100, 600);
        settingsButton.setPosition(100, 450);
        multiplayerButton.setPosition(100, 300);
        exitGameButton.setPosition(100, 150);

        stage.addActor(startButton);
        stage.addActor(settingsButton);
        stage.addActor(multiplayerButton);
        stage.addActor(exitGameButton);

        addClickListener(startButton, "StartGame");
        addClickListener(settingsButton, "Settings");
        addClickListener(multiplayerButton, "MP");
        addClickListener(exitGameButton, "Exit");

        Gdx.input.setInputProcessor(stage);

        logger.info("Texture loading complete");
    }

    private ImageButton createButtonWithSize(Texture texture, float width, float height) {
        ImageButton button = new ImageButton(new TextureRegionDrawable(texture));
        button.setSize(width, height);
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
            case "StartGame":
                System.out.println("Start Game pressed");
                game.goToNewGame(); // Transition to MainMenuScreen
                dispose();
                break;
            case "Settings":
                System.out.println("Settings pressed");
                game.goToSettingsMenu(); // Transition to MainMenuScreen
                dispose();
                break;
            case "MP":
                System.out.println("MP pressed");
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
        startNewGameButtonTexture.dispose();
        settingsButtonTexture.dispose();
        multiplayerButtonTexture.dispose();
        exitGameButtonTexture.dispose();
    }
}
