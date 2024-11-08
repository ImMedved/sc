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
import org.kukharev.managers.AssetLoader;
import org.kukharev.utils.ButtonHoverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Texture buttonTextureSheet;
    private final TextureRegion[][] buttonRegions;
    private final Stage stage;
    private final GameApplication game;
    private final String currentLanguage;
    private static final Logger logger = LoggerFactory.getLogger(LanguageScreen.class);

    public LanguageScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        logger.info("Loading textures");
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        currentLanguage = GlobalSettings.getInstance().getLanguage();

        buttonTextureSheet = new Texture(Gdx.files.internal("assets/buttons.png"));
        buttonRegions = TextureRegion.split(buttonTextureSheet, 1000, 300);
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");

        createButtons();
        Gdx.input.setInputProcessor(stage);
        logger.info("Texture loading complete");
    }

    private void createButtons() {
        // Creating buttons for language selection and back action
        ImageButton ruButton = createAnimatedButton("ruButton", 6, 1, "ru");
        ImageButton enButton = createAnimatedButton("enButton", 8, 0, "en");
        ImageButton backButton = createAnimatedButton("Back", 8, 1, "Back");

        // Positioning buttons
        enButton.setPosition(100, 600);
        ruButton.setPosition(100, 450);
        backButton.setPosition(100, 150);

        // Adding buttons to the stage
        stage.addActor(ruButton);
        stage.addActor(enButton);
        stage.addActor(backButton);
    }

    private ImageButton createAnimatedButton(String action, int row, int col, String actionName) {
        int languageOffset = currentLanguage.equals("ru") ? 2 : 0;

        // Assign main and hover textures based on language and animation row
        TextureRegion normalRegion = buttonRegions[row][col + languageOffset];
        TextureRegion hoverRegion = buttonRegions[row + 1][col + languageOffset];

        ImageButton button = new ImageButton(new TextureRegionDrawable(normalRegion));
        button.setSize(500, 150);

        // Adding listener for hover effect and button action
        button.addListener(new ButtonHoverListener(button, normalRegion, hoverRegion, actionName, this::handleButtonAction));

        return button;
    }

    private void handleButtonAction(String action) {
        switch (action) {
            case "ru":
                logger.info("Russian language button pressed");
                GlobalSettings.getInstance().setLanguage("ru");
                game.goLanguage();
                break;
            case "en":
                logger.info("English language button pressed");
                GlobalSettings.getInstance().setLanguage("en");
                game.goLanguage();
                break;
            case "Back":
                logger.info("Back button pressed. Returning to Settings Menu.");
                game.goToSettingsMenu();
                break;
            default:
                logger.warn("Unknown action: " + action);
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
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
