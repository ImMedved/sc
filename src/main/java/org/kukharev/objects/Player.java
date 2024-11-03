package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject {
    private final TextureRegion texture;

    public Player(TextureRegion texture) {
        this.texture = texture;
        this.x = 100;
        this.y = 100;
    }

    public void update(float delta) {
        // Здесь можно добавлять логику передвижения, проверки столкновений
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {

    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float v) {
    }

    public void setY(float v) {
    }
}
