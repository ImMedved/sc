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

public class SettingsMenu implements Screen {
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Texture videoButtonTexture;
    private final Texture audioButtonTexture;
    private final Texture languageButtonTexture;
    private final Texture backButtonTexture;

    private final Stage stage;
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);
    private final GameApplication game;

    public SettingsMenu(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());

        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");
        this.videoButtonTexture = new Texture("assets/buttons/VideoSettingsButton.png");
        this.audioButtonTexture = new Texture("assets/buttons/AudioSettingsButton.png");
        this.languageButtonTexture = new Texture("assets/buttons/LanguageSettingsButton.png");
        this.backButtonTexture = new Texture("assets/buttons/BackButton.png");

        ImageButton videoButton = createButtonWithSize(videoButtonTexture, 400, 200);
        ImageButton audioButton = createButtonWithSize(audioButtonTexture, 400, 200);
        ImageButton languageButton = createButtonWithSize(languageButtonTexture, 400, 200);
        ImageButton backButton = createButtonWithSize(backButtonTexture, 400, 200);

        videoButton.setPosition(100, 600);
        audioButton.setPosition(100, 450);
        languageButton.setPosition(100, 300);
        backButton.setPosition(100, 150);

        stage.addActor(videoButton);
        stage.addActor(audioButton);
        stage.addActor(languageButton);
        stage.addActor(backButton);

        addClickListener(videoButton, "Video");
        addClickListener(audioButton, "Audio");
        addClickListener(languageButton, "Language");
        addClickListener(backButton, "Back");

        Gdx.input.setInputProcessor(stage);
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
            case "Video":
                logger.info("Video settings button pressed");
                // TODO: Add video settings logic
                break;
            case "Audio":
                logger.info("Audio settings button pressed");
                // TODO: Add audio settings logic
                break;
            case "Language":
                logger.info("Language settings button pressed");
                // TODO: Add language settings logic
                break;
            case "Back":
                logger.info("Back button pressed. Returning to Main Menu.");
                game.goToMainMenu();
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
        videoButtonTexture.dispose();
        audioButtonTexture.dispose();
        languageButtonTexture.dispose();
        backButtonTexture.dispose();
    }
}
