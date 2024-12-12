package org.kukharev.screens.gameRun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.core.game.TimeSystem;
import org.kukharev.objects.Player;

public class HUD {
    private Player player;
    private TimeSystem timeSystem;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Hotbar hotbar;
    private ClockUI clockUI;

    public HUD(Player player, TimeSystem timeSystem, OrthographicCamera camera, SpriteBatch batch) {
        this.player = player;
        this.timeSystem = timeSystem;
        this.camera = camera;
        this.batch = batch;
        this.hotbar = new Hotbar(player);
        this.clockUI = new ClockUI(timeSystem);
    }

    public void render() {
        batch.begin();
        hotbar.render(batch);
        clockUI.render(batch, Gdx.graphics.getWidth()-200, 50);
        batch.end();
    }
}

