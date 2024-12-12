package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.screens.menus.BaseMenuScreen;
import org.kukharev.utils.ButtonHoverListener;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PauseScreen extends BaseMenuScreen {
    private static final Logger logger = LoggerFactory.getLogger(PauseScreen.class);
    private final GameScreen gameScreen;
    private Texture goToMainMenuButtonTexture;
    private Texture exitGameButtonTexture;
    private Texture settingsButtonTexture;

    public PauseScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        super(game, batch, new Texture("assets/backgrounds/MenuBackground.gif"));
        this.gameScreen = null; // Если без gameScreen
        createButtons();
    }
    private void createButtons() {
        // Кнопки: Continue, Save, Settings, Exit
        // Пример:
        ImageButton continueButton = createButton("Continue");
        ImageButton saveButton = createButton("Save");
        ImageButton settingsButton = createButton("Settings");
        ImageButton exitButton = createButton("Exit");

        // Расставить по координатам:
        // stage.addActor(...)

        addClickListener(continueButton, "Continue");
        addClickListener(saveButton, "Save");
        addClickListener(settingsButton, "Settings");
        addClickListener(exitButton, "Exit");
    }

    private void addClickListener(ImageButton button, final String action) {
        button.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                handleAction(action);
            }
        });
    }
    private ImageButton createButton(String text) {
        // Загрузить текстуру кнопки или использовать уже имеющуюся
        TextureRegion normal = new TextureRegion(new Texture("assets/buttons/btn_normal.png"));
        TextureRegion hover = new TextureRegion(new Texture("assets/buttons/btn_hover.png"));
        ImageButton button = new ImageButton(new TextureRegionDrawable(normal));
        button.setSize(200,50);
        button.addListener(new ButtonHoverListener(button, normal, hover, text, this::handleAction));
        return button;
    }

    private void handleAction(String action) {
        switch(action) {
            case "Continue":
                game.setScreen(gameScreen);
                gameScreen.setPaused(false);
                break;
            case "Save":
                // логика сохранения
                break;
            case "Settings":
                game.goToSettingsMenu();
                break;
            case "Exit":
                Gdx.app.exit();
                System.exit(0);
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        settingsButtonTexture.dispose();
        exitGameButtonTexture.dispose();
        goToMainMenuButtonTexture.dispose();
    }
}
