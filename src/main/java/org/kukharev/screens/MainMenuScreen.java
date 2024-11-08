package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.kukharev.core.GameApplication;
import org.kukharev.core.GlobalSettings;
import org.kukharev.utils.ButtonHoverListener;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Texture buttonTextureSheet;
    private final TextureRegion[][] buttonRegions;
    private final String currentLanguage;
    private final Stage stage;
    private final GameApplication game;
    private static final Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);

    public MainMenuScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Texture loading start");
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        currentLanguage = GlobalSettings.getInstance().getLanguage();

        buttonTextureSheet = new Texture(Gdx.files.internal("assets/buttons.png"));
        buttonRegions = TextureRegion.split(buttonTextureSheet, 1000, 300);
        createButtons();
        Gdx.input.setInputProcessor(stage);
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");

        logger.info("Texture loading complete");
    }

    private void createButtons() {
        // Button creation with hover and press effects
        ImageButton startButton = createAnimatedButton("StartNewGame", 0, 0, "StartGame");
        ImageButton settingsButton = createAnimatedButton("Settings", 0, 1, "Settings");
        ImageButton multiplayerButton = createAnimatedButton("Multiplayer", 2, 0, "MP");
        ImageButton exitGameButton = createAnimatedButton("ExitGame", 2, 1, "Exit");

        // Positioning buttons
        startButton.setPosition(100, 600);
        settingsButton.setPosition(100, 450);
        multiplayerButton.setPosition(100, 300);
        exitGameButton.setPosition(100, 150);

        stage.addActor(startButton);
        stage.addActor(settingsButton);
        stage.addActor(multiplayerButton);
        stage.addActor(exitGameButton);
    }

    private ImageButton createAnimatedButton(String baseName, int row, int col, String action) {
        int languageOffset = currentLanguage.equals("ru") ? 2 : 0;

        // Main and hover regions
        TextureRegion normalRegion = buttonRegions[row][col + languageOffset];
        TextureRegion hoverRegion = buttonRegions[row + 1][col + languageOffset];

        ImageButton button = new ImageButton(new TextureRegionDrawable(normalRegion));
        button.setSize(500, 150);

        // Adding hover and press effect listener
        button.addListener(new ButtonHoverListener(button, normalRegion, hoverRegion, action, this::handleButtonAction));

        return button;
    }

    private void handleButtonAction(String action) {
        switch (action) {
            case "StartGame":
                System.out.println("Start Game pressed");
                game.goToNewGame();
                dispose();
                break;
            case "Settings":
                System.out.println("Settings pressed");
                game.goToSettingsMenu();
                dispose();
                break;
            case "MP":
                System.out.println("Multiplayer pressed");
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
        logger.info("Rendering textures");
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
        buttonTextureSheet.dispose();
    }
}