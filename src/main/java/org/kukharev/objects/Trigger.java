package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kukharev.core.game.GameWorld;
import org.kukharev.core.game.LevelManager;

public class Trigger extends GameObject {
    private final Rectangle area;
    private final String targetLevel;

    public Trigger(float x, float y, float width, float height, String targetLevel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetLevel = targetLevel;
        area = new Rectangle(x, y, width, height);
    }

    public Rectangle getArea() { return area; }
    public String getTargetLevel() { return targetLevel; }

    @Override
    public void update(float delta) {}
    @Override
    public void render(SpriteBatch batch) {}
    @Override
    public void dispose() {}

    public void activate(LevelManager levelManager, GameWorld gameWorld) {}
}
