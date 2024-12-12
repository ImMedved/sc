package org.kukharev.systems;

import org.kukharev.core.game.GameWorld;

public class MobSystem implements GameSystem {
    private GameWorld world;
    public MobSystem(GameWorld world) {
        this.world = world;
    }

    @Override
    public void update(float delta) {
        // Найти мобов в world.getWorldObjects()
        // Для каждого моба: двигаться к игроку, если в радиусе атаки - аттаковать
        // Если моб умирает - создать DroppedItem, удалить моба, запустить MobSpawnSystem
    }

    @Override
    public void render() {}
}

