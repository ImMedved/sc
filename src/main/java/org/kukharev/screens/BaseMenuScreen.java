package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.kukharev.core.GameApplication;

public abstract class BaseMenuScreen implements Screen {
    protected final GameApplication game;
    protected final SpriteBatch batch;
    protected final Stage stage;
    protected final Texture backgroundTexture;

    public BaseMenuScreen(GameApplication game, SpriteBatch batch, Texture backgroundTexture) {
        this.game = game;
        this.batch = batch;
        this.stage = new Stage(new ScreenViewport());
        this.backgroundTexture = backgroundTexture;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.begin();
        batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
    }
}
