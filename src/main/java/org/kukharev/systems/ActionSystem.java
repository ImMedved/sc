package org.kukharev.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.kukharev.objects.Player;

public class ActionSystem implements GameSystem {
    private final Player player;
    private final TextureRegion[] actionTextures;
    private int currentActionFrame = 0;
    private float animationTimer = 0;

    public ActionSystem(Player player, TextureRegion[] actionTextures) {
        this.player = player;
        this.actionTextures = actionTextures;
    }

    public void update(float delta) {
        // Логика использования действий (например, использование мотыгой или топором)
        animationTimer += delta;
        if (animationTimer > 0.1f) {  // Скорость смены кадра
            currentActionFrame = (currentActionFrame + 1) % actionTextures.length;
            player.setTexture(actionTextures[currentActionFrame]);
            animationTimer = 0;
        }
    }

    @Override
    public void render() {

    }
}
