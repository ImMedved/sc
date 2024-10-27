package org.kukharev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import org.kukharev.core.GameApplication;
import org.kukharev.utils.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadingScreen extends Screen {
    private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
    private final GameApplication gameApp;
    private final AsyncExecutor asyncExecutor;

    public LoadingScreen(GameApplication gameApp) {
        this.gameApp = gameApp;
        this.asyncExecutor = new AsyncExecutor(1);

        // Background image
        Image backgroundImage = new Image(new Texture("assets/background.jpg"));
        addActor(backgroundImage);

        // Loading animation (GIF)
        Image loadingGif = new Image(new Texture("assets/loading.gif"));
        loadingGif.setPosition(Gdx.graphics.getWidth() / 2f - loadingGif.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - loadingGif.getHeight() / 2f);
        addActor(loadingGif);
    }

    public void startLoading() {
        asyncExecutor.submit(new AsyncTask<Void>() {
            @Override
            public Void call() throws Exception {
                logger.info("Resource loading started.");
                ResourceLoader.loadAllResources(); // Loading assets
                logger.info("Resource loading completed.");
                onLoadingComplete();
                return null;
            }
        });
    }

    private void onLoadingComplete() {
        Gdx.app.postRunnable(() -> {
            gameApp.setScreen(new MainMenu(gameApp));
        });
    }

    @Override
    public void render(float delta) {
        // Update and draw the stage
        act(delta);
        Batch batch = getStage().getBatch();
        draw(batch, 1.0f);
    }

    @Override
    public void dispose() {
        asyncExecutor.dispose();
        logger.info("LoadingScreen disposed.");
    }
}
