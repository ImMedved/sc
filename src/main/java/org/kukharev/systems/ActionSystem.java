package org.kukharev.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.kukharev.objects.Player;

public class ActionSystem implements GameSystem {
    private final Player player;
    private final TextureRegion[] actionTextures;
    private int currentActionFrame = 0;
    private float animationTimer = 0f;

    public ActionSystem(Player player, TextureRegion[] actionTextures) {
        this.player = player;
        this.actionTextures = actionTextures;
    }

    @Override
    public void update(float delta) {
        animationTimer += delta;
        if (animationTimer > 0.1f) {
            currentActionFrame = (currentActionFrame + 1) % actionTextures.length;
            player.setTexture(actionTextures[currentActionFrame]);
            animationTimer = 0;
        }
    }

    @Override
    public void render() {}
}
