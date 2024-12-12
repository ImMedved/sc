package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.kukharev.core.GameApplication;
import org.kukharev.screens.BaseMenuScreen;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PauseScreen extends BaseMenuScreen {
    private static final Logger logger = LoggerFactory.getLogger(PauseScreen.class);
    private final Texture settingsButtonTexture;
    private final Texture exitGameButtonTexture;
    private final Texture goToMainMenuButtonTexture;

    public PauseScreen(GameApplication game, com.badlogic.gdx.graphics.g2d.SpriteBatch batch, AssetLoader assetLoader) {
        super(game, batch, new Texture("assets/backgrounds/MenuBackground.gif"));
        logger.info("PauseScreen init");
        settingsButtonTexture = new Texture("assets/buttons/SettingsButton.png");
        exitGameButtonTexture = new Texture("assets/buttons/ExitGameButton.png");
        goToMainMenuButtonTexture = new Texture("assets/buttons/MainMenuButton.png");

        ImageButton settingsButton = createButtonWithSize(settingsButtonTexture);
        ImageButton exitGameButton = createButtonWithSize(exitGameButtonTexture);
        ImageButton goToMainMenuButton = createButtonWithSize(goToMainMenuButtonTexture);

        settingsButton.setPosition(100,300);
        goToMainMenuButton.setPosition(100,450);
        exitGameButton.setPosition(100,150);

        addClickListener(settingsButton, "Settings");
        addClickListener(exitGameButton, "Exit");
        addClickListener(goToMainMenuButton, "MainMenu");

        stage.addActor(settingsButton);
        stage.addActor(exitGameButton);
        stage.addActor(goToMainMenuButton);
    }

    private ImageButton createButtonWithSize(Texture texture) {
        ImageButton button = new ImageButton(new TextureRegionDrawable(new com.badlogic.gdx.graphics.g2d.TextureRegion(texture)));
        button.setSize(400,200);
        return button;
    }

    private void addClickListener(ImageButton button, final String action) {
        button.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event,float x,float y){
                handleButtonAction(action);
            }
        });
    }

    private void handleButtonAction(String action) {
        switch(action){
            case "MainMenu":
                game.goToMainMenu();
                dispose();
                break;
            case "Settings":
                game.goToSettingsMenu();
                dispose();
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
