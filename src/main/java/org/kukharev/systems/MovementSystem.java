package org.kukharev.systems;

import org.kukharev.objects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MovementSystem implements GameSystem {
    private final Player player;
    private float speed = 100f;

    public MovementSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update(float delta) {
        float dx = 0;
        float dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx += speed * delta;
        }
        player.setX(player.getX() + dx);
        player.setY(player.getY() + dy);
    }

    @Override
    public void render() {
    }
}
