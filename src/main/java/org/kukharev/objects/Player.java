package org.kukharev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.kukharev.systems.ActionSystem;
import org.kukharev.systems.CombatSystem;
import org.kukharev.systems.InputSystem;
import org.kukharev.systems.MovementSystem;

public class Player extends GameObject {
    private TextureRegion currentTexture;
    private TextureRegion idleTexture;
    private TextureRegion walkFrontTextures[];
    private TextureRegion walkRightTextures[];
    private TextureRegion walkBackTextures[];
    private TextureRegion combatTextures[];
    private TextureRegion actionTextures[];

    private final MovementSystem movementSystem;
    private final InputSystem inputSystem;
    private final CombatSystem combatSystem;
    private final ActionSystem actionSystem;

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

        // Инициализация систем
        this.movementSystem = new MovementSystem(this);
        this.inputSystem = new InputSystem();
        this.combatSystem = new CombatSystem(this, combatTextures);
        this.actionSystem = new ActionSystem(this, actionTextures);
    }

    public void update(float delta) {
        // Обработка ввода и передвижения
        inputSystem.update(delta);
        movementSystem.update(delta);

        // Обработка системы боя
        combatSystem.update(delta);

        // Обработка действий
        actionSystem.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(currentTexture, x, y, width, height);
    }

    @Override
    public void dispose() {
        // Освобождение ресурсов, если требуется
    }

    public void setTexture(TextureRegion texture) {
        this.currentTexture = texture;
    }

    public TextureRegion[] getWalkFrontTextures() {
        return walkFrontTextures;
    }

    public TextureRegion[] getWalkRightTextures() {
        return walkRightTextures;
    }

    public TextureRegion[] getWalkBackTextures() {
        return walkBackTextures;
    }

    public TextureRegion[] getCombatTextures() {
        return combatTextures;
    }

    public TextureRegion[] getActionTextures() {
        return actionTextures;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
