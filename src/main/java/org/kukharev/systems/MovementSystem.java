package org.kukharev.systems;

import org.kukharev.objects.Player;

public class MovementSystem implements GameSystem {
    private final Player player;

    public MovementSystem(Player player) {
        this.player = player;
    }

    @Override
    public void update(float delta) {
        // Логика движения игрока
    }

    @Override
    public void render() {

    }
}
