package org.kukharev.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Texture texture;
    private float x, y;
    private Rectangle hitbox;

    public Player(String texturePath, float startX, float startY) {
        texture = new Texture(texturePath);
        this.x = startX;
        this.y = startY;
        this.hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        // Логика обновления позиции игрока
        // Обработать ввод пользователя
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
