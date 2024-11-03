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
import org.kukharev.core.GlobalSettings;
import org.kukharev.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Texture ruButtonTexture;
    private final Texture enButtonTexture;
    private final Texture backButtonTexture;

    private final Stage stage;
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);
    private final GameApplication game;

    public LanguageScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        this.batch = batch;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        String currentLanguage = GlobalSettings.getInstance().getLanguage();

        String buttonsFolder = "";
        if (currentLanguage == "en") {
            buttonsFolder = "assets/buttons/ENButtons/";
        }else{
            buttonsFolder = "assets/buttons/RUButtons/";
        }
        this.backgroundTexture = new Texture("assets/backgrounds/MenuBackground.gif");
        this.enButtonTexture = new Texture(buttonsFolder + "EnglishLanguageButton.png");
        this.ruButtonTexture = new Texture(buttonsFolder + "RussianLanguageButton.png");
        this.backButtonTexture = new Texture(buttonsFolder + "BackButton.png");

        ImageButton ruButton = createButtonWithSize(ruButtonTexture, 400, 200);
        ImageButton enButton = createButtonWithSize(enButtonTexture, 400, 200);
        ImageButton backButton = createButtonWithSize(backButtonTexture, 400, 200);

        enButton.setPosition(100, 600);
        ruButton.setPosition(100, 450);
        backButton.setPosition(100, 150);

        stage.addActor(ruButton);
        stage.addActor(enButton);
        stage.addActor(backButton);

        addClickListener(ruButton, "ruButton");
        addClickListener(enButton, "enButton");
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
            case "ruButton":
                logger.info("Video settings button pressed");
                GlobalSettings.getInstance().setLanguage("ru");
                game.goLanguage();
                break;
            case "enButton":
                logger.info("Audio settings button pressed");
                GlobalSettings.getInstance().setLanguage("en");
                game.goLanguage();
                break;
            case "Back":
                logger.info("Back button pressed. Returning to Main Menu.");
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
        enButtonTexture.dispose();
        ruButtonTexture.dispose();
        backButtonTexture.dispose();
    }
}
