package org.kukharev.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.kukharev.objects.Player;

public class InputSystem implements GameSystem {
    private final Player player;

    public InputSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setX(player.getX() - 200 * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setX(player.getX() + 200 * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setY(player.getY() + 200 * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setY(player.getY() - 200 * delta);
        }
    }

    @Override
    public void render() {
        // В данном случае рендеринг не требуется для InputSystem
    }
}
