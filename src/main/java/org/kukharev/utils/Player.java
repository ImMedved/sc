package org.kukharev.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private final Texture texture;
    private final float x;
    private final float y;

    public Player(String texturePath, float startX, float startY) {
        texture = new Texture(texturePath);
        this.x = startX;
        this.y = startY;
        Rectangle hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        // Logic for updating player position
        // Process user input
    }

    public void draw(Batch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
