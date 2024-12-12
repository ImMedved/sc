package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.kukharev.systems.ActionSystem;
import org.kukharev.systems.CombatSystem;
import org.kukharev.systems.InputSystem;
import org.kukharev.systems.MovementSystem;

public class Player extends GameObject {
    private TextureRegion currentTexture;
    private final TextureRegion idleTexture;
    private final TextureRegion[] walkFrontTextures;
    private final TextureRegion[] walkRightTextures;
    private final TextureRegion[] walkBackTextures;
    private final TextureRegion[] combatTextures;
    private final TextureRegion[] actionTextures;

    // Системы могут быть управляемы извне (сейчас они инициализируются в GameWorld/SystemManager)
    // но можно оставить ссылку.
    public Player(TextureRegion idleTexture,
                  TextureRegion[] walkFrontTextures,
                  TextureRegion[] walkRightTextures,
                  TextureRegion[] walkBackTextures,
                  TextureRegion[] combatTextures,
                  TextureRegion[] actionTextures) {
        this.idleTexture = idleTexture;
        this.walkFrontTextures = walkFrontTextures;
        this.walkRightTextures = walkRightTextures;
        this.walkBackTextures = walkBackTextures;
        this.combatTextures = combatTextures;
        this.actionTextures = actionTextures;
        this.currentTexture = idleTexture;

        // Начальная позиция игрока
        this.x = 100;
        this.y = 100;

        // Установка размеров по текстуре (idle как базовый)
        this.width = idleTexture.getRegionWidth();
        this.height = idleTexture.getRegionHeight();
    }

    public void update(float delta) {
        // Логика обновления позиционируется теперь в MovementSystem, InputSystem и т.д.
        // Здесь можно оставить пустым или использовать только если есть локальная логика.
    }

    @Override
    public void render(SpriteBatch batch) {
        if (currentTexture != null) {
            batch.draw(currentTexture, x, y, width, height);
        }
    }

    @Override
    public void dispose() {
        // Если текcтуры управляются AssetManager, не освобождать тут.
    }

    public void setTexture(TextureRegion texture) {
        this.currentTexture = texture;
    }

    // Getters for texture arrays
    public TextureRegion[] getWalkFrontTextures() { return walkFrontTextures; }
    public TextureRegion[] getWalkRightTextures() { return walkRightTextures; }
    public TextureRegion[] getWalkBackTextures() { return walkBackTextures; }
    public TextureRegion[] getCombatTextures() { return combatTextures; }
    public TextureRegion[] getActionTextures() { return actionTextures; }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
