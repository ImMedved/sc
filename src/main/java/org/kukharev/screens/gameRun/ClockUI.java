package org.kukharev.screens.gameRun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kukharev.core.game.TimeSystem;

public class ClockUI {
    private TimeSystem timeSystem;
    private Texture clockTexture;
    public ClockUI(TimeSystem timeSystem) {
        this.timeSystem = timeSystem;
        clockTexture = new Texture("assets/ui/clock.png");
    }

    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(clockTexture, x, y, 150,150);
        int hour = timeSystem.getHour();
        int minute = timeSystem.getMinute();
        // Нарисовать текст с временем
        // Используйте BitmapFont для вывода "HH:MM"
    }
}

