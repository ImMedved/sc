package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected float x, y; // Позиция объекта
    protected float width, height; // Размеры объекта

    // Конструктор и другие методы

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
