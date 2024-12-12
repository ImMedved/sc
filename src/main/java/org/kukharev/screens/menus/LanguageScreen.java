package org.kukharev.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.kukharev.core.desktop.GameApplication;
import org.kukharev.core.game.GlobalSettings;
import org.kukharev.utils.ButtonHoverListener;
import org.kukharev.utils.managers.AssetLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageScreen extends BaseMenuScreen {
    private static final Logger logger = LoggerFactory.getLogger(LanguageScreen.class);
    private final Texture buttonTextureSheet;
    private final TextureRegion[][] buttonRegions;
    private final String currentLanguage;

    public LanguageScreen(GameApplication game, com.badlogic.gdx.graphics.g2d.SpriteBatch batch, AssetLoader assetLoader) {
        super(game, batch, new Texture("assets/backgrounds/MenuBackground.gif"));
        currentLanguage = GlobalSettings.getInstance().getLanguage();
        buttonTextureSheet = new Texture(Gdx.files.internal("assets/textures/buttons.png"));
        buttonRegions = TextureRegion.split(buttonTextureSheet,1000,300);
        createButtons();
    }

    private void createButtons() {
        ImageButton ruButton = createAnimatedButton("ru",6,1);
        ImageButton enButton = createAnimatedButton("en",8,0);
        ImageButton backButton = createAnimatedButton("Back",8,1);

        enButton.setPosition(100,600);
        ruButton.setPosition(100,450);
        backButton.setPosition(100,150);

        stage.addActor(ruButton);
        stage.addActor(enButton);
        stage.addActor(backButton);
    }

    private ImageButton createAnimatedButton(String actionName, int row, int col) {
        int langOffset = currentLanguage.equals("ru") ? 2 : 0;
        TextureRegion normalRegion = buttonRegions[row][col + langOffset];
        TextureRegion hoverRegion = buttonRegions[row+1][col + langOffset];

        ImageButton button = new ImageButton(new TextureRegionDrawable(normalRegion));
        button.setSize(500,150);
        button.addListener(new ButtonHoverListener(button, normalRegion, hoverRegion, actionName, this::handleButtonAction));
        return button;
    }

    private void handleButtonAction(String action) {
        switch(action) {
            case "ru":
                GlobalSettings.getInstance().setLanguage("ru");
                game.goLanguage();
                break;
            case "en":
                GlobalSettings.getInstance().setLanguage("en");
                game.goLanguage();
                break;
            case "Back":
                game.goToSettingsMenu();
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        buttonTextureSheet.dispose();
    }
}
