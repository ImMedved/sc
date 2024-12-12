package org.kukharev.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.kukharev.objects.Player;

public class CombatSystem implements GameSystem {
    private final Player player;
    private final TextureRegion[] combatTextures;
    private int currentCombatFrame = 0;
    private float animationTimer = 0f;

    public CombatSystem(Player player, TextureRegion[] combatTextures) {
        this.player = player;
        this.combatTextures = combatTextures;
    }

    @Override
    public void update(float delta) {
        // Пример простой анимации атаки
        animationTimer += delta;
        if (animationTimer > 0.1f) {
            currentCombatFrame = (currentCombatFrame + 1) % combatTextures.length;
            player.setTexture(combatTextures[currentCombatFrame]);
            animationTimer = 0;
        }
    }

    @Override
    public void render() {}
}
