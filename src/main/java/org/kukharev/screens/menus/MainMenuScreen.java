package org.kukharev.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.core.game.GlobalSettings;
import org.kukharev.utils.ButtonHoverListener;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen extends BaseMenuScreen {
    private static final Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);
    private final Texture buttonTextureSheet;
    private final TextureRegion[][] buttonRegions;
    private final String currentLanguage;

    public MainMenuScreen(GameApplication game, SpriteBatch batch, AssetLoader assetLoader) {
        super(game, batch, new Texture("assets/backgrounds/MenuBackground.gif"));
        logger.info("MainMenuScreen init");
        currentLanguage = GlobalSettings.getInstance().getLanguage();
        buttonTextureSheet = new Texture(Gdx.files.internal("assets/textures/buttons.png"));
        buttonRegions = TextureRegion.split(buttonTextureSheet,1000,300);
        createButtons();
    }

    private void createButtons() {
        ImageButton startButton = createAnimatedButton("StartGame",0,0);
        ImageButton settingsButton = createAnimatedButton("Settings",0,1);
        ImageButton multiplayerButton = createAnimatedButton("MP",2,0);
        ImageButton exitGameButton = createAnimatedButton("Exit",2,1);

        startButton.setPosition(100,600);
        settingsButton.setPosition(100,450);
        multiplayerButton.setPosition(100,300);
        exitGameButton.setPosition(100,150);

        stage.addActor(startButton);
        stage.addActor(settingsButton);
        stage.addActor(multiplayerButton);
        stage.addActor(exitGameButton);
    }

    private ImageButton createAnimatedButton(String action, int row, int col) {
        int langOffset = currentLanguage.equals("ru") ? 2 : 0;
        TextureRegion normalRegion = buttonRegions[row][col + langOffset];
        TextureRegion hoverRegion = buttonRegions[row+1][col + langOffset];

        ImageButton button = new ImageButton(new TextureRegionDrawable(normalRegion));
        button.setSize(500,150);

        button.addListener(new ButtonHoverListener(button, normalRegion, hoverRegion, action, this::handleButtonAction));
        return button;
    }

    private void handleButtonAction(String action) {
        switch(action) {
            case "StartGame":
                game.startGameAtLevel("level1.tmx");
                dispose();
                break;
            case "Settings":
                game.goToSettingsMenu();
                dispose();
                break;
            case "MP":
                // TODO: Multiplayer logic
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
        buttonTextureSheet.dispose();
    }
}
