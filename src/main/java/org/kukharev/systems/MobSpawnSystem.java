package org.kukharev.systems;

import org.kukharev.core.game.GameWorld;
import org.kukharev.objects.Mob;

public class MobSpawnSystem implements GameSystem {
    private GameWorld world;
    private float spawnTimer;
    private String levelName;
    private float x,y;

    public MobSpawnSystem(GameWorld w, String levelName, float x, float y) {
        this.world=w;this.levelName=levelName;this.x=x;this.y=y;
    }

    @Override
    public void update(float delta) {
        if (spawnTimer>0) {
            spawnTimer-=delta;
            if (spawnTimer<=0) {
                // Вставить нового моба в worldObjects
                world.getWorldObjects().add(new Mob(x,y));
            }
        }
    }

    public void startSpawnTimer() {
        spawnTimer=30f; // например через 30 секунд
    }

    @Override
    public void render() {}
}
