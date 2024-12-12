package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected float x, y;
    protected float width, height;

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
