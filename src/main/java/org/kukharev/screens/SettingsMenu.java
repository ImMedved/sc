package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import org.kukharev.core.GameApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingsMenu extends Screen {
    private static final Logger logger = LoggerFactory.getLogger(SettingsMenu.class);
    private final GameApplication gameApp;

    public SettingsMenu(GameApplication gameApp) {
        this.gameApp = gameApp;

        // Создание стилей для элементов интерфейса
        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Загрузка скина из файла, который должен быть добавлен в проект
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = font;

        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.font = font;

        // Создание элементов интерфейса
        CheckBox fullscreenCheckBox = new CheckBox("Fullscreen", checkBoxStyle);
        CheckBox soundCheckBox = new CheckBox("Sound", checkBoxStyle);
        SelectBox<String> resolutionSelectBox = new SelectBox<>(selectBoxStyle);
        resolutionSelectBox.setItems("1280x720", "1366x768", "1600x900", "1920x1080");

        TextButton backButton = new TextButton("Back", buttonStyle);

        // Добавление элементов на экран
        addActor(fullscreenCheckBox);
        addActor(soundCheckBox);
        addActor(resolutionSelectBox);
        addActor(backButton);

        // Определение центра экрана
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        // Установка позиций
        fullscreenCheckBox.setPosition(centerX, centerY + 60);
        soundCheckBox.setPosition(centerX, centerY + 30);
        resolutionSelectBox.setPosition(centerX, centerY);
        backButton.setPosition(centerX, centerY - 60);

        // Обработчики событий
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logger.info("Back button clicked. Returning to Main Menu.");
                gameApp.setScreen(new MainMenu(gameApp));
            }
        });

        fullscreenCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isChecked = fullscreenCheckBox.isChecked();
                Gdx.graphics.setFullscreenMode(isChecked ? Gdx.graphics.getDisplayMode() : null);
                logger.info("Fullscreen mode toggled: " + isChecked);
            }
        });

        soundCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isChecked = soundCheckBox.isChecked();
                logger.info("Sound toggled: " + isChecked);
                // Добавьте логику включения/выключения звука
            }
        });

        resolutionSelectBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String resolution = resolutionSelectBox.getSelected();
                setResolution(resolution);
                logger.info("Resolution changed to: " + resolution);
            }
        });
    }

    private void setResolution(String resolution) {
        String[] parts = resolution.split("x");
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        Gdx.graphics.setWindowedMode(width, height);
    }

    @Override
    public void render(float delta) {
        // Реализация рендера для SettingsMenu
    }

    @Override
    public void dispose() {
        logger.info("SettingsMenu disposed.");
    }
}
